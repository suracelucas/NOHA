package ar.unlam.nohaapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.unlam.nohaapp.data.model.Weather
import ar.unlam.nohaapp.domain.GetDay
import ar.unlam.nohaapp.domain.GetTodosDias
import ar.unlam.nohaapp.domain.GetWeather
import ar.unlam.nohaapp.notificaciones.data.model.ActividadEntity
import kotlinx.coroutines.launch

class HomeFragmentViewModel (var getWeather: GetWeather, var getDay: GetDay, val getTodosDias: GetTodosDias): ViewModel() {
    val clima = MutableLiveData<Weather>()
    val dia : MutableLiveData<Int> by lazy{
        MutableLiveData<Int>()
    }
    val listaActividades = MutableLiveData<List<ActividadEntity>>()

    fun onCreate(latitud :Double, longitud: Double){
      viewModelScope.launch {
         val result = getWeather(latitud, longitud)
          clima.postValue(result)
      }
        val dayResult = getDay()
        dia.postValue(dayResult)
        val actividades = getTodosDias()
        listaActividades.postValue(actividades)
    }
}