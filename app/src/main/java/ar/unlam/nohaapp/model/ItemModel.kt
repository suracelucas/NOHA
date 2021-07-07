package ar.unlam.nohaapp.model
import androidx.annotation.DrawableRes

data class ItemModel (@DrawableRes val imageResourceId:Int, val titulo: String, val horario: String)