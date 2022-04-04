package com.example.weatherapp.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.di.NetworkModule
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.*
import java.time.LocalDateTime
import javax.inject.Inject
import kotlin.math.roundToInt

@HiltViewModel
class WeatherViewModel @Inject constructor(private val repositoryInterface: RepositoryInterface): ViewModel() {

    var weatherData = MutableLiveData<ArrayList<String>>()
    var forecastData = MutableLiveData<ArrayList<String>>()
    private val errorMessage = MutableLiveData<String>()
    var isOperationCompleted: Boolean = true
    private var job: Job? = null

    @ViewModelScoped
    fun requestHandler(inputText: String) {
        viewModelScope.launch {
            makeRequest(inputText)
        }
    }

    private val onlinePosts = ArrayList<String>()
    private val onlineForecastPost = ArrayList<String>()
    @ViewModelScoped
    private suspend fun makeRequest(cityName: String) {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = repositoryInterface.getTempInfo(cityName, NetworkModule.provideRetrofitService(NetworkModule.provideRetrofit()))
            withContext(Dispatchers.Main){
                if (response.isSuccessful) {
                    response.body()?.let {
                        val requestCityName = it.city.name ?: "No city found"
                        val currentTemperature = it.list[0].main?.temp?.roundToInt().toString() + "°C"
                        val temperatureImage = "https://openweathermap.org/img/w/" + it.list[0].weather?.get(0)?.icon.toString() + ".png"
                        val humidity = it.list[0].main?.humidity.toString() + "%"
                        val airPressure = it.list[0].main?.pressure.toString() + "mph"
                        val windSpeed = it.list[0].wind?.speed.toString() + "M/S"
                        val currentVisibility = it.list[0].visibility.toString() + "M"
                        val weatherDescription = it.list[0].weather?.get(0)?.description.toString()
                        onlinePosts.add(requestCityName)
                        onlinePosts.add(currentTemperature)
                        onlinePosts.add(temperatureImage)
                        onlinePosts.add(humidity)
                        onlinePosts.add(airPressure)
                        onlinePosts.add(windSpeed)
                        onlinePosts.add(currentVisibility)
                        onlinePosts.add(weatherDescription)
                        weatherData.value = onlinePosts
                        for (weatherElement in 1..5) {
                            onlineForecastPost.add(it.list[weatherElement].main?.temp?.roundToInt().toString() + "°C")
                            onlineForecastPost.add("https://openweathermap.org/img/w/" + it.list[weatherElement].weather?.get(0)?.icon.toString() + ".png")
                            val localDateTime = LocalDateTime.parse((it.list[weatherElement].dt_txt.toString().replace(" ", "T")))
                            onlineForecastPost.add(localDateTime.hour.toString() + ":00")
                        }
                        forecastData.value = onlineForecastPost
                    }
                } else {
                    onError("Error: ${response.message()}")
                }
            }
        }

    }
    private fun onError(message: String) {
        errorMessage.value = message
        Log.i("Fragment", errorMessage.toString())
    }
    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}