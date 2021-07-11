package ar.unlam.nohaapp.notificaciones.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class LugaresConActividadesEntity(
    @Embedded
    val lugar: LugarEntity,

    @Relation(parentColumn = "idLugar", entityColumn = "idLugar")
    val actividades: List<ActividadEntity>
)