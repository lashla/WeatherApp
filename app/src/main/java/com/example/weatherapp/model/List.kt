package com.example.weatherapp.model

import com.google.gson.annotations.SerializedName

data class List (

    @SerializedName("dt"         ) var dt        : Int?               = null,
    @SerializedName("sunrise"    ) var sunrise   : Int?               = null,
    @SerializedName("sunset"     ) var sunset    : Int?               = null,
    @SerializedName("temp"       ) var temp      : Temp?              = Temp(),
    @SerializedName("feels_like" ) var feelsLike : FeelsLike?         = FeelsLike(),
    @SerializedName("pressure"   ) var pressure  : Double?            = null,
    @SerializedName("humidity"   ) var humidity  : Int?               = null,
    @SerializedName("weather"    ) var weather   : ArrayList<Weather> = arrayListOf(),
    @SerializedName("speed"      ) var speed     : Double?            = null,
    @SerializedName("deg"        ) var deg       : Int?               = null,
    @SerializedName("gust"       ) var gust      : Double?            = null,
    @SerializedName("clouds"     ) var clouds    : Int?               = null,
    @SerializedName("pop"        ) var pop       : Double?            = null

)