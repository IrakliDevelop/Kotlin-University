package com.example.cufinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import com.example.cufinal.dto.weather.WeatherResponse
import com.example.cufinal.services.ApiInterface
import com.example.cufinal.services.ApiUtils
import com.example.cufinal.utils.Util
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var apiService: ApiInterface? = null
        val loading: ProgressBar = findViewById(R.id.loading)

        loading.visibility = View.VISIBLE

        apiService = ApiUtils.apiService

        val fetch = apiService.fetchAll()
        val data = MutableLiveData<WeatherResponse>()

        var weatherResponse: WeatherResponse

        fetch.enqueue(object: Callback<WeatherResponse> {
            override fun onFailure(call: Call<WeatherResponse>?, t: Throwable?) {
                Log.v("retrofit", "call failed")
                // TODO: notify user
            }
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                Log.d("retrofit", "success")
                loading.visibility = View.GONE
                Log.d("data", "${response.body()}")

                data.value = response.body()!!
                weatherResponse = data.value!!

                // set country and city
                val city = weatherResponse.city.name
                val countryCode = weatherResponse.city.country
                val country = Locale("", countryCode).displayCountry
                findViewById<TextView>(R.id.city).text = city
                findViewById<TextView>(R.id.country).text = country

                // set current temperature
                val temperatureInKelvin = weatherResponse.list[0].mainInfo.temperature
                val temperature = Util.kelvinToCelsius(temperatureInKelvin)
                findViewById<TextView>(R.id.currentTemperature).text = "$temperature°C"


                // set current weather
                val weatherIcon = weatherResponse.list[0].weather[0].icon
                val currentWeatherIcon = findViewById<ImageView>(R.id.currentWeatherIcon)
                currentWeatherIcon.setImageResource(Util.getIconFromCode(weatherIcon))

                // set current weather description
                val currentWeatherDescription = weatherResponse.list[0].weather[0].description
                findViewById<TextView>(R.id.currentWeatherDescription).text = currentWeatherDescription
            } })
    }
}
