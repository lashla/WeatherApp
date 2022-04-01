package com.example.weatherapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.di.NetworkModule
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val repositoryInterface: RepositoryInterface): ViewModel() {

    var weatherData = MutableLiveData<ArrayList<String>>()
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

    @ViewModelScoped
    private suspend fun makeRequest(cityName: String) {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = repositoryInterface.getTempInfo(cityName, NetworkModule.provideRetrofitService(NetworkModule.provideRetrofit()))
            withContext(Dispatchers.Main){
                if (response.isSuccessful) {
                    response.body()?.let {

                        val requestCityName = it.city.name ?: "No city found"
                        val currentTemperature = it.temp?.day.toString()
                        val temperatureImage = it.weather.icon
                        val humidity = it.list.humidity.toString() + "%"
                        val airPressure = it.list.pressure.toString() + "mph"
                        val windSpeed = it.list.speed.toString() + "m/s"
                        val currentVisibility = it.list.gust.toString()+ "m/s"
                        val weatherDescription = it.weather.description
                        onlinePosts.add(requestCityName)
                        onlinePosts.add(currentTemperature)
                        onlinePosts.add(temperatureImage!!)
                        onlinePosts.add(humidity)
                        onlinePosts.add(airPressure)
                        onlinePosts.add(windSpeed)
                        onlinePosts.add(currentVisibility)
                        onlinePosts.add(weatherDescription!!)
                        weatherData.value = onlinePosts
                    }
                } else {
                    onError("Error: ${response.message()}")
                }
            }
        }

    }
    private fun onError(message: String) {
        errorMessage.value = message
    }
    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}
