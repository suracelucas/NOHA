package ar.unlam.nohaapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ar.unlam.nohaapp.Datasource
import ar.unlam.nohaapp.ItemsAdapter
import ar.unlam.nohaapp.R
import ar.unlam.nohaapp.databinding.FragmentHomeBinding
import java.util.*

lateinit var homeBinding: FragmentHomeBinding

class HomeFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        homeBinding = FragmentHomeBinding.inflate(LayoutInflater.from(context))
        super.onCreate(savedInstanceState)
        homeBinding.diaSemana.text = ""
        if(homeBinding.diaSemana.text == "") {
            homeBinding.diaSemana.text = getString(getDay())
        }
        setUpView()
    }

    private fun setUpView() {
        val myDataSet = Datasource().loadAffirmations()
        homeBinding.novedades.adapter = ItemsAdapter( myDataSet)
    }

    fun getDay():Int{
        return when(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)){
            1-> R.string.domingo
            2-> R.string.lunes
            3-> R.string.martes
            4-> R.string.miercoles
            5-> R.string.jueves
            6-> R.string.viernes
            else-> R.string.sabado
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return homeBinding.root
    }
}