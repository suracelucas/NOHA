package ar.unlam.nohaapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ar.unlam.app.actividades.services.API
import ar.unlam.nohaapp.R
import ar.unlam.nohaapp.adapters.ItemsAdapter
import ar.unlam.nohaapp.data.Datasource
import ar.unlam.nohaapp.databinding.FragmentHomeBinding
import ar.unlam.nohaapp.model.Weather
import ar.unlam.nohaapp.viewmodel.HomeFragmentViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

lateinit var homeBinding: FragmentHomeBinding
lateinit var homeFragmentViewModel: HomeFragmentViewModel

class HomeFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        homeBinding = FragmentHomeBinding.inflate(LayoutInflater.from(context))
        homeFragmentViewModel = ViewModelProvider(this).get(HomeFragmentViewModel::class.java)
        super.onCreate(savedInstanceState)
        setUpView()
    }

    private fun searchWeather() {
        API().getWeather(object : Callback<Weather> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                if(response.isSuccessful){
                    response.body()!!.apply {
                        homeFragmentViewModel.climaDescription = this.weather[0].description
                        homeFragmentViewModel.climaIcon = this.weather[0].icon
                        homeFragmentViewModel.climaTemp = this.main.temp
                    }
                }else{
                    Toast.makeText(context, "Fallo con código ${response.code()}", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<Weather>, t: Throwable) {
                Log.e("HomeFragment", "Fallo al cargar el clima", t)
            }

        })
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
        if(!homeFragmentViewModel.conContenido){
            val myDataSet = Datasource().loadAffirmations()
            homeFragmentViewModel.actividades = ItemsAdapter(myDataSet)
            homeFragmentViewModel.dia = getString(getDay())
            searchWeather()
            homeFragmentViewModel.conContenido = true
        }
        setContenido()
    }

    private fun setContenido(){
        homeBinding.diaSemana.text = homeFragmentViewModel.dia
        homeBinding.novedades.adapter = homeFragmentViewModel.actividades
        homeBinding.weatherImage.setImageResource(getResourceId(homeFragmentViewModel.climaIcon))
        homeBinding.description.text = homeFragmentViewModel.climaDescription
        homeBinding.temperature.text = "${homeFragmentViewModel.climaTemp}°"
        /*
        Esto es lo que había antes en el if de searchWeather(), con esto la aplicación
        anda pero los datos del clima no se guardan en el viewModel
        * homeBinding.weatherImage.setImageResource(getResourceId(this.weather[0].icon))
                        homeBinding.description.text = this.weather[0].description
                        homeBinding.temperature.text = "${this.main.temp}°"
        * */
    }

    private fun getDay(): Int {
        return when (Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
            1 -> R.string.domingo
            2 -> R.string.lunes
            3 -> R.string.martes
            4 -> R.string.miercoles
            5 -> R.string.jueves
            6 -> R.string.viernes
            else -> R.string.sabado
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return homeBinding.root
    }
}