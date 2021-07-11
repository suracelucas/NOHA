package ar.unlam.nohaapp.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ar.unlam.nohaapp.R
import ar.unlam.nohaapp.data.Datasource
import ar.unlam.nohaapp.databinding.FragmentHomeBinding
import ar.unlam.nohaapp.ui.adapters.ItemsAdapter
import ar.unlam.nohaapp.ui.viewmodel.HomeFragmentViewModel

private lateinit var homeBinding: FragmentHomeBinding

class HomeFragment : Fragment() {
    private val homeFragmentViewModel : HomeFragmentViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        homeBinding = FragmentHomeBinding.inflate(LayoutInflater.from(context))
        super.onCreate(savedInstanceState)
        homeFragmentViewModel.onCreate()
        setUpView()
    }

    private fun getResourceId(name:String):Int{
        return when(name){
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
        homeFragmentViewModel.clima.observe(this, androidx.lifecycle.Observer { clima->
            homeBinding.temperature.text = "${clima.main?.temp}°"
            homeBinding.weatherImage.setImageResource(getResourceId(clima.weather!![0].icon))
            homeBinding.description.text = clima.weather!![0].description
        })
        homeFragmentViewModel.dia.observe(this, androidx.lifecycle.Observer { dia->
            homeBinding.diaSemana.text = getString(dia)
        })
        if(!homeFragmentViewModel.conContenido){
            val myDataSet = Datasource().loadAffirmations()
            homeFragmentViewModel.actividades = ItemsAdapter(myDataSet)
            homeFragmentViewModel.conContenido = true
        }
        setContenido()
    }

    private fun setContenido(){
       //homeBinding.diaSemana.text = homeFragmentViewModel.dia
        homeBinding.novedades.adapter = homeFragmentViewModel.actividades
        /*
        Esto es lo que había antes en el if de searchWeather(), con esto la aplicación
        anda pero los datos del clima no se guardan en el viewModel
        * homeBinding.weatherImage.setImageResource(getResourceId(this.weather[0].icon))
                        homeBinding.description.text = this.weather[0].description
                        homeBinding.temperature.text = "${this.main.temp}°"
        * */
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return homeBinding.root
    }
}