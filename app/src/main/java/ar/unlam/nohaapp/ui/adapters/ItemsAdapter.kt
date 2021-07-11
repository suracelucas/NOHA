package ar.unlam.nohaapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ar.unlam.nohaapp.data.model.ItemModel
import ar.unlam.nohaapp.databinding.ItemNovedadBinding

class ItemsAdapter(
        //private val context: Context,
        private val items : List<ItemModel>
): RecyclerView.Adapter<ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemNovedadBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindNovedaditem(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

}

class ItemViewHolder(private val binding: ItemNovedadBinding) : RecyclerView.ViewHolder(binding.root){

    fun bindNovedaditem(itemModel: ItemModel){
        binding.imageItem.setImageResource(itemModel.imageResourceId)
        binding.titleItem.text = itemModel.titulo
        binding.horarioItem.text = itemModel.horario
    }
}