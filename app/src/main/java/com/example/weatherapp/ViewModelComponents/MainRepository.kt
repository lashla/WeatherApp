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

class MainRepository() {

    var posts = MutableLiveData<ArrayList<String>>()

    private val retrofit by lazy {
        Retrofit.Builder()

            .baseUrl("http://api.weatherstack.com/") //         android:usesCleartextTraffic="true"
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    private val okHttpClient by lazy {
        OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor()).build()
    }

    private val service by lazy {
        retrofit.create(WeatherApi::class.java)
    }

    suspend fun makeRequest(query: String) {
        val call = service.getCityTemp(query, "2486c00d678c12f26979dcefa4344b2f")
        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                if (response.isSuccessful) {
                    val onlinePosts = ArrayList<String>()

                    response.body()?.let {

                        val cityName = it.location?.name ?: "No city found"
                        val currentTemperature = it.current?.temperature.toString()
                        val temperatureImage = it.current?.weatherIcons?.get(0)

                        onlinePosts.add(cityName)
                        onlinePosts.add(currentTemperature)
                        onlinePosts.add(temperatureImage!!)
                        posts.value = onlinePosts
                    }
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.e(ContentValues.TAG, "${t.message} ")
            }
        })

    }


}