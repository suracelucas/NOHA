package ar.unlam.nohaapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import ar.unlam.nohaapp.Datasource
import ar.unlam.nohaapp.ItemsAdapter
import ar.unlam.nohaapp.R
import ar.unlam.nohaapp.databinding.FragmentHomeBinding
import ar.unlam.nohaapp.model.Weather
import ar.unlam.nohaapp.services.API
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

lateinit var homeBinding: FragmentHomeBinding

class HomeFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        homeBinding = FragmentHomeBinding.inflate(LayoutInflater.from(context))
        super.onCreate(savedInstanceState)
        homeBinding.diaSemana.text = ""
        if (homeBinding.diaSemana.text == "") {
            homeBinding.diaSemana.text = getString(getDay())
        }
        setUpView()
        searchWeather()
    }

    private fun searchWeather() {
        API().getWeather(object : Callback<Weather> {
            override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                if(response.isSuccessful){
                    response.body()!!.apply {
                        homeBinding.weatherImage.setImageResource(getResourceId(this.weather[0].icon))
                        homeBinding.description.text = this.weather[0].description
                        homeBinding.temperature.text = "${this.main.temp}°"
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
        val myDataSet = Datasource().loadAffirmations()
        homeBinding.novedades.adapter = ItemsAdapter(myDataSet)
    }

    fun getDay(): Int {
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