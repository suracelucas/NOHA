package ar.unlam.nohaapp.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import ar.unlam.nohaapp.R
import ar.unlam.nohaapp.databinding.FragmentHomeBinding
import ar.unlam.nohaapp.notificaciones.data.local.RoomNohaDB
import ar.unlam.nohaapp.notificaciones.data.model.ActividadEntity
import ar.unlam.nohaapp.notificaciones.data.model.DatabaseProvider
import ar.unlam.nohaapp.ui.adapters.ItemsAdapter
import ar.unlam.nohaapp.ui.viewmodel.HomeFragmentViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

private lateinit var homeBinding: FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var database: RoomNohaDB
    private val homeFragmentViewModel: HomeFragmentViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        homeBinding = FragmentHomeBinding.inflate(LayoutInflater.from(context))
        val provider = DatabaseProvider()
        database = provider.getInstanceDatabase(requireContext())
        super.onCreate(savedInstanceState)
        homeFragmentViewModel.onCreate()
        setUpView()
    }

    private fun getResourceId(name: String): Int {
        return when (name) {
            "50d" -> R.drawable._0d
            "50n" -> R.drawable._0n
            "01n" -> R.drawable._1n
            "02n" -> R.drawable._2n
            "03n" -> R.drawable._3n
            "04n" -> R.drawable._4n
            "09n" -> R.drawable._9n
            "01d" -> R.drawable._1d
            "02d" -> R.drawable._2d
            "03d" -> R.drawable._3d
            "04d" -> R.drawable._4d
            else -> R.drawable._9d
        }
    }

    private fun setUpView() {
        homeFragmentViewModel.clima.observe(this, androidx.lifecycle.Observer { clima ->
            homeBinding.temperature.text = "${clima.main?.temp}°"
            homeBinding.weatherImage.setImageResource(getResourceId(clima.weather!![0].icon))
            homeBinding.description.text = clima.weather!![0].description
        })
        homeFragmentViewModel.dia.observe(this, androidx.lifecycle.Observer { dia ->
            homeBinding.diaSemana.text = getString(dia)
        })
        val myDataSet = filtroDias()
        if (myDataSet.isEmpty()) {
            homeBinding.noData.visibility = View.VISIBLE
            Toast.makeText(
                requireContext(),
                "ACTIVA LAS NOTIFICACIONES PARA\nVER LAS ACTIVIDADES DEL DIA",
                Toast.LENGTH_LONG,
            ).show()
        } else {
            homeBinding.novedades.adapter = ItemsAdapter(myDataSet)
        }

        //setContenido()
    }

    private fun setContenido() {
        //homeBinding.diaSemana.text = homeFragmentViewModel.dia
        //homeBinding.novedades.adapter = homeFragmentViewModel.actividades
        /*
        Esto es lo que había antes en el if de searchWeather(), con esto la aplicación
        anda pero los datos del clima no se guardan en el viewModel
        * homeBinding.weatherImage.setImageResource(getResourceId(this.weather[0].icon))
                        homeBinding.description.text = this.weather[0].description
                        homeBinding.temperature.text = "${this.main.temp}°"
        * */
    }

    private fun filtroDias(): List<ActividadEntity> {
        val todosLosDias = database.actividadDao().getActividadesByDiaYNotificar(8)
        val diasDeSemana: List<ActividadEntity>
        val lista: MutableList<ActividadEntity> = mutableListOf()
        val dia = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
        lista.addAll(todosLosDias)
        if (dia == 2 || dia == 3 || dia == 4 ||
            dia == 5 || dia == 6
        ) {
            diasDeSemana = database.actividadDao().getActividadesByDiaYNotificar(9)
            lista.addAll(diasDeSemana)
        }
        when (dia) {
            1 -> {
                val domingo = database.actividadDao().getActividadesByDiaYNotificar(1)
                lista.addAll(domingo)
                return lista
            }
            2 -> {
                val lunes = database.actividadDao().getActividadesByDiaYNotificar(2)
                lista.addAll(lunes)
                return lista
            }
            3 -> {
                val martes = database.actividadDao().getActividadesByDiaYNotificar(3)
                lista.addAll(martes)
                return lista
            }
            4 -> {
                val miercoles = database.actividadDao().getActividadesByDiaYNotificar(4)
                lista.addAll(miercoles)
                return lista
            }
            5 -> {
                val jueves = database.actividadDao().getActividadesByDiaYNotificar(5)
                lista.addAll(jueves)
                return lista
            }
            6 -> {
                val viernes = database.actividadDao().getActividadesByDiaYNotificar(6)
                lista.addAll(viernes)
                return lista
            }
            7 -> {
                val sabado = database.actividadDao().getActividadesByDiaYNotificar(7)
                lista.addAll(sabado)
                return lista
            }
        }
        return lista
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return homeBinding.root
    }
}