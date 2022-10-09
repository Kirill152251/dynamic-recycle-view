package com.example.dymanicrv

typealias ItemListener = (items:List<Item>) -> Unit

class ItemService {

    private var items = mutableListOf<Item>()
    private val listeners = mutableSetOf<ItemListener>()

    init {
        items = getItems()
    }

    private fun getItems(): MutableList<Item> {
        val list = mutableListOf<Item>()
        for (i in 1 .. 16) {
            list.add(Item(i))
        }
        return list
    }

    fun deleteItem(item: Item) {
        val indexToDelete = items.indexOfFirst { it.value == item.value }
        if (indexToDelete != -1) {
            items = items.toMutableList()
            items.removeAt(indexToDelete)
            notifyChanges()
        }

    }
    private fun notifyChanges() {
        listeners.forEach { it.invoke(items) }
    }
    fun addListener(listener: ItemListener) {
        listeners.add(listener)
        listener.invoke(items)
    }

    fun removeListener(listener: ItemListener) {
        listeners.remove(listener)
    }
}