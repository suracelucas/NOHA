package ar.unlam.nohaapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [ActividadEntity::class, LugarEntity::class]
)

abstract class RoomNohaDB : RoomDatabase() {
    abstract fun actividadDao(): ActividadDao
    abstract fun lugarDao(): LugarDao
}