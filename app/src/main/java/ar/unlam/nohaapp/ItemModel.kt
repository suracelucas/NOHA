package ar.unlam.nohaapp
import androidx.annotation.DrawableRes

data class ItemModel (@DrawableRes val imageResourceId:Int, val titulo: String, val horario: String)