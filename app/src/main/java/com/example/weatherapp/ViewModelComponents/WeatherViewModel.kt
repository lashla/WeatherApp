package com.example.weatherapp.ViewModelComponents

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val mainRepository: MainRepository): ViewModel() {

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
            val response = mainRepository.getTempInfo(query)
            withContext(Dispatchers.Main){
                if (response.isSuccessful) {
                    response.body()?.let {

                        val cityName = it.location?.name ?: "No city found"
                        val currentTemperature = it.current?.temperature.toString()
                        val temperatureImage = it.current?.weatherIcons?.get(0)

                        onlinePosts.add(cityName)
                        onlinePosts.add(currentTemperature)
                        onlinePosts.add(temperatureImage!!)
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
