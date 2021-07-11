package ar.unlam.nohaapp.notificaciones.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ar.unlam.nohaapp.notificaciones.data.model.ActividadEntity
import ar.unlam.nohaapp.notificaciones.data.model.LugarEntity

@Database(
    version = 2,
    entities = [ActividadEntity::class, LugarEntity::class]
)

abstract class RoomNohaDB : RoomDatabase() {
    abstract fun actividadDao(): ActividadDao
    abstract fun lugarDao(): LugarDao
    abstract fun actividadEnLugarDao(): LugaresConActividadesDao
}