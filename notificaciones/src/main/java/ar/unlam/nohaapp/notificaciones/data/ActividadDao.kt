package ar.unlam.nohaapp.notificaciones.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ActividadDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(entity: ActividadEntity)

    @Query("SELECT * FROM actividades")
    fun getAll(): LiveData<List<ActividadEntity>>

    @Query("SELECT * FROM actividades WHERE id = :id")
    fun getActividadById(id: Short): LiveData<List<ActividadEntity>>

    @Query("SELECT nombreActividad FROM actividades WHERE id = :id")
    fun getNombreActividadById(id: Short): String

    @Query("SELECT dia FROM actividades WHERE id = :id")
    fun getDiaActividadById(id: Short): Short

    @Query("SELECT * FROM actividades WHERE dia = :dia")
    fun getActividadByDia(dia: Short): LiveData<List<ActividadEntity>>
}