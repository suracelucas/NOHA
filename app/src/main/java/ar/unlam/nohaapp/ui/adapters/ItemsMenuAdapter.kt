package ar.unlam.nohaapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ar.unlam.nohaapp.data.ItemMenu
import ar.unlam.nohaapp.databinding.ItemMenuBinding

class ItemsMenuAdapter(private val items: List<ItemMenu>) :
    RecyclerView.Adapter<ItemMenuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemMenuViewHolder {
        val binding = ItemMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemMenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemMenuViewHolder, position: Int) {
        holder.bindItem(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

}

class ItemMenuViewHolder(private val binding: ItemMenuBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bindItem(itemModel: ItemMenu) {
        binding.itemNameTv.text = itemModel.nombre
        binding.itemPriceTv.text = "$${itemModel.precio}"
        sumarPrecio(itemModel)
        restarPrecio(itemModel)
    }

    fun sumarPrecio(itemModel: ItemMenu) {
        val btn = binding.plusBtn
        var itemCount = binding.itemCountTv
        btn.setOnClickListener {
            val value = itemModel.precio
            binding.compra.text = value.toString()
            binding.itemCountTv.text = itemCount.text
        }
    }

    fun restarPrecio(itemModel: ItemMenu) {
        val btn = binding.lessBtn
        var itemCount = binding.itemCountTv
        btn.setOnClickListener {
            val value = itemModel.precio
            binding.compra.text = value.toString()
            itemCount.text
            binding.itemCountTv.text = itemCount.text
        }
    }
}