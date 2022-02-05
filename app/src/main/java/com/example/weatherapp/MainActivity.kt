package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

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
                    sendMessage()
                    true
                }
                else -> false
            }

        }

    }

    private fun sendMessage() {
        Toast.makeText(this, "Button is Pratsye",Toast.LENGTH_LONG).show()
    }
}

