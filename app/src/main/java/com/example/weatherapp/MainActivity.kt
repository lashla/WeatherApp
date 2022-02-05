package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.example.weatherapp.api.WeatherApi
import com.example.weatherapp.api.WeatherResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    var inputCityName: String = ""
    private lateinit var textInput: EditText
    private lateinit var enteredCityName: TextView
    private lateinit var imageView: ImageView
    private lateinit var tvTemperature: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val enteredCityName: TextView = findViewById(R.id.enteredCityName)
        findViewById<EditText?>(R.id.textInput).setOnEditorActionListener { v, actionId, event ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEND -> {

                    true
                }
                else -> false
            }

        }
        val retrofit = Retrofit.Builder()
            .baseUrl("http://api.weatherstack.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(WeatherApi::class.java)

        val call = service.getCurrentWeatherData(query, "2486c00d678c12f26979dcefa4344b2f")
        val stringBuilder = Html.fromHtml(
            "<b>País:</b> " + WeatherResponse.sys!!.country + "<br>" +
                    "<b>Temperatura:</b> " +(WeatherResponse.main!!.temp - 273 ) + " ºC"
    }
}

