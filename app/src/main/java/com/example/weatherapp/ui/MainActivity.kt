package com.example.weatherapp.ui

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
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.weatherapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavigationAndActionBars()
    }

    private fun setupNavigationAndActionBars() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.navigation)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        val navController: NavController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.mainFragment, R.id.weatherDisplayFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)
        bottomNavigationView.setupWithNavController(navController)
    }
//    private fun setupBottomNavigation() {
//
//    }
//    private fun setupActionBar(){
//        val navController = findNavController(R.id.fragmentContainerView)
//
//    }
//    @RequiresApi(Build.VERSION_CODES.CUPCAKE)
//    private fun initView() {
//        textInput.setOnEditorActionListener { _, keyCode, event ->
//            if (((event?.action ?: -1) == KeyEvent.ACTION_DOWN)
//                || keyCode == EditorInfo.IME_ACTION_DONE
//            ) {
//                progressBar.visibility = ProgressBar.VISIBLE
//                weatherDisplay(textInput.text.toString())
//                return@setOnEditorActionListener true
//            }
//            return@setOnEditorActionListener false
//        }
//    }
//
//    private fun weatherDisplay(cityInput: String) {
//        viewModel.requestHandler(cityInput)
//        if (!viewModel.isOperationCompleted) {
//            Toast.makeText(this, "Something went wrong, try using another input", Toast.LENGTH_LONG).show()
//        }
//        }
//    private fun initViewModel() {
//        viewModel = ViewModelProvider(this)[WeatherViewModel::class.java]
//        viewModel.weatherData.observe(this) {
//            enteredCityName.text = it[0]
//            tvTemperature.text = it[1]
//            Picasso.with(this@MainActivity)
//                .load(it[2])
//                .error(androidx.constraintlayout.widget.R.drawable.abc_btn_check_to_on_mtrl_000)
//                .into(imageView)
//            progressBar.visibility = ProgressBar.INVISIBLE
//            enteredCityName.visibility = TextView.VISIBLE
//            tvTemperature.visibility = TextView.VISIBLE
//            imageView.visibility = ImageView.VISIBLE
//            it.clear()
//        }
//    }
}



