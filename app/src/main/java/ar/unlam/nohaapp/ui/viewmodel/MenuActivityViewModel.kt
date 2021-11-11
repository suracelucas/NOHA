package ar.unlam.nohaapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import ar.unlam.nohaapp.data.ItemMenu
import ar.unlam.nohaapp.data.ItemsMenuList

class MenuActivityViewModel : ViewModel() {

    val state = liveData {
        emit(State.Loading)
        emit(State.Success(ItemsMenuList().getAll()))
    }

    val itemList = ItemsMenuList().loadItemsMenu()
    var buyList = MutableLiveData<MutableList<ItemMenu>>(mutableListOf())

    fun addItemNewList(item: ItemMenu) {
        val newList = buyList.value?.toMutableList()
        newList?.add(item)
        buyList.value = newList!!
    }

    fun removeItemNewList(item: ItemMenu) {
        val newList = buyList.value?.toMutableList()
        if (newList?.contains(item) == true) {
            newList.remove(item)
            buyList.value = newList!!
        }
    }

    sealed class State {
        object Loading : State()
        class Success(val itemList: List<ItemMenu>) : State()
    }
}