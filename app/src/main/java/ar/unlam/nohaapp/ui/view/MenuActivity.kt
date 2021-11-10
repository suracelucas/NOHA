package ar.unlam.nohaapp.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import ar.unlam.nohaapp.composables.MenuScreen
import ar.unlam.nohaapp.data.ItemMenu
import ar.unlam.nohaapp.ui.theme.NOHATheme
import ar.unlam.nohaapp.ui.viewmodel.MenuActivityViewModel

class MenuActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: MenuActivityViewModel by viewModels()
        val itemList = viewModel.itemList
        val buyList = viewModel.buyList

        setContent {
            NOHATheme {
                Scaffold(
                    topBar = {
                        TopAppBar(title = {
                            Text("Menu Room Service")
                        },
                            navigationIcon = {
                                IconButton(onClick = { finish() }) {
                                    Icon(Icons.Filled.ArrowBack, "Volver")
                                }
                            })
                    }, content = {
                        MenuScreen(itemList, buyList) { string, item ->
                            if (string == "-") {
                                viewModel.removeItemNewList(item)
                            }
                            if (string == "+") {
                                viewModel.addItemNewList(item)
                            }
                        }
                    }
                )
            }
        }
    }
}