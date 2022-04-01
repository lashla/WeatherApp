package com.example.weatherapp.model

import com.google.gson.annotations.SerializedName

data class List (

    @SerializedName("dt"         ) var dt        : Int?,
    @SerializedName("sunrise"    ) var sunrise   : Int?,
    @SerializedName("sunset"     ) var sunset    : Int?,
    @SerializedName("temp"       ) var temp      : Temp?,
    @SerializedName("feels_like" ) var feelsLike : FeelsLike?,
    @SerializedName("pressure"   ) var pressure  : Double?,
    @SerializedName("humidity"   ) var humidity  : Int?,
    @SerializedName("weather"    ) var weather   : ArrayList<Weather>,
    @SerializedName("speed"      ) var speed     : Double?,
    @SerializedName("deg"        ) var deg       : Int?,
    @SerializedName("gust"       ) var gust      : Double?,
    @SerializedName("clouds"     ) var clouds    : Int?,
    @SerializedName("pop"        ) var pop       : Double?

)