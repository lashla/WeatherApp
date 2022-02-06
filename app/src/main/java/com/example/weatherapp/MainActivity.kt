package com.example.weatherapp

import android.app.DownloadManager
import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.weatherapp.api.WeatherApi
import com.example.weatherapp.api.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.ArrayList
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    private val cityArrayList = ArrayList<String>()
    private var currentWeather: Int = 0
    private lateinit var textInput: EditText
    private lateinit var enteredCityName: TextView
    private lateinit var imageView: ImageView
    private lateinit var tvTemperature: TextView
    private lateinit var query: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textInput = findViewById(R.id.textInput)
        textInput.setOnKeyListener(object : View.OnKeyListener{
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
                if (event != null) {
                    if (event.action == KeyEvent.ACTION_DOWN &&
                        keyCode == KeyEvent.KEYCODE_ENTER) {
                        Toast.makeText(applicationContext, "pressed some key$textInput", Toast.LENGTH_LONG).show()

                        textInput.clearFocus()
                        textInput.isCursorVisible = false

                        return true
                    }
                }
                return false
            }
        })



}
}



