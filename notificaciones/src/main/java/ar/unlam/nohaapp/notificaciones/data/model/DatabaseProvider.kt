package ar.unlam.nohaapp.notificaciones.data.model

import android.content.Context
import androidx.room.Room
import ar.unlam.nohaapp.notificaciones.data.local.RoomNohaDB

class DatabaseProvider {
    fun getInstanceDatabase(context: Context): RoomNohaDB {
        return Room.databaseBuilder(
            context,
            RoomNohaDB::class.java,
            "noha_db"
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }
}