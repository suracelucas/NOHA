package ar.unlam.nohaapp.notificaciones.data

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface LugaresConActividadesDao {
    @Transaction
    @Query("SELECT * FROM lugares WHERE idLugar IN (SELECT idLugar FROM actividades WHERE idLugar = lugares.idLugar)")
    fun getAll(): List<LugaresConActividadesEntity>

    @Transaction
    @Query("SELECT * FROM lugares WHERE idLugar = :idLugar")
    fun getActividadEnLugarByID(idLugar: Short): List<LugaresConActividadesEntity>

    @Transaction
    @Query("SELECT * FROM lugares WHERE idLugar IN (SELECT notificar FROM actividades WHERE idLugar = :idLugar AND id = :idActividad)")
    fun getNotificarByID(idLugar: Short, idActividad: Short): Boolean
}