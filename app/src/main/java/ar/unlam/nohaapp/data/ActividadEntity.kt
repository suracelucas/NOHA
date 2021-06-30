package ar.unlam.nohaapp.data

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "actividades")
data class ActividadEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Short,

    @ColumnInfo(name = "nombreActividad")
    val nombreActividad: String,

    @ColumnInfo(name = "horario")
    var horario: String,

    @ColumnInfo(name = "notificar")
    var notificar: Boolean,

    @ColumnInfo(name = "dia")
    var dia: Short,

    @Embedded val lugar: LugarEntity
)