package ar.unlam.nohaapp.data.network

import ar.unlam.nohaapp.data.model.Weather
import ar.unlam.nohaapp.data.model.WeatherMain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit

class  API(private val retrofit: Retrofit) {
    suspend fun getAPI(): Weather {
        return withContext(Dispatchers.IO){
            val response = retrofit.create(OpenWeatherApi::class.java).getWeather()
            response.body() ?: Weather(emptyList(), WeatherMain(""))
        }
    }
}