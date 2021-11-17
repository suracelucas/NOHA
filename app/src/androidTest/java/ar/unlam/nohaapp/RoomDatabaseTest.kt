package ar.unlam.nohaapp

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import ar.unlam.nohaapp.notificaciones.data.local.ActividadDao
import ar.unlam.nohaapp.notificaciones.data.local.LugarDao
import ar.unlam.nohaapp.notificaciones.data.local.LugaresConActividadesDao
import ar.unlam.nohaapp.notificaciones.data.local.RoomNohaDB
import ar.unlam.nohaapp.notificaciones.data.model.ActividadEntity
import ar.unlam.nohaapp.notificaciones.data.model.LugarEntity
import io.mockk.impl.annotations.MockK
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.IOException

class RoomDatabaseTest {
    private lateinit var actividadDao: ActividadDao
    private lateinit var  lugarDao: LugarDao
    private lateinit var  actividadEnLugar: LugaresConActividadesDao
    private lateinit var db: RoomNohaDB

    @MockK
    private lateinit var actividad: ActividadEntity

    @MockK
    private lateinit var lugar: LugarEntity

    @Before
    fun createDb(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, RoomNohaDB::class.java
        ).allowMainThreadQueries().build()
        actividadDao = db.actividadDao()
        lugarDao = db.lugarDao()
        actividadEnLugar = db.actividadEnLugarDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb(){
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun testQueSeAgregaUnaActividad(){
        actividad = ActividadEntity(
            1, "ActividadTest", "08:00", false, 1, 1
        )
        actividadDao.save(actividad)

        assertThat("ActividadTest", equalTo(actividadDao.getNombreActividadById(1)))
    }

    @Test
    @Throws(Exception::class)
    fun testQueSeAgregaUnLugar() {
        lugar = LugarEntity(1, "LugarTest", 1)
        lugarDao.save(lugar)

        assertThat("LugarTest", equalTo(lugarDao.getNombreLugarById(1)))
    }

    @Test
    @Throws(Exception::class)
    fun testQueMatcheeActividadEnLugar() {
        actividad = ActividadEntity(
            1, "ActividadTest", "08:00", false, 1, 1
        )
        lugar = LugarEntity(1, "LugarTest", 1)
        actividadDao.save(actividad)
        lugarDao.save(lugar)

        val actividadEnLista = actividadDao.getActividadById(1)
        actividadEnLugar.getActividadEnLugarByID(1)

        assertThat(
            actividadEnLugar.getActividadEnLugarByID(1)[0].actividades[0].id.toString(),
            equalTo(actividadEnLista[0].id.toString())
        )
    }
}