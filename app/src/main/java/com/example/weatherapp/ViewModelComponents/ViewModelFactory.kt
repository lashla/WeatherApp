package com.example.weatherapp.ViewModelComponents

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class MyViewModelFactory constructor(private val repository: MainRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            WeatherViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }


}