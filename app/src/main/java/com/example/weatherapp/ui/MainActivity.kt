package com.example.weatherapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.weatherapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavigationAndActionBars()
    }

    private fun setupNavigationAndActionBars() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.navigation)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        val navController: NavController = navHostFragment.navController
        val appBarConfiguration =
            AppBarConfiguration(setOf(R.id.mainFragment, R.id.weatherDisplayFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)
        bottomNavigationView.setupWithNavController(navController)
    }
}

