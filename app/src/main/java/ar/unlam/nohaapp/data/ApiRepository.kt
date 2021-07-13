package ar.unlam.nohaapp.data

import ar.unlam.nohaapp.data.model.Weather
import ar.unlam.nohaapp.data.network.API

class ApiRepository(private val api: API) {
    suspend fun getWeather(): Weather {
        return api.getAPI()
    }
}