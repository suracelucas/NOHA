package ar.unlam.nohaapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ar.unlam.nohaapp.data.model.ItemMenu
import ar.unlam.nohaapp.databinding.ItemMenuBinding

class ItemsMenuAdapter(
    private val items: List<ItemMenu>,
    private val itemClickListener: OnButtonClickListener
) :
    RecyclerView.Adapter<ItemsMenuAdapter.ItemMenuViewHolder>() {

    interface OnButtonClickListener {
        fun onPlusClick(item: ItemMenu)
        fun onLessClick(item: ItemMenu)
    }

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


    inner class ItemMenuViewHolder(private val binding: ItemMenuBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItem(item: ItemMenu) {
            binding.plusBtn.setOnClickListener {
                itemClickListener.onPlusClick(item)
                val tvValue = binding.itemCountTv.text
                val tvValueNum = Integer.valueOf(tvValue.toString()).inc()
                binding.itemCountTv.text = tvValueNum.toString()
            }
            binding.lessBtn.setOnClickListener {
                itemClickListener.onLessClick(item)
                val tvValue = binding.itemCountTv.text
                val tvValueNum = Integer.valueOf(tvValue.toString()).dec()
                if (!tvValue.equals("0")) {
                    binding.itemCountTv.text = tvValueNum.toString()
                }
            }
            binding.itemNameTv.text = item.nombre
            binding.itemPriceTv.text = "$${item.precio}"
        }
    }
}