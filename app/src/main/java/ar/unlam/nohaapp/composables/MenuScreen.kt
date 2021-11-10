package ar.unlam.nohaapp.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import ar.unlam.nohaapp.data.ItemMenu
import ar.unlam.nohaapp.ui.viewmodel.MenuActivityViewModel

@Composable
fun MenuScreen(
    itemList: List<ItemMenu>,
    buyList: MutableLiveData<MutableList<ItemMenu>>,
    onClick: (string: String, item: ItemMenu) -> Unit
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
                        "Pedido para habitación ###",
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
                    Box(modifier = Modifier.fillMaxSize(), Alignment.BottomStart) {
                        TotalView(composeList)
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