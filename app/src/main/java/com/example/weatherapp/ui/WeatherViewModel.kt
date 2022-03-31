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
    private suspend fun makeRequest(query: String) {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = repositoryInterface.getTempInfo(query, NetworkModule.provideRetrofitService(NetworkModule.provideRetrofit()))
            withContext(Dispatchers.Main){
                if (response.isSuccessful) {
                    response.body()?.let {

                        val cityName = it.location?.name ?: "No city found"
                        val currentTemperature = it.current?.temperature.toString()
                        val temperatureImage = it.current?.weatherIcons?.get(0)
                        val humidity = it.current?.humidity.toString() + "%"
                        val airPressure = it.current?.pressure.toString() + "mph"
                        val windSpeed = it.current?.windSpeed.toString() + "m/s"
                        val currentVisibility = it.current?.visibility.toString() + "%"
                        val weatherDescription = it.current?.weatherDescriptions?.get(0)
                        onlinePosts.add(cityName)
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
