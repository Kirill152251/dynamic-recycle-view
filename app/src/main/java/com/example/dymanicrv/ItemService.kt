package com.example.dymanicrv

typealias ItemListener = (items: List<Item>) -> Unit

const val initialSize = 5

class ItemService {

    private var items = mutableListOf<Item>()
    private var maxValue = initialSize
    private val listeners = mutableSetOf<ItemListener>()
    private val pullOfDeletedItemNumbers = mutableListOf<Item>()

    init {
        items = getItems()
    }

    private fun getItems(): MutableList<Item> {
        val list = mutableListOf<Item>()
        for (i in 1..initialSize) {
            list.add(Item(i))
        }
        return list
    }

    fun deleteItem(item: Item) {
        val indexToDelete = items.indexOfFirst { it.number == item.number }
        if (indexToDelete != -1) {
            items = items.toMutableList()
            pullOfDeletedItemNumbers.add(Item(items[indexToDelete].number))
            items.removeAt(indexToDelete)
            notifyChanges()
        }

    }

    fun addItem() {
        items = items.toMutableList()
        val position = if (items.size == 0) 0 else
            (0 until items.size).random()
        if (pullOfDeletedItemNumbers.isEmpty()) {
            maxValue += 1
            items.add(position, Item(maxValue))
        } else {
            items.add(position, pullOfDeletedItemNumbers.first())
            pullOfDeletedItemNumbers.removeAt(0)
        }
        notifyChanges()
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