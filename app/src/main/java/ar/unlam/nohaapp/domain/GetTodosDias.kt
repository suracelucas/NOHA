package ar.unlam.nohaapp.domain

import ar.unlam.nohaapp.data.ActivitiesRepository
import ar.unlam.nohaapp.notificaciones.data.model.ActividadEntity

class GetTodosDias(private val repository: ActivitiesRepository){
    operator fun invoke(): List<ActividadEntity> = repository.getActividades()
}

