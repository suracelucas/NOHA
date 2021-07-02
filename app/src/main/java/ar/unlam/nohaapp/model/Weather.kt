package ar.unlam.nohaapp.model

data class Weather (
    var weather: List<WeatherInfo>,
    var main: WeatherMain
    )

data class WeatherInfo(
    val description: String,
    val icon:String,
)

data class WeatherMain(
    val temp:String
)
