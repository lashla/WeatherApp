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
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class WeatherViewModel: ViewModel() {
    private var mainRepository = MainRepository()
    var weatherData = MutableLiveData<ArrayList<String>>()


    fun requestHandler(inputText: String){
        viewModelScope.launch(Dispatchers.IO) {
            mainRepository.makeRequest(inputText)
        }
    weatherData = mainRepository.posts
    }
}