package ar.unlam.nohaapp.notificaciones.data

import android.content.Context
import androidx.room.Room

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