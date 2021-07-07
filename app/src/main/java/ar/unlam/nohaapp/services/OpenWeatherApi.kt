package ar.unlam.app.actividades.services

import ar.unlam.nohaapp.model.Weather
import retrofit2.Call
import retrofit2.http.GET

interface OpenWeatherApi {
    @GET("data/2.5/weather?id=3435907&lang=sp&units=metric&appid=b68945516e0312ab8f0a46e7c6ce0508")
    fun getWeather(): Call<Weather>

}