package ar.unlam.nohaapp.viewmodel

import androidx.lifecycle.ViewModel
import ar.unlam.nohaapp.adapters.ItemsAdapter

 class HomeFragmentViewModel : ViewModel() {

    lateinit var climaDescription : String
    lateinit var climaIcon : String
    lateinit var climaTemp : String
    lateinit var actividades : ItemsAdapter
    lateinit var dia : String
    var conContenido = false;
}