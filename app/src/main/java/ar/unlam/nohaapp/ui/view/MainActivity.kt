package ar.unlam.nohaapp.ui.view

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import ar.unlam.nohaapp.R
import ar.unlam.nohaapp.databinding.ActivityMainBinding
import ar.unlam.nohaapp.notificaciones.data.local.RoomNohaDB
import ar.unlam.nohaapp.notificaciones.data.model.ActividadEntity
import ar.unlam.nohaapp.notificaciones.data.model.LugarEntity
import ar.unlam.nohaapp.notificaciones.iu.fragments.NotificationFragment
import com.google.android.gms.location.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val database: RoomNohaDB by inject()

    //Asignar valor en el OnCreate
    private lateinit var administradorDeSensorGPS: FusedLocationProviderClient

    //Valores de ubicación, hay que pasarlos a la API y ver si funciona todo o se rompe xD
    private var longitud = 0.0
    private var latitud = 0.0
    private val CAMERA_REQUEST_CODE = 0
    private val GPS_REQUEST_CODE = 10
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        val sharedPreferences = getSharedPreferences("my_settings", Context.MODE_PRIVATE)
        if (sharedPreferences.getBoolean("crearDB", true)) {
            resetDatabase()
            sharedPreferences.edit().putBoolean("crearDB", false).commit()
        }
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        administradorDeSensorGPS = LocationServices.getFusedLocationProviderClient(this)

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


        //Se ejecuta la acción al hacer click en el botón de camara, el botón todavía no está hecho.
        //binding.btnCamera.setOnClickListener { checkCameraPermission() }
        //Pide permiso de GPS siempre que se abre la aplicación
        checkGPSPermission()
        //Pide la ubicación cada vez que arranca la aplicación
        pedirUbicacionGPS()
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

    //Función que ejecuta el botón de la camara para los permisos.
    private fun checkCameraPermission() {
        //ContextCompat.checkSelfPermission verifica si un permiso está aceptado o no
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED
        ) {
            //El permiso no está aceptado, así que hay que comprobar si el permiso no fue pedido antes y rechazado.
            requestCameraPermission()
        } else {
            //El permiso está aceptado.
        }
    }

    //No sé si hay forma de no volver a pedir todo de nuevo y hacerlo más simple
    //Función que se ejecuta al abrir la aplicación.
    private fun checkGPSPermission() {
        //ContextCompat.checkSelfPermission verifica si un permiso está aceptado o no
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            //El permiso no está aceptado, así que hay que comprobar si el permiso no fue pedido antes y rechazado.
            requestGPSPermission()
        } else {
            //El permiso está aceptado.
        }
    }

    private fun requestGPSPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        ) {
            //Ya se rechazo el permiso anteriormente, debe irse a ajustes.
            Toast.makeText(this, "Ir a ajustes para proporcionar permisos.", Toast.LENGTH_SHORT)
                .show()
        } else {
            //Nunca acepto ni rechazo, pedimos el permiso con la función requestPermissions
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), GPS_REQUEST_CODE
            )
        }
    }

    private fun requestCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.CAMERA
            )
        ) {
            //Ya se rechazo el permiso anteriormente, debe irse a ajustes.
            Toast.makeText(this, "Ir a ajustes para proporcionar permisos.", Toast.LENGTH_SHORT)
                .show()
        } else {
            //Nunca acepto ni rechazo, pedimos el permiso con la función requestPermissions
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA), CAMERA_REQUEST_CODE
            )
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        //Hacemos un when por si tenemos que pedir más permisos
        when (requestCode) {
            CAMERA_REQUEST_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                ) {
                    //Se acepto el permiso, podemos lanzar la funcionalidad desde acá.
                } else {
                    //Se rechazo el permiso, podemos desactivar la funcionalidad o mostrar un dialogo.
                    Toast.makeText(this, "No se puede acceder a la camara", Toast.LENGTH_SHORT)
                }
                return
            }
            GPS_REQUEST_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                ) {
                    //Se acepto el permiso, podemos lanzar la funcionalidad desde acá.
                    pedirUbicacionGPS()
                } else {
                    //Se rechazo el permiso, podemos desactivar la funcionalidad o mostrar un dialogo.
                    Toast.makeText(
                        this,
                        "No se podrá dar información de temperatura de su ciudad.",
                        Toast.LENGTH_SHORT
                    )
                }
                return
            }
            else -> {
                // Por si sale algún otro permiso.
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    //Se supone que estoy pidiendo la ubicación, pero no sé si está bien hecho, tampoco la estoy usando
    // ni llevando a ningún lado todavía.
    //PD: No sé porque me pide que verifique el permiso, si lo estoy llamando después de verificar.

    private fun pedirUbicacionGPS() {
        //Pedir permiso por si fue cancelado con anterioridad
        checkGPSPermission()
        administradorDeSensorGPS.lastLocation.addOnSuccessListener {
            latitud = it.latitude
            longitud = it.longitude
        }
    }

}