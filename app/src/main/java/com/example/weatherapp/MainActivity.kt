package com.example.weatherapp

import android.content.ContentValues
import android.os.Build
import kotlinx.android.synthetic.main.activity_main.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.ViewModelComponents.MainRepository
import com.example.weatherapp.ViewModelComponents.MyViewModelFactory
import com.example.weatherapp.ViewModelComponents.WeatherViewModel
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

enum class Status{
    SUCCESS,
    ERROR,
    LOADING
}

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var viewModel: WeatherViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val retrofitSerevice = WeatherApi.getInstance()
        val mainRepository = MainRepository(retrofitSerevice)
        viewModel = ViewModelProvider(this, MyViewModelFactory(mainRepository))[WeatherViewModel::class.java]
        initView()
    }

    @RequiresApi(Build.VERSION_CODES.CUPCAKE)
    private fun initView() {
        textInput.setOnEditorActionListener { _, keyCode, event ->
            if (((event?.action ?: -1) == KeyEvent.ACTION_DOWN)
                || keyCode == EditorInfo.IME_ACTION_DONE
            ) {
                progressBar.visibility = ProgressBar.VISIBLE
                weatherDisplay(textInput.text.toString())
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }

    private fun weatherDisplay(cityInput: String) {
        viewModel.requestHandler(cityInput)
        if (viewModel.isOperationCompleted) {
            viewModel.weatherData.observe(this) {
                enteredCityName.text = it[0]
                tvTemperature.text = it[1]
                Picasso.with(this@MainActivity)
                    .load(it[2])
                    .error(androidx.constraintlayout.widget.R.drawable.abc_btn_check_to_on_mtrl_000)
                    .into(imageView)
                progressBar.visibility = ProgressBar.INVISIBLE
                enteredCityName.visibility = TextView.VISIBLE
                tvTemperature.visibility = TextView.VISIBLE
                imageView.visibility = ImageView.VISIBLE
            }
        } else {
            Toast.makeText(this, "Something went wrong, try using another input", Toast.LENGTH_LONG).show()
        }
        }
}



