package com.example.weatherapp.api

import android.util.Log
import com.example.weatherapp.api.RetrfitInstance
import retrofit2.HttpException
import java.io.IOException

const val TAG = "REQUESTHANDLER"

class RequestHandler {
    fun responseHandler() {
        val response = try {
            RetrfitInstance.api.getPost("2486c00d678c12f26979dcefa4344b2f")
        } catch (e: IOException) {
            Log.e(TAG, "NO INTENET CONECTION ")
        } catch (e: HttpException) {
            Log.e(TAG, "HttpException, unexpected response")
        }

    }
}