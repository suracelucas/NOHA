package ar.unlam.nohaapp.data

import ar.unlam.nohaapp.R
import ar.unlam.nohaapp.data.model.ItemModel

class Datasource {
    fun loadAffirmations(): List<ItemModel> {
        return listOf<ItemModel>(
            ItemModel(R.drawable.image_buffet, "Almuerzo", "12:00 - 14:00"),
            ItemModel(R.drawable.image_gimnasio, "Clase de zumba", "12:00 - 14:00"),
            ItemModel(R.drawable.image_spa, "Relajación", "12:00 - 14:00"),
            ItemModel(R.drawable.image_natatorio, "AquaGym", "12:00 - 14:00"),
            ItemModel(R.drawable.image_salon, "Torneo de metegol", "12:00 - 14:00")
        )
    }
}