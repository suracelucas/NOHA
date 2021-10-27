package ar.unlam.nohaapp.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ar.unlam.nohaapp.R
import ar.unlam.nohaapp.data.ItemsMenuList
import ar.unlam.nohaapp.databinding.ActivityMainBinding
import ar.unlam.nohaapp.databinding.ActivityMenuBinding
import ar.unlam.nohaapp.ui.adapters.ItemsMenuAdapter

class MenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMenuBinding.inflate(LayoutInflater.from(this))
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.itemMenuRw.adapter = ItemsMenuAdapter(ItemsMenuList().loadItemsMenu())
        binding.itemMenuRw.layoutManager = LinearLayoutManager(this)


    }

}