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
    private val enteredText = ArrayList<String>()
    private lateinit var textInput: EditText
    private lateinit var enteredCityName: TextView
    private lateinit var imageView: ImageView
    private lateinit var tvTemperature: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textInput = findViewById(R.id.textInput)

        fun inputText(){
            enteredText.add(textInput.text.toString())
            Toast.makeText(this, "Added$enteredText", Toast.LENGTH_SHORT).show()
        }
        inputText()

    }
}

