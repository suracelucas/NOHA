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
    val buyList = MutableLiveData<MutableList<ItemMenu>>()

    sealed class State {
        object Loading : State()
        class Success(val itemList: List<ItemMenu>) : State()
    }
}