package ar.unlam.nohaapp.notificaciones.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ar.unlam.nohaapp.notificaciones.data.model.ActividadEntity

@Dao
interface ActividadDao {
    @Insert
    fun save(entity: ActividadEntity)

    @Query("SELECT * FROM actividades")
    fun getAll(): List<ActividadEntity>

    @Query("SELECT * FROM actividades WHERE id = :id")
    fun getActividadById(id: Short): List<ActividadEntity>

    @Query("SELECT nombreActividad FROM actividades WHERE id = :id")
    fun getNombreActividadById(id: Short): String

    @Query("SELECT dia FROM actividades WHERE id = :id")
    fun getDiaActividadById(id: Short): Short

    @Query("SELECT * FROM actividades WHERE dia = :dia")
    fun getActividadByDia(dia: Short): List<ActividadEntity>

    @Query("SELECT notificar FROM actividades WHERE nombreActividad = :nombreActividad")
    fun getNotificarByName(nombreActividad: String): Boolean

    @Query("UPDATE actividades SET notificar = :value WHERE nombreActividad = :nombreActividad")
    fun setNotificarByID(nombreActividad: String, value: Short)

    @Query("SELECT * FROM actividades WHERE dia = :dia AND notificar = 1")
    fun getActividadesByDiaYNotificar(dia: Short): List<ActividadEntity>
}