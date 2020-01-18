package com.example.cufinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.MutableLiveData
import com.example.cufinal.dto.weather.WeatherResponse
import com.example.cufinal.services.ApiInterface
import com.example.cufinal.services.ApiUtils
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

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
            }
        })
    }
}
