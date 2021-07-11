package ar.unlam.nohaapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.unlam.nohaapp.data.model.Weather
import ar.unlam.nohaapp.domain.GetDay
import ar.unlam.nohaapp.domain.GetWeather
import ar.unlam.nohaapp.ui.adapters.ItemsAdapter
import kotlinx.coroutines.launch

class HomeFragmentViewModel : ViewModel() {
    val clima = MutableLiveData<Weather>()
    val dia : MutableLiveData<Int> by lazy{
        MutableLiveData<Int>()
    }
    var getWeather = GetWeather()
    var getDay = GetDay()
    fun onCreate(){
      viewModelScope.launch {
         val result = getWeather()
         clima.postValue(result)
      }
        val dayResult = getDay()
        dia.postValue(dayResult)
    }




    lateinit var actividades : ItemsAdapter

    var conContenido = false;
}