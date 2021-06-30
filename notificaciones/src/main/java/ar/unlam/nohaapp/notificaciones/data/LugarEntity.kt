package ar.unlam.nohaapp.notificaciones.data

import androidx.annotation.DrawableRes
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lugares")
data class LugarEntity(

    @PrimaryKey
    @ColumnInfo(name = "idLugar")
    val idLugar: Short,

    @ColumnInfo(name = "nombreLugar")
    val nombreLugar: String,

    @ColumnInfo(name = "img")
    @DrawableRes val img: Int,
)