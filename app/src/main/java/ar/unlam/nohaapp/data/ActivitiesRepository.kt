package ar.unlam.nohaapp.data

import ar.unlam.nohaapp.notificaciones.data.local.RoomNohaDB
import ar.unlam.nohaapp.notificaciones.data.model.ActividadEntity
import java.util.*

class ActivitiesRepository(private val database : RoomNohaDB) {
    fun getActividades(): List<ActividadEntity>{
        val todosLosDias = database.actividadDao().getActividadesByDiaYNotificar(8)
        val diasDeSemana: List<ActividadEntity>
        val lista: MutableList<ActividadEntity> = mutableListOf()
        val dia = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
        lista.addAll(todosLosDias)
        if (dia == 2 || dia == 3 || dia == 4 ||
            dia == 5 || dia == 6
        ) {
            diasDeSemana = database.actividadDao().getActividadesByDiaYNotificar(9)
            lista.addAll(diasDeSemana)
        }
        when (dia) {
            1 -> {
                val domingo = database.actividadDao().getActividadesByDiaYNotificar(1)
                lista.addAll(domingo)
                return lista
            }
            2 -> {
                val lunes = database.actividadDao().getActividadesByDiaYNotificar(2)
                lista.addAll(lunes)
                return lista
            }
            3 -> {
                val martes = database.actividadDao().getActividadesByDiaYNotificar(3)
                lista.addAll(martes)
                return lista
            }
            4 -> {
                val miercoles = database.actividadDao().getActividadesByDiaYNotificar(4)
                lista.addAll(miercoles)
                return lista
            }
            5 -> {
                val jueves = database.actividadDao().getActividadesByDiaYNotificar(5)
                lista.addAll(jueves)
                return lista
            }
            6 -> {
                val viernes = database.actividadDao().getActividadesByDiaYNotificar(6)
                lista.addAll(viernes)
                return lista
            }
            7 -> {
                val sabado = database.actividadDao().getActividadesByDiaYNotificar(7)
                lista.addAll(sabado)
                return lista
            }
        }
        return lista
    }
}