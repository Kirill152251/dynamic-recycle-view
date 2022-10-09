package com.example.dymanicrv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.dymanicrv.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val itemService: ItemService
        get() = (applicationContext as App).itemService

    private val adapter = ItemAdapter(object : ItemAdapter.Listener {
        override fun deleteItem(item: Item) {
            itemService.deleteItem(item)
        }
        override fun clickItem(item: Item) {
            Toast.makeText(
                this@MainActivity,
                "You click ${item.value} item",
                Toast.LENGTH_SHORT
            ).show()
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recycleView.layoutManager = GridLayoutManager(this, 2)
        binding.recycleView.adapter = adapter
        itemService.addListener(itemListener)
    }
    private val itemListener: ItemListener = {
        adapter.submitList(it)
    }
    
    override fun onDestroy() {
        super.onDestroy()
        itemService.removeListener(itemListener)
    }
}