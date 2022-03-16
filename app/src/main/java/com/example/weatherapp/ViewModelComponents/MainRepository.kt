package com.example.weatherapp.ViewModelComponents

import android.content.ContentValues
import android.util.Log
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.api.WeatherApi
import com.example.weatherapp.api.WeatherResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainRepository(private val retrofitService: WeatherApi) {

    suspend fun getTempInfo(query: String) = retrofitService.getCityTemp(query, "2486c00d678c12f26979dcefa4344b2f")

}