package com.example.dymanicrv

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.dymanicrv.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

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
                "You click ${item.number} item",
                Toast.LENGTH_SHORT
            ).show()
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            binding.recycleView.layoutManager = GridLayoutManager(this, 2)
        } else {
            binding.recycleView.layoutManager = GridLayoutManager(this, 4)
        }
        binding.recycleView.adapter = adapter
        itemService.addListener(itemListener)
        lifecycleScope.launch(Dispatchers.IO) {
            while (isActive) {
                delay(5000)
                itemService.addItem()
            }
        }
    }

    private val itemListener: ItemListener = {
        adapter.submitList(it)
    }

    override fun onDestroy() {
        super.onDestroy()
        itemService.removeListener(itemListener)
    }
}