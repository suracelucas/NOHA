package ar.unlam.nohaapp.domain

import ar.unlam.nohaapp.data.ApiRepository
import ar.unlam.nohaapp.data.model.Weather

class GetWeather(private val repository: ApiRepository) {
    suspend operator fun invoke():Weather = repository.getWeather()
}