package com.example.dymanicrv

data class Item(
    val value: Int
)
fun getItems(size: Int): List<Item> {
    val list = mutableListOf<Item>()
    for (i in 1 .. size) {
        list.add(Item(i))
    }
    return list
}
