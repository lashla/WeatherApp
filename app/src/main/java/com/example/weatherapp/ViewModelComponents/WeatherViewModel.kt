package com.example.weatherapp.ViewModelComponents

import android.content.ContentValues
import android.util.Log
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.RequestQueue
import com.example.weatherapp.api.WeatherApi
import com.example.weatherapp.api.WeatherResponse
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class WeatherViewModel(private val mainRepository: MainRepository): ViewModel() {
    var weatherData = MutableLiveData<ArrayList<String>>()
    private val errorMessage = MutableLiveData<String>()
    var isOperationCompleted: Boolean = true
    private var job: Job? = null

    fun requestHandler(inputText: String) {
        viewModelScope.launch {
            makeRequest(inputText)
        }
    }

    private val onlinePosts = ArrayList<String>()

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
