package ar.unlam.nohaapp.notificaciones.iu.viewmodel

import android.widget.ExpandableListAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NotificationFragmentViewModel(): ViewModel() {
    val lista = MutableLiveData<ExpandableListAdapter>()
    fun onCreate(){

    }
}