package com.example.dymanicrv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dymanicrv.databinding.RvItemBinding
import java.text.FieldPosition

class ItemAdapter(private val data: MutableList<Item>) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    private val dataList = data

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = RvItemBinding.bind(view)
        fun bind(item: Item, position: Int) {
            binding.textNumber.text = item.value.toString()
            binding.btnDelete.setOnClickListener {
                deleteItem(position)
            }
        }
    }

    private fun deleteItem(position: Int) {
        dataList.removeAt(position)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.rv_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item, position)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}