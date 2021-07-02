package ar.unlam.nohaapp.services

import ar.unlam.nohaapp.model.Weather
import com.google.gson.Gson
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class API {
    private fun getAPI(): OpenWeatherApi {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .baseUrl("https://api.openweathermap.org/")
            .build();
        return retrofit.create(OpenWeatherApi::class.java)
    }

     fun getWeather(callback: Callback<Weather>) {
        getAPI().getWeather().enqueue(callback)
    }
}