package ar.unlam.nohaapp.data.model

data class Weather (
    var weather: List<WeatherInfo>,
    var main: WeatherMain
    )

data class WeatherInfo(
    var description: String,
    var icon:String,
)

data class WeatherMain(
    var temp:String?
)
