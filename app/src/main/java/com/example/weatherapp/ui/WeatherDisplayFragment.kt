package com.example.weatherapp.ui

import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.weatherapp.R
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.weather_display_fragment.*

@AndroidEntryPoint
class WeatherDisplayFragment : Fragment() {

    private lateinit var viewModel: WeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.weather_display_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        initViewModel()
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
        if (!viewModel.isOperationCompleted) {
            Toast.makeText(context, "Something went wrong, try using another input", Toast.LENGTH_LONG).show()
        }
    }
    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[WeatherViewModel::class.java]
        viewModel.weatherData.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                enteredCityName.text = it[0]
                tvTemperature.text = it[1]
                Picasso.with(context)
                    .load(it[2])
                    .error(androidx.constraintlayout.widget.R.drawable.abc_btn_check_to_on_mtrl_000)
                    .into(imageView)
                progressBar.visibility = ProgressBar.INVISIBLE
                enteredCityName.visibility = TextView.VISIBLE
                tvTemperature.visibility = TextView.VISIBLE
                imageView.visibility = ImageView.VISIBLE
                it.clear()
            }
        }
    }
}