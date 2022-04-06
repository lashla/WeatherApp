package com.example.weatherapp.ui

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationToken
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.weather_display_fragment.*
import java.util.*


@AndroidEntryPoint
class WeatherDisplayFragment : Fragment(R.layout.weather_display_fragment) {

    private lateinit var viewModel: WeatherViewModel
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var currentLocation: Location
    private var geoCityName: String? = null

    val data = ArrayList<ItemViewModel>()
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        initViewModel()
        setupSearchButtons()
        setupLocationWeatherButton()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        setupFusedLocation()
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
        locationButton.setOnClickListener {
            if (!geoCityName.isNullOrEmpty()){
                weatherDisplay(geoCityName!!)
            } else {
                Toast.makeText(requireContext(), "Turn on your location or give the app permissions", Toast.LENGTH_LONG).show()
            }

        }
    }

    private fun setupFusedLocation(){
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())

        var isLocationGranted = true
        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            isLocationGranted = when {
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                    true
                }
                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                    true
                } else -> {
                    false
                }
            }
        }

        locationPermissionRequest.launch(arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION))


        when (PackageManager.PERMISSION_GRANTED) {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) -> {
                val geocoder: Geocoder = Geocoder(context, Locale.getDefault())
                isLocationGranted = true
                fusedLocationClient.lastLocation
                    .addOnSuccessListener {
                        if (it != null){
                            currentLocation = it
                            geoCityName = geocoder.getFromLocation(currentLocation.latitude, currentLocation.longitude, 1)[0].locality
                        } else {
                            geoCityName = "New York"
                            Toast.makeText(requireContext(), "Isn't possible to take your last location, setting to default", Toast.LENGTH_LONG).show()
                        }
                    }

                    .addOnFailureListener {

                    }


            }
            else -> {
                requestPermissions(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION),
                    1)
            }
        }
    }
}