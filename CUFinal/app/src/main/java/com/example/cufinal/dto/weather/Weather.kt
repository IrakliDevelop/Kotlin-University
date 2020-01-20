package com.example.cufinal.dto.weather

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("cod")
    val code: String,
    @SerializedName("message")
    val message: Number,
    @SerializedName("cnt")
    val count: Number,
    @SerializedName("city")
    val city: City,
    @SerializedName("list")
    val list: List<WeatherInfo>
)

data class City(
    @SerializedName("id")
    val id: Number,
    @SerializedName("name")
    val name: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("population")
    val population: Number,
    @SerializedName("timezone")
    val timezone: Number,
    @SerializedName("sunrise")
    val sunrsie: Number,
    @SerializedName("sunset")
    val sunset: Number,
    @SerializedName("coord")
    val coordinates: Coordinates
)

data class Coordinates(
    @SerializedName("lat")
    val latitude: Double,
    @SerializedName("long")
    val longitude: Double
)

data class WeatherInfo(
    @SerializedName("dt")
    val date: Number,
    @SerializedName("dt_txt")
    val dateText: String,
    @SerializedName("main")
    val mainInfo: MainInfo,
    @SerializedName("weather")
    val weather: List<Weather>,
    @SerializedName("clouds")
    val clouds: Clouds,
    @SerializedName("wind")
    val wind: Wind
)

data class MainInfo(
    @SerializedName("temp")
    val temperature: Double,
    @SerializedName("feels_like")
    val feels_like: Double,
    @SerializedName("temp_min")
    val temp_min: Double,
    @SerializedName("temp_max")
    val temp_max: Double,
    @SerializedName("pressure")
    val pressure: Number,
    @SerializedName("sea_level")
    val sea_level: Number,
    @SerializedName("grnd_level")
    val grnd_level: Number,
    @SerializedName("humidity")
    val humidity: Number,
    @SerializedName("temp_kf")
    val temp_kf: Double
)

data class Weather(
    @SerializedName("id")
    val id: Number,
    @SerializedName("main")
    val main: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("icon")
    val icon: String
)

data class Wind(
    @SerializedName("speed")
    val speed: Double,
    @SerializedName("deg")
    val deg: Number
)

data class Clouds(
    @SerializedName("all")
    val all: Number
)

// I don't know what the fuck is this for, but api gives us this kind of information
data class Sys(
    @SerializedName("pod")
    val pod: String
)

// this class is needed for recycler view of next five days and nights
data class WeatherFull(
    val days: List<WeatherInfo>,
    val nights: List<WeatherInfo>
)