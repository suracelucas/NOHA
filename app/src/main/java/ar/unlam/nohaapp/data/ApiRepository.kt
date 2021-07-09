package ar.unlam.nohaapp.data

import ar.unlam.nohaapp.data.network.API
import ar.unlam.nohaapp.data.model.Weather
import ar.unlam.nohaapp.data.model.WeatherProvider

class ApiRepository {
    private val api = API()

    suspend fun getWeather(): Weather {
        val response = api.getAPI()
        WeatherProvider.weather = response
        return response
    }
}