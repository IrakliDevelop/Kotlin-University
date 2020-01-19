package com.example.cufinal.utils

import com.example.cufinal.R
import kotlin.math.roundToInt

object Util {
    fun kelvinToCelsius(temperature: Double): Int {
        return (temperature - 273.15).roundToInt()
    }

    fun getIconFromCode(iconCode: String): Int {
        return when (iconCode) {
            "01d" -> R.drawable.sunny
            "02d" -> R.drawable.partially_cloudy
            "03d" -> R.drawable.cloudy
            "04d" -> R.drawable.double_clouds
            "09d" -> R.drawable.light_rain
            "10d" -> R.drawable.heavy_rain
            "11d" -> R.drawable.thunderstorm
            "13d" -> R.drawable.snow
            "50d" -> R.drawable.wind
            "01n" -> R.drawable.clear_night
            "02n" -> R.drawable.partially_cloudy_night
            "03n" -> R.drawable.cloudy
            "04n" -> R.drawable.double_clouds
            "09n" -> R.drawable.light_rain_night
            "10n" -> R.drawable.heavy_rain
            "11n" -> R.drawable.thunderstorm
            "13n" -> R.drawable.snow
            else -> R.drawable.wind
        }
    }
}