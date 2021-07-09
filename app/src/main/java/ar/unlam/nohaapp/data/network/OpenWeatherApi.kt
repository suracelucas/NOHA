package ar.unlam.nohaapp.data.network

import ar.unlam.nohaapp.data.model.Weather
import retrofit2.Response
import retrofit2.http.GET

interface OpenWeatherApi {
    @GET("data/2.5/weather?id=3435907&lang=sp&units=metric&appid=b68945516e0312ab8f0a46e7c6ce0508")
    suspend fun getWeather(): Response<Weather>

}