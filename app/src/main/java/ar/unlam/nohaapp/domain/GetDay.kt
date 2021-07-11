package ar.unlam.nohaapp.domain

import ar.unlam.nohaapp.data.DayRepository

class GetDay (private val repository:DayRepository){
    //private val repository = DayRepository()
    operator fun invoke(): Int = repository.getDay()
}