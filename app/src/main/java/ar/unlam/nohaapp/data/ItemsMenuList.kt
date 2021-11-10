package ar.unlam.nohaapp.data

import ar.unlam.nohaapp.data.model.ItemMenu

class ItemsMenuList {
    fun loadItemsMenu(): List<ItemMenu> {
        return listOf(
            ItemMenu("Hamburguesa", 200),
            ItemMenu("Napolitana", 200),
            ItemMenu("Tortilla", 300),
            ItemMenu("Fritas", 100),
            ItemMenu("Agua", 50),
            ItemMenu("Gaseosa", 80)
        )
    }
}
