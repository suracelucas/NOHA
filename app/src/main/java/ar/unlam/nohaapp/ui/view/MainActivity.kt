package ar.unlam.nohaapp.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.room.Room
import ar.unlam.nohaapp.R
import ar.unlam.nohaapp.databinding.ActivityMainBinding
import ar.unlam.nohaapp.notificaciones.data.ActividadEntity
import ar.unlam.nohaapp.notificaciones.data.LugarEntity
import ar.unlam.nohaapp.notificaciones.data.RoomNohaDB
import ar.unlam.nohaapp.notificaciones.fragments.NotificationFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var database: RoomNohaDB
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val homeFragment = HomeFragment()
        val notificationFragment = NotificationFragment()

        makeCurrentFragment(homeFragment)

        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.Home -> makeCurrentFragment(homeFragment)
                R.id.notificaciones -> makeCurrentFragment(notificationFragment)
            }
            true
        }

        createDatabase()
        insertLugarData()
        insertActividadData()
    }

    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }

    private fun createDatabase() {
        database = Room.databaseBuilder(
            this,
            RoomNohaDB::class.java,
            "noha_db"
        ).allowMainThreadQueries().build()
    }

    private fun insertLugarData() {
        database.lugarDao().save(LugarEntity(1, "Buffet", R.drawable.image_buffet))
        database.lugarDao().save(LugarEntity(2, "Salón", R.drawable.image_recreacion))
        database.lugarDao().save(LugarEntity(3, "Gimnasio", R.drawable.image_gimnasio))
        database.lugarDao().save(LugarEntity(4, "Natatorio", R.drawable.image_pileta))
        database.lugarDao().save(LugarEntity(5, "Spa", R.drawable.image_masajes))
    }

    private fun insertActividadData() {
        val lugar = database.lugarDao()
        database.actividadDao().save(
            ActividadEntity(
                1,
                "Desayuno",
                "07:00 - 10:00",
                false,
                8,
                LugarEntity(
                    1, lugar.getNombreLugarById(1), lugar.getImgLugarById(1)
                )
            )
        )
        database.actividadDao().save(
            ActividadEntity(
                2,
                "Almuerzo",
                "07:00 - 10:00",
                false,
                8,
                LugarEntity(
                    1, lugar.getNombreLugarById(1), lugar.getImgLugarById(1)
                )
            )
        )
        database.actividadDao().save(
            ActividadEntity(
                3,
                "Merienda",
                "07:00 - 10:00",
                false,
                8,
                LugarEntity(
                    1, lugar.getNombreLugarById(1), lugar.getImgLugarById(1)
                )
            )
        )
        database.actividadDao().save(
            ActividadEntity(
                4,
                "Cena",
                "07:00 - 10:00",
                false,
                8,
                LugarEntity(
                    1, lugar.getNombreLugarById(1), lugar.getImgLugarById(1)
                )
            )
        )
        database.actividadDao().save(
            ActividadEntity(
                5,
                "Karaoke",
                "07:00 - 10:00",
                false,
                8,
                LugarEntity(
                    2, lugar.getNombreLugarById(2), lugar.getImgLugarById(2)
                )
            )
        )
        database.actividadDao().save(
            ActividadEntity(
                6,
                "StandUp",
                "07:00 - 10:00",
                false,
                8,
                LugarEntity(
                    2, lugar.getNombreLugarById(2), lugar.getImgLugarById(2)
                )
            )
        )
        database.actividadDao().save(
            ActividadEntity(
                7,
                "Bingo",
                "07:00 - 10:00",
                false,
                8,
                LugarEntity(
                    2, lugar.getNombreLugarById(2), lugar.getImgLugarById(2)
                )
            )
        )
        database.actividadDao().save(
            ActividadEntity(
                8,
                "Lifting",
                "07:00 - 10:00",
                false,
                8,
                LugarEntity(
                    3, lugar.getNombreLugarById(3), lugar.getImgLugarById(3)
                )
            )
        )
        database.actividadDao().save(
            ActividadEntity(
                9,
                "Crossfit",
                "07:00 - 10:00",
                false,
                8,
                LugarEntity(
                    3, lugar.getNombreLugarById(3), lugar.getImgLugarById(3)
                )
            )
        )
        database.actividadDao().save(
            ActividadEntity(
                10,
                "Zumba",
                "07:00 - 10:00",
                false,
                8,
                LugarEntity(
                    3, lugar.getNombreLugarById(3), lugar.getImgLugarById(3)
                )
            )
        )
        database.actividadDao().save(
            ActividadEntity(
                11,
                "Pileta Libre",
                "07:00 - 10:00",
                false,
                8,
                LugarEntity(
                    4, lugar.getNombreLugarById(4), lugar.getImgLugarById(4)
                )
            )
        )
        database.actividadDao().save(
            ActividadEntity(
                12,
                "AquaGym",
                "07:00 - 10:00",
                false,
                8,
                LugarEntity(
                    4, lugar.getNombreLugarById(4), lugar.getImgLugarById(4)
                )
            )
        )
        database.actividadDao().save(
            ActividadEntity(
                13,
                "Masajes",
                "07:00 - 10:00",
                false,
                8,
                LugarEntity(
                    5, lugar.getNombreLugarById(5), lugar.getImgLugarById(5)
                )
            )
        )
        database.actividadDao().save(
            ActividadEntity(
                14,
                "Circuito",
                "07:00 - 10:00",
                false,
                8,
                LugarEntity(
                    5, lugar.getNombreLugarById(5), lugar.getImgLugarById(5)
                )
            )
        )
        database.actividadDao().save(
            ActividadEntity(
                15,
                "Relajación",
                "07:00 - 10:00",
                false,
                8,
                LugarEntity(
                    5, lugar.getNombreLugarById(5), lugar.getImgLugarById(5)
                )
            )
        )
    }
}