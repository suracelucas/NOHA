package ar.unlam.nohaapp.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import ar.unlam.nohaapp.data.ItemMenu
import ar.unlam.nohaapp.data.ItemsMenuList
import ar.unlam.nohaapp.databinding.ActivityMenuBinding
import ar.unlam.nohaapp.ui.adapters.ItemsMenuAdapter

class MenuActivity : AppCompatActivity(), ItemsMenuAdapter.OnButtonClickListener {
    private lateinit var binding: ActivityMenuBinding
    private lateinit var itemsTotales: MutableList<ItemMenu>

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMenuBinding.inflate(LayoutInflater.from(this))
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupRecyclerView()
        itemsTotales = mutableListOf()
        codigoHabitacion()
    }

    private fun setupRecyclerView() {
        binding.itemMenuRw.layoutManager = LinearLayoutManager(this)
        binding.itemMenuRw.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        binding.itemMenuRw.adapter = ItemsMenuAdapter(ItemsMenuList().loadItemsMenu(), this)
    }

    override fun onPlusClick(item: ItemMenu) {
        itemsTotales.add(item)
        calcularTotal()
    }

    override fun onLessClick(item: ItemMenu) {
        if (itemsTotales.isNotEmpty()) {
            itemsTotales.remove(item)
        }
        calcularTotal()
    }

    private fun calcularTotal() {
        var precioTotal = 0
        for (item in itemsTotales) {
            precioTotal += item.precio.toShort()
        }
        binding.compra.text = "$${precioTotal}"
    }


    fun codigoHabitacion() {
        binding.habitacion.text = "Pedido para habitación ###" // ${codigoQR.getHabitacion()}
    }

    /*
    fun pagar(){
        binding.pay.setOnClickListener{
        }
    }
     */
}