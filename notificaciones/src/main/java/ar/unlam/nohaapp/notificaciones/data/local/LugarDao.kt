package ar.unlam.nohaapp.notificaciones.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ar.unlam.nohaapp.notificaciones.data.model.LugarEntity

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