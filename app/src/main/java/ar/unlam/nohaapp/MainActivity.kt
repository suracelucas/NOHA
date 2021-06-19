package ar.unlam.nohaapp

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import ar.unlam.nohaapp.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding  = ActivityMainBinding.inflate(LayoutInflater.from(this))
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.diaSemana.text = ""
        if(binding.diaSemana.text == "") {
            binding.diaSemana.text = getString(getDay())
        }
        setUpView()
    }

    private fun setUpView() {
        val myDataSet = Datasource().loadAffirmations()
        binding.novedades.adapter = ItemsAdapter(this, myDataSet)
    }

    fun getDay():Int{
        return when(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)){
            1->R.string.domingo
            2->R.string.lunes
            3->R.string.martes
            4->R.string.miercoles
            5->R.string.jueves
            6->R.string.viernes
            else-> R.string.sabado
        }
    }
}