package ar.unlam.nohaapp.data.model
import androidx.annotation.DrawableRes

data class ItemModel (@DrawableRes val imageResourceId:Int, val titulo: String, val horario: String)