package ar.unlam.nohaapp.notificaciones.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LugarDao {
    @Insert
    fun save(entity: LugarEntity)

    @Query("SELECT * FROM lugares")
    fun getAll(): List<LugarEntity>

    @Query("SELECT nombreLugar FROM lugares WHERE idLugar = :id")
    fun getNombreLugarById(id: Short): String

    @Query("SELECT img FROM lugares WHERE idLugar = :id")
    fun getImgLugarById(id: Short): Int
}