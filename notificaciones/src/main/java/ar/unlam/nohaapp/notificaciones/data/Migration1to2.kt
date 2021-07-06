package ar.unlam.nohaapp.notificaciones.data

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class Migration1to2 : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Horarios buffet
        database.execSQL("UPDATE actividades SET horario = '11:00 - 14:00' WHERE nombreActividad == 'Almuerzo'")
        database.execSQL("UPDATE actividades SET horario = '16:00 - 19:00' WHERE nombreActividad == 'Merienda'")
        database.execSQL("UPDATE actividades SET horario = '20:00 - 23:00' WHERE nombreActividad == 'Cena'")

        // Días salón
        database.execSQL("UPDATE actividades SET dia = 7 WHERE nombreActividad == 'Karaoke'")
        database.execSQL("UPDATE actividades SET dia = 6 WHERE nombreActividad == 'StandUp'")
        database.execSQL("UPDATE actividades SET dia = 1 WHERE nombreActividad == 'Bingo'")

        // Días gym
        database.execSQL("UPDATE actividades SET dia = 9 WHERE nombreActividad == 'Lifting'")
        database.execSQL("UPDATE actividades SET dia = 3 WHERE nombreActividad == 'Crossfit'")
        database.execSQL("UPDATE actividades SET dia = 5 WHERE nombreActividad == 'Zumba'")

        // Días natatorio
        database.execSQL("UPDATE actividades SET dia = 9 WHERE nombreActividad == 'Pileta Libre'")
        database.execSQL("UPDATE actividades SET dia = 3 WHERE nombreActividad == 'AquaGym'")

        // Días spa
        database.execSQL("UPDATE actividades SET dia = 2 WHERE nombreActividad == 'Masajes'")
        database.execSQL("UPDATE actividades SET dia = 6 WHERE nombreActividad == 'Circuito'")
        database.execSQL("UPDATE actividades SET dia = 7 WHERE nombreActividad == 'Relajación'")
    }
}