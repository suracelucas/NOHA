package ar.unlam.nohaapp

import androidx.test.espresso.matcher.ViewMatchers.assertThat
import ar.unlam.nohaapp.core.RetrofitHelper
import ar.unlam.nohaapp.data.ApiRepository
import ar.unlam.nohaapp.data.model.Weather
import ar.unlam.nohaapp.data.model.WeatherInfo
import ar.unlam.nohaapp.data.model.WeatherMain
import ar.unlam.nohaapp.data.network.API
import ar.unlam.nohaapp.data.network.OpenWeatherApi
import ar.unlam.nohaapp.domain.GetWeather
import org.hamcrest.CoreMatchers.equalTo
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Test
import retrofit2.Response

class ApiCallTest {

    @Test
    fun testQueDevuelvaLaTemperatura(){
        val retrofit = RetrofitHelper.getRetrofit()

        val list:MutableList<WeatherInfo> = mutableListOf()
        val info = WeatherInfo("nublado", "ds02")
        list.add(info)
        val givenWeather = Weather(list, WeatherMain("28"))
        val tiempo = mockk<OpenWeatherApi>(relaxed = true)
        coEvery { tiempo.getWeather(any(), any()) } returns Response.success(givenWeather)
        //val prueba = runBlocking { API(retrofit).getAPI(-56.00, 65.00) }
        //val sut = GetWeather(ApiRepository(API(retrofit)))
        //runBlocking { sut.invoke(-56.00, 65.00) }
        val resultado = runBlocking { tiempo.getWeather(-56.00, 65.00) }
        assertThat(givenWeather, equalTo(resultado.body()))

    }
}