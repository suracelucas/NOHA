package ar.unlam.nohaapp.ui.view

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import ar.unlam.nohaapp.composables.DialogPago
import ar.unlam.nohaapp.composables.MenuScreen
import ar.unlam.nohaapp.domain.RESULTADO
import ar.unlam.nohaapp.ui.theme.NOHATheme
import ar.unlam.nohaapp.ui.viewmodel.MenuActivityViewModel

class MenuActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: MenuActivityViewModel by viewModels()
        val itemList = viewModel.itemList
        val buyList = viewModel.buyList
        val resultado = intent.getStringExtra(RESULTADO).toString()

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
                        MenuScreen(itemList, buyList, resultado,
                            onClick = { string, item ->
                                if (string == "-") {
                                    viewModel.removeItemNewList(item)
                                }
                                if (string == "+") {
                                    viewModel.addItemNewList(item)
                                }
                            },
                            onButtonPagar = {
                                viewModel.toggleAlert()
                            }
                        )
                        if (viewModel.toggleAlert.value) {
                            DialogPago {
                                try {
                                    openMercadoPago()
                                } catch (e: ActivityNotFoundException) {
                                    openStore()
                                }
                            }
                        }
                    }
                )
            }
        }
    }

    private fun openMercadoPago() {
        val intent =
            Intent(Intent.ACTION_VIEW, Uri.parse("https://www.mercadopago.com.ar/money-transfer"))
        startActivity(intent)
    }

    private fun openStore() {
        try {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName")))
        } catch (e: ActivityNotFoundException) {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
                )
            )
        }
    }
}