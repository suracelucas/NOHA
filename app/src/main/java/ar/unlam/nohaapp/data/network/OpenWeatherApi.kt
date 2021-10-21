package ar.unlam.nohaapp.data.network

import ar.unlam.nohaapp.BuildConfig
import ar.unlam.nohaapp.data.model.Weather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface OpenWeatherApi {
    @GET("data/2.5/weather")
    suspend fun getWeather(@Query("lat") latitud : Double, @Query("lon") longitud : Double,
                           @Query("lang") langueage : String = "sp",
                           @Query("units") units : String = "metric",
                           @Query("appid") apiKey : String = BuildConfig.PRIVATE_KEY): Response<Weather>
}