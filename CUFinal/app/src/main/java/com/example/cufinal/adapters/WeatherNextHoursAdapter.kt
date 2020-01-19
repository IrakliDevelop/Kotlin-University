package com.example.cufinal.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.cufinal.dto.weather.WeatherInfo
import androidx.recyclerview.widget.RecyclerView
import com.example.cufinal.R
import com.example.cufinal.utils.Util


class WeatherNextHoursAdapter(
    private val weatherInfo: List<WeatherInfo>
) : RecyclerView.Adapter<WeatherNextHoursAdapter.WeatherHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WeatherHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.weather_next_hours,  parent,false)
        return WeatherHolder(v)
    }

    override fun onBindViewHolder(holder: WeatherHolder, position: Int) {
        holder.temperature.text = "${Util.getAndFormatTemperature(weatherInfo, position )}°C"
        holder.temperatureIcon.setImageResource(Util.getIconFromCode(Util.getIconCode(weatherInfo, position)))
        holder.time.text = Util.getHourFromDate(Util.fromEpochToDate(weatherInfo[position].date))
    }

    override fun getItemCount() = weatherInfo.size

    class WeatherHolder(v: View) : RecyclerView.ViewHolder(v) {
        private val view = v
        var temperature = view.findViewById<TextView>(R.id.temperature)!!
        var temperatureIcon = view.findViewById<ImageView>(R.id.temperatureIcon)!!
        var time = view.findViewById<TextView>(R.id.time)!!
    }
}