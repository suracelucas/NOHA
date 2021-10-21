package ar.unlam.nohaapp.data.network

import android.util.Log
import ar.unlam.nohaapp.data.model.Weather
import ar.unlam.nohaapp.data.model.WeatherMain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit

class  API(private val retrofit: Retrofit) {
    suspend fun getAPI(latitud:Double, longitud: Double): Weather {
        return withContext(Dispatchers.IO){
            val response = retrofit.create(OpenWeatherApi::class.java).getWeather(
                latitud, longitud
            )
            Log.i("Localizacion", "Latitud: $latitud , Longitud: $longitud")
            response.body() ?: Weather(emptyList(), WeatherMain(""))
        }
    }
}