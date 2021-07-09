package ar.unlam.nohaapp.data.model

class WeatherProvider {
    companion object{
        var weather = Weather(emptyList(), WeatherMain(""))
    }
}