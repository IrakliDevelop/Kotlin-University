package com.example.cufinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cufinal.adapters.WeatherNextFiveDaysAdapter
import com.example.cufinal.adapters.WeatherNextHoursAdapter
import com.example.cufinal.dto.weather.WeatherInfo
import com.example.cufinal.dto.weather.WeatherResponse
import com.example.cufinal.services.ApiInterface
import com.example.cufinal.services.ApiUtils
import com.example.cufinal.utils.Util
import com.example.cufinal.utils.Util.fromEpochToDate
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var linearLayoutManagerForNextDaysView: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var apiService: ApiInterface? = null
        val loading: ProgressBar = findViewById(R.id.loading)

        // manage layout for adapter
        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        weatherNextHoursRecyclerView.layoutManager = linearLayoutManager

        // layout for second adapter
        linearLayoutManagerForNextDaysView = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        weatherNextFiveDaysRecyclerView.layoutManager = linearLayoutManagerForNextDaysView

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
                loading.visibility = View.GONE
                data.value = response.body()!!
                weatherResponse = data.value!!

                // set country and city
                val city = weatherResponse.city.name
                val countryCode = weatherResponse.city.country
                val country = Locale("", countryCode).displayCountry
                findViewById<TextView>(R.id.city).text = city
                findViewById<TextView>(R.id.country).text = country

                // set current temperature
                val temperature = Util.getAndFormatTemperature(weatherResponse.list, 0)
                findViewById<TextView>(R.id.currentTemperature).text = "$temperature°C"

                // set current weather
                val weatherIcon = Util.getIconCode(weatherResponse.list, 0)
                val currentWeatherIcon = findViewById<ImageView>(R.id.currentWeatherIcon)
                currentWeatherIcon.setImageResource(Util.getIconFromCode(weatherIcon))

                // set current weather description
                val currentWeatherDescription = weatherResponse.list[0].weather[0].description
                findViewById<TextView>(R.id.currentWeatherDescription).text = currentWeatherDescription


                // set next 15 hours
                val weatherNextHoursList = weatherResponse.list.slice(1..5)
                val adapter = WeatherNextHoursAdapter(weatherNextHoursList)
                weatherNextHoursRecyclerView.adapter = adapter

                // next 5 days
                val nextFiveDaysMutable: MutableList<WeatherInfo> = arrayListOf()
                val nextFiveNightsMutable: MutableList<WeatherInfo> = arrayListOf()

                weatherResponse.list.forEach { element: WeatherInfo ->
                    run {
                        val hour = Util.getHourFromDate(fromEpochToDate(element.date)).toInt()
                        // day
                        if (hour in 11..13) {
                            nextFiveDaysMutable.add(element)
                        }
                        if (hour in 20..23) {
                            nextFiveNightsMutable.add(element)
                        }
                    }
                }
                val nextFiveDays: List<WeatherInfo> = nextFiveDaysMutable.toList()
                val nextFiveNights: List<WeatherInfo> = nextFiveNightsMutable.toList()

                val nextFiveDaysAdapter = WeatherNextFiveDaysAdapter(nextFiveDays, nextFiveNights)
                weatherNextFiveDaysRecyclerView.adapter = nextFiveDaysAdapter
            } })
    }
}
