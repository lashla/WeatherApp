package com.example.weatherapp

import android.content.ContentValues
import kotlinx.android.synthetic.main.activity_main.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo

import com.example.weatherapp.api.WeatherApi
import com.example.weatherapp.api.WeatherResponse
import com.squareup.picasso.Picasso
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    //опчитай про lazy инициализаторы

    private val retrofit by lazy {
        Retrofit.Builder()

            .baseUrl("http://api.weatherstack.com/") //Ты неправильно указал url(https недоступен). Когда используешь  http а не https то не забывай в манифесте указывать         android:usesCleartextTraffic="true"
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Не нагромождай onCreate выноси все в отдельные функции

        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        textInput.setOnEditorActionListener { _, keyCode, event ->
            if (((event?.action ?: -1) == KeyEvent.ACTION_DOWN)
                || keyCode == EditorInfo.IME_ACTION_DONE
            ) {
                makeRequest(textInput.text.toString())
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }

    private fun makeRequest(query: String) {
        val call = service.getCityTemp(query, "2486c00d678c12f26979dcefa4344b2f")
        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                //не проверяй на 200 код. так как успешный ответ это коды с 200 по 299
                if (response.isSuccessful) {
                    //почитай про let apply with штуки

                    response.body()?.let {
                        enteredCityName.text = it.location?.name ?: "No city found"
                        tvTemperature.text = it.current?.temperature.toString()
                        Picasso.with(this@MainActivity)
                            .load(it.current?.weatherIcons?.get(0))
                            .error(androidx.constraintlayout.widget.R.drawable.abc_btn_check_to_on_mtrl_000)
                            .into(imageView)
                    }

                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.e(ContentValues.TAG, "${t.message} ")
            }
        })
    }

}



