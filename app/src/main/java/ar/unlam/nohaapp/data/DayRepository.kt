package ar.unlam.nohaapp.data

import ar.unlam.nohaapp.R
import java.util.*

class DayRepository {

    private var dia = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
    private var diaAnterior = dia

    fun getDay(): Int {
        return when (dia) {
            1 -> R.string.domingo
            2 -> R.string.lunes
            3 -> R.string.martes
            4 -> R.string.miercoles
            5 -> R.string.jueves
            6 -> R.string.viernes
            else -> R.string.sabado
        }
    }
}