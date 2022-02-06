package com.example.weatherapp

import android.content.ContentValues
import kotlinx.android.synthetic.main.activity_main.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo

import com.example.weatherapp.api.ViewModel
import com.example.weatherapp.api.WeatherApi
import com.example.weatherapp.api.WeatherResponse
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    var icon: List<String>? = listOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fun someRetrofit(query: String,){
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.weatherstack.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service = retrofit.create(WeatherApi::class.java)

            val call = service.getCityTemp(query, "2486c00d678c12f26979dcefa4344b2f")
            call.enqueue(object : Callback<WeatherResponse> {
                override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>){
                    if (response.code() == 200) {
                        enteredCityName.text = response.body()!!.location?.name ?: "No city found"
                        tvTemperature.text = response.body()!!.current?.temperature.toString()
                        Picasso.with(this@MainActivity)
                            .load(response.body()!!.current?.weather_icons?.get(0))
                            .error(androidx.constraintlayout.widget.R.drawable.abc_btn_check_to_on_mtrl_000)
                            .into(imageView);
                    }
                }

                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                    Log.e(ContentValues.TAG, "${t.message} " )
                }
            })
        }
        textInput.setOnEditorActionListener { _, keyCode, event ->
            if (((event?.action ?: -1) == KeyEvent.ACTION_DOWN)
                || keyCode == EditorInfo.IME_ACTION_DONE) {

                someRetrofit(textInput.text.toString())

                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }





}
}



