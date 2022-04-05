package com.example.weatherapp.ui

import android.Manifest
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.weather_display_fragment.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.log


@AndroidEntryPoint
class WeatherDisplayFragment : Fragment(R.layout.weather_display_fragment) {

    private lateinit var viewModel: WeatherViewModel
    private var itemViewModel = ArrayList<String>()
    val data = ArrayList<ItemViewModel>()
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        initViewModel()
        setupSearchButtons()
    }

    private fun initRecyclerView(data: ArrayList<ItemViewModel>){
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        val adapter = CustomRecyclerAdapter(requireContext(), data)
        recyclerView.adapter = adapter
    }


    @RequiresApi(Build.VERSION_CODES.CUPCAKE)
    private fun initView() {
        textInput.setOnEditorActionListener { _, keyCode, event ->
            if (((event?.action ?: -1) == KeyEvent.ACTION_DOWN)
                || keyCode == EditorInfo.IME_ACTION_DONE
            ) {
                progressBar.visibility = ProgressBar.VISIBLE
                searchView.visibility = View.INVISIBLE
                textInput.visibility = View.INVISIBLE
                cancelSearchBtn.visibility = View.INVISIBLE
                weatherDisplay(textInput.text.toString())
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }

    private fun weatherDisplay(cityInput: String) {
        enteredCityName.visibility = View.VISIBLE
        viewModel.requestHandler(cityInput)
        if (!viewModel.isOperationCompleted) {

            Toast.makeText(context, "Something went wrong, try using another input", Toast.LENGTH_LONG).show()
        }
    }
    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[WeatherViewModel::class.java]
        viewModel.weatherData.observe(viewLifecycleOwner) {
            enteredCityName.text = it[0]
            tvTemperature.text = it[1]
            Picasso.with(context)
                .load(it[2])
                .error(androidx.constraintlayout.widget.R.drawable.abc_btn_check_to_on_mtrl_000)
                .into(imageView)
            tvHumidityValue.text = it[3]
            tvAirPressureValue.text = it[4]
            tvWindStatusValue.text = it[5]
            tvVisibilityValue.text = it[6]
            weatherDiscription.text = it[7]
            progressBar.visibility = ProgressBar.INVISIBLE
            weatherDiscription.visibility = TextView.VISIBLE
            enteredCityName.visibility = TextView.VISIBLE
            tvTemperature.visibility = TextView.VISIBLE
            imageView.visibility = ImageView.VISIBLE
        }
        viewModel.forecastData.observe(viewLifecycleOwner){

            for (element in 0..29 step 3) {
                data.add(ItemViewModel(it[element], it[element+1], it[element+2]))
            }

            initRecyclerView(data)
//            forecastViewCon1.attributeSourceResourceMap
//            forecastViewCon1.initForecastDataView()
//            tvDayOfWeekTemp1.text = it[0]
//            Picasso.with(context)
//                .load(it[1])
//                .error(androidx.constraintlayout.widget.R.drawable.abc_btn_check_to_on_mtrl_000)
//                .into(ivDayOfWeek1)
//            tvDayOfWeek1.text = it[2]
//            tvDayOfWeekTemp2.text = it[3]
//            Picasso.with(context)
//                .load(it[4])
//                .error(androidx.constraintlayout.widget.R.drawable.abc_btn_check_to_on_mtrl_000)
//                .into(ivDayOfWeek2)
//            tvDayOfWeek2.text = it[5]
//            tvDayOfWeekTemp3.text = it[6]
//            Picasso.with(context)
//                .load(it[7])
//                .error(androidx.constraintlayout.widget.R.drawable.abc_btn_check_to_on_mtrl_000)
//                .into(ivDayOfWeek3)
//            tvDayOfWeek3.text = it[8]
//            tvDayOfWeekTemp4.text = it[9]
//            Picasso.with(context)
//                .load(it[10])
//                .error(androidx.constraintlayout.widget.R.drawable.abc_btn_check_to_on_mtrl_000)
//                .into(ivDayOfWeek4)
//            tvDayOfWeek4.text = it[11]
//            tvDayOfWeekTemp5.text = it[12]
//            Picasso.with(context)
//                .load(it[13])
//                .error(androidx.constraintlayout.widget.R.drawable.abc_btn_check_to_on_mtrl_000)
//                .into(ivDayOfWeek5)
//            tvDayOfWeek5.text = it[14]
        }
    }

    private fun setupSearchButtons() {
        searchBtn.setOnClickListener {
            searchView.visibility = View.VISIBLE
            textInput.visibility = View.VISIBLE
            cancelSearchBtn.visibility = View.VISIBLE
        }
        cancelSearchBtn.setOnClickListener{
            searchView.visibility = View.INVISIBLE
            textInput.visibility = View.INVISIBLE
            cancelSearchBtn.visibility = View.INVISIBLE
        }
    }
    private fun setupLocationWeatherButton(){

    }
}