package com.example.cufinal.utils

import kotlin.math.roundToInt

object Util {
    fun kelvinToCelsius(temperature: Double): Int {
        return (temperature - 273.15).roundToInt()
    }
}