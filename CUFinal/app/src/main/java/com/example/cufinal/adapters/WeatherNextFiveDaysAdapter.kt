package com.example.cufinal.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cufinal.R
import com.example.cufinal.dto.weather.WeatherInfo
import com.example.cufinal.utils.Util

class WeatherNextFiveDaysAdapter(
    private val day: List<WeatherInfo>,
    private val night: List<WeatherInfo>
): RecyclerView.Adapter<WeatherNextFiveDaysAdapter.WeatherForecastHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WeatherForecastHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.weather_next_days, parent, false)
        return WeatherForecastHolder(v)
    }

    override fun onBindViewHolder(holder: WeatherForecastHolder, position: Int) {
        holder.date.text = Util.getDayAndMonth(Util.fromEpochToDate(day[position].date))
        holder.dayTempIcon.setImageResource(Util.getIconFromCode(Util.getIconCode(day, position)))
        holder.dayTemp.text = "${Util.getAndFormatTemperature(day, position )}°C"
        holder.nightTempIcon.setImageResource(Util.getIconFromCode(Util.getIconCode(night, position)))
        holder.nightTemp.text = "${Util.getAndFormatTemperature(night, position )}°C"
    }

    override fun getItemCount() = day.size

    class WeatherForecastHolder(v: View) : RecyclerView.ViewHolder(v) {
        private val view= v
        val date = view.findViewById<TextView>(R.id.dateMonth)!!
        val dayTempIcon = view.findViewById<ImageView>(R.id.dayTempIcon)!!
        val dayTemp = view.findViewById<TextView>(R.id.dayTemp)!!
        val nightTempIcon = view.findViewById<ImageView>(R.id.nightTempIcon)!!
        val nightTemp = view.findViewById<TextView>(R.id.nightTemp)!!
    }
}