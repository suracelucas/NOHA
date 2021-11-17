package ar.unlam.nohaapp

import ar.unlam.nohaapp.data.ActivitiesRepository
import ar.unlam.nohaapp.data.DayRepository
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testQueSeObtengaElDia(){
        val dia = DayRepository(4).getDay()
        assertEquals(dia, R.string.miercoles)
    }

    @Test
    fun testObtenerDiaInvalido(){
        val dia = DayRepository(78).getDay()
        assertEquals(dia, R.string.sabado)
    }

}