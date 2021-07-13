package ar.unlam.nohaapp.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import ar.unlam.nohaapp.R
import ar.unlam.nohaapp.databinding.FragmentHomeBinding
import ar.unlam.nohaapp.notificaciones.data.local.RoomNohaDB
import ar.unlam.nohaapp.ui.adapters.ItemsAdapter
import ar.unlam.nohaapp.ui.viewmodel.HomeFragmentViewModel
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

private lateinit var homeBinding: FragmentHomeBinding

class HomeFragment() : Fragment() {
    private val database : RoomNohaDB by inject()
    private val homeFragmentViewModel: HomeFragmentViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        homeBinding = FragmentHomeBinding.inflate(LayoutInflater.from(context))
        super.onCreate(savedInstanceState)
        homeFragmentViewModel.onCreate()
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
        homeFragmentViewModel.clima.observe(viewLifecycleOwner, androidx.lifecycle.Observer { clima ->
            homeBinding.temperature.text = "${clima.main?.temp}°"
            homeBinding.weatherImage.setImageResource(getResourceId(clima.weather[0].icon))
            homeBinding.description.text = clima.weather[0].description
        })
        homeFragmentViewModel.dia.observe(viewLifecycleOwner, androidx.lifecycle.Observer { dia ->
            homeBinding.diaSemana.text = getString(dia)
        })
        homeFragmentViewModel.listaActividades.observe(viewLifecycleOwner, Observer {
            actividades->
            if (actividades.isEmpty()) {
                homeBinding.noData.visibility = View.VISIBLE
                homeBinding.novedades.adapter = ItemsAdapter(emptyList())
                Toast.makeText(
                    requireContext(),
                    "ACTIVA LAS NOTIFICACIONES PARA\nVER LAS ACTIVIDADES DEL DIA",
                    Toast.LENGTH_LONG,
                ).show()
            } else {
                homeBinding.noData.visibility = View.INVISIBLE
                homeBinding.novedades.adapter = ItemsAdapter(actividades)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
    }
}