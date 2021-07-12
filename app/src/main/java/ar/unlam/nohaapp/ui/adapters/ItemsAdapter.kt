package ar.unlam.nohaapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ar.unlam.nohaapp.R
import ar.unlam.nohaapp.databinding.ItemNovedadBinding
import ar.unlam.nohaapp.notificaciones.data.model.ActividadEntity

class ItemsAdapter(
    private val items: List<ActividadEntity>
) : RecyclerView.Adapter<ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemNovedadBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindNovedaditem(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

}

class ItemViewHolder(private val binding: ItemNovedadBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bindNovedaditem(itemModel: ActividadEntity) {
        binding.imageItem.setImageResource(filtroImg(itemModel))
        binding.titleItem.text = itemModel.nombreActividad
        binding.horarioItem.text = itemModel.horario
    }

    private fun filtroImg(itemModel: ActividadEntity): Int {
        return when (itemModel.idLugar) {
            1.toShort() -> R.drawable.image_buffet
            2.toShort() -> R.drawable.image_salon
            3.toShort() -> R.drawable.image_gimnasio
            4.toShort() -> R.drawable.image_natatorio
            5.toShort() -> R.drawable.image_spa
            else -> 0
        }
    }
}