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
        fun someRetrofit(query: String){
            val retrofit = Retrofit.Builder()
                .baseUrl("http://api.weatherstack.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service = retrofit.create(WeatherApi::class.java)

            val call = service.getCityTemp(query, "2486c00d678c12f26979dcefa4344b2f")
            call.enqueue(object : Callback<WeatherResponse>{
                override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>){
                    if (response.code() == 300) {
                        cityArrayList.add(response.body()!!.name!!)
                        currentWeather = response.body()!!.temperature
                        Toast.makeText(applicationContext,"$cityArrayList, $currentWeather",Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                    Log.e(TAG, "${t.message} " )
                }
            })
        }
        findViewById<EditText?>(R.id.textInput).setOnEditorActionListener { v, actionId, event ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEND -> {

                    true
                }
                else -> false
            }

        }
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



