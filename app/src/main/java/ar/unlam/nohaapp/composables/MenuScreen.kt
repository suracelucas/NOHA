package ar.unlam.nohaapp.composables

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import ar.unlam.nohaapp.data.model.ItemMenu
import ar.unlam.nohaapp.ui.viewmodel.MenuActivityViewModel

@Composable
fun MenuScreen(
    itemList: List<ItemMenu>,
    buyList: MutableLiveData<MutableList<ItemMenu>>,
    resultado: String,
    onClick: (string: String, item: ItemMenu) -> Unit,
    onButtonPagar: () -> Unit
) {
    val viewModel: MenuActivityViewModel = viewModel()
    val state = viewModel.state.observeAsState()
    val composeList = buyList.observeAsState().value

    when (state.value) {
        is MenuActivityViewModel.State.Loading -> LoadingScreen()
        is MenuActivityViewModel.State.Success -> {
            Surface(Modifier.padding(all = 8.dp)) {
                Column(Modifier.fillMaxHeight()) {
                    Text(
                        "Pedido para habitación $resultado",
                        textAlign = TextAlign.Center,
                        fontSize = 36.sp
                    )
                    LazyColumn {
                        items(itemList) { item ->
                            ItemView(item, composeList) { string ->
                                onClick(string, item)
                            }
                        }
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Column { TotalView(composeList) }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            BotonPagar { onButtonPagar() }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ItemView(
    item: ItemMenu,
    composeList: MutableList<ItemMenu>?,
    onClick: (string: String) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Column {
            Text(item.nombre, fontSize = 32.sp)
            Text("$${item.precio}", fontSize = 32.sp)
        }
        CantidadView(item, composeList) { string -> onClick(string) }
    }
    Column {
        Divider(
            color = Color.Gray,
            thickness = 1.dp
        )
    }
}

@Composable
fun CantidadView(
    item: ItemMenu,
    composeList: MutableList<ItemMenu>?,
    onClick: (string: String) -> Unit
) {
    val cantidadEnLista: Int = composeList?.count { it == item } ?: 0

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
        Boton("-") { onClick("-") }
        Text(
            cantidadEnLista.toString(),
            fontSize = 36.sp,
            modifier = Modifier.padding(horizontal = 7.dp)
        )
        Boton("+") { onClick("+") }
    }
}

@Composable
fun Boton(
    string: String,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        Modifier.size(50.dp)
    ) {
        if (string == "-") {
            Icon(
                Icons.Filled.KeyboardArrowLeft,
                contentDescription = "Restar",
                tint = Color.Red,
                modifier = Modifier.size(40.dp)
            )
        }
        if (string == "+") {
            Icon(
                Icons.Filled.KeyboardArrowRight,
                contentDescription = "Sumar",
                tint = Color.Red,
                modifier = Modifier.size(40.dp)
            )
        }
    }
}

@Composable
fun TotalView(composeList: MutableList<ItemMenu>?) {
    var total = 0
    if (composeList != null) {
        for (item in composeList) {
            total += item.precio
        }
    }
    Text("Total: $${total}", fontSize = 36.sp)
}

@Composable
fun BotonPagar(onClick: () -> Unit) {
    Button(onClick = onClick, Modifier.padding(top = 7.dp)) {
        Icon(
            Icons.Filled.ShoppingCart,
            contentDescription = "Pagar",
            tint = Color.White,
            modifier = Modifier.size(35.dp)
        )
    }
}

@Composable
fun DialogPago(onClick: () -> Unit) {
    val viewModel: MenuActivityViewModel = viewModel()
    val openDialog = viewModel.toggleAlert

    AlertDialog(
        onDismissRequest = { openDialog.value = false },
        title = {
            Text("Cómo pagar", fontSize = 30.sp, fontStyle = FontStyle.Italic)
        },
        text = {
            Column {
                Divider(
                    color = Color.Gray, thickness = 1.dp,
                    modifier = Modifier.padding(0.dp, 10.dp)
                )
                Text("Anotá este mail:", fontSize = 20.sp)
                Text(
                    "tu_hotel@hotel.com", fontSize = 25.sp, fontWeight = FontWeight.Bold,
                    color = if (isSystemInDarkTheme()) Color.White else Color.Black
                )
                Text(
                    "Entrá a tu cuenta de MercadoPago. \n" +
                            "Ingresá el mail anterior. \n" +
                            "Ingresá el monto. \n" +
                            "Ingresá tu habitación. \n" +
                            "Aceptá los términos. \n" +
                            "Confirmá el envío.",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(top = 3.dp),
                )
            }
        },
        buttons = {
            Row(
                modifier = Modifier
                    .padding(all = 8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = { openDialog.value = false }
                ) {
                    Text("Cerrar", fontSize = 20.sp, color = Color.White)
                }
                Button(
                    onClick = { onClick() },
                    Modifier.padding(start = 5.dp)
                ) {
                    Text("Continuar", fontSize = 20.sp, color = Color.White)
                }
            }
        }
    )
}

@Composable
fun LoadingScreen() {
    Surface(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        CircularProgressIndicator(
            Modifier
                .wrapContentHeight()
                .wrapContentWidth()
        )
    }
}