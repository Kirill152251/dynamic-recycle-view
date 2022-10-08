package com.example.dymanicrv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.dymanicrv.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val data = getItems(15).toMutableList()

        binding.recycleView.layoutManager = GridLayoutManager(this,2)
        val adapter = ItemAdapter(data)
        binding.recycleView.adapter = adapter
    }
}