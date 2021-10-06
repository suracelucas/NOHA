package ar.unlam.nohaapp.ui.view

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.camera2.internal.annotation.CameraExecutor
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import ar.unlam.nohaapp.R
import ar.unlam.nohaapp.databinding.ActivityMainBinding
import ar.unlam.nohaapp.notificaciones.data.local.RoomNohaDB
import ar.unlam.nohaapp.notificaciones.data.model.ActividadEntity
import ar.unlam.nohaapp.notificaciones.data.model.LugarEntity
import ar.unlam.nohaapp.notificaciones.iu.fragments.NotificationFragment
import kotlinx.android.synthetic.main.fragment_camera.*
import org.koin.android.ext.android.inject
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val database: RoomNohaDB by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        val sharedPreferences = getSharedPreferences("my_settings", Context.MODE_PRIVATE)
        if(sharedPreferences.getBoolean("crearDB", true)){
            resetDatabase()
            sharedPreferences.edit().putBoolean("crearDB", false).commit()
        }
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val homeFragment = HomeFragment()
        val notificationFragment = NotificationFragment()
        val cameraFragment = CameraFragment(this)

        makeCurrentFragment(homeFragment)

        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.Home -> makeCurrentFragment(homeFragment)
                R.id.notificaciones -> makeCurrentFragment(notificationFragment)
                R.id.camara -> makeCurrentFragment(cameraFragment)
            }
            true
        }


    }


    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }



    private fun resetDatabase() {
        database.clearAllTables()
        insertLugarData()
        insertActividadData()
    }

    private fun insertLugarData() {
        database.lugarDao()
            .save(LugarEntity(1, "Buffet", R.drawable.image_buffet))
        database.lugarDao()
            .save(LugarEntity(2, "Salón", R.drawable.image_salon))
        database.lugarDao()
            .save(LugarEntity(3, "Gimnasio", R.drawable.image_gimnasio))
        database.lugarDao()
            .save(LugarEntity(4, "Natatorio", R.drawable.image_natatorio))
        database.lugarDao()
            .save(LugarEntity(5, "Spa", R.drawable.image_spa))
    }

    private fun insertActividadData() {
        database.actividadDao().save(
            ActividadEntity(
                1,
                "Desayuno",
                "07:00 - 10:00",
                false,
                8,
                1
            )
        )
        database.actividadDao().save(
            ActividadEntity(
                2,
                "Almuerzo",
                "11:00 - 14:00",
                false,
                8,
                1
            )
        )
        database.actividadDao().save(
            ActividadEntity(
                3,
                "Merienda",
                "16:00 - 19:00",
                false,
                8,
                1
            )
        )
        database.actividadDao().save(
            ActividadEntity(
                4,
                "Cena",
                "20:00 - 23:00",
                false,
                8,
                1
            )
        )
        database.actividadDao().save(
            ActividadEntity(
                5,
                "Karaoke",
                "23:00 - 02:00",
                false,
                7,
                2
            )
        )
        database.actividadDao().save(
            ActividadEntity(
                6,
                "StandUp",
                "22:00 - 00:00",
                false,
                6,
                2
            )
        )
        database.actividadDao().save(
            ActividadEntity(
                7,
                "Bingo",
                "14:00 - 16:00",
                false,
                1,
                2
            )
        )
        database.actividadDao().save(
            ActividadEntity(
                8,
                "Lifting",
                "11:00 - 19:00",
                false,
                9,
                3
            )
        )
        database.actividadDao().save(
            ActividadEntity(
                9,
                "Crossfit",
                "15:00 - 16:00",
                false,
                3,
                3
            )
        )
        database.actividadDao().save(
            ActividadEntity(
                10,
                "Zumba",
                "18:00 - 19:00",
                false,
                5,
                3
            )
        )
        database.actividadDao().save(
            ActividadEntity(
                11,
                "Pileta Libre",
                "13:00 - 18:00",
                false,
                9,
                4
            )
        )
        database.actividadDao().save(
            ActividadEntity(
                12,
                "AquaGym",
                "09:00 - 11:00",
                false,
                3,
                4
            )
        )
        database.actividadDao().save(
            ActividadEntity(
                13,
                "Masajes",
                "10:00 - 12:00",
                false,
                2,
                5
            )
        )
        database.actividadDao().save(
            ActividadEntity(
                14,
                "Circuito",
                "10:00 - 11:00",
                false,
                6,
                5
            )
        )
        database.actividadDao().save(
            ActividadEntity(
                15,
                "Relajación",
                "10:00 - 11:00",
                false,
                7,
                5
            )
        )
    }


}