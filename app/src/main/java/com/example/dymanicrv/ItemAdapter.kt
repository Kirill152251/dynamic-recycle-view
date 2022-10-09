package com.example.dymanicrv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dymanicrv.databinding.RvItemBinding

class ItemAdapter(private val listener: Listener) :
    ListAdapter<Item, ItemAdapter.ViewHolder>(ItemCallback), View.OnClickListener {

    class ViewHolder(val binding: RvItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RvItemBinding.inflate(inflater, parent, false)

        binding.btnDelete.setOnClickListener(this)
        binding.root.setOnClickListener(this)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.textNumber.text = item.value.toString()
        with(holder.binding) {
            root.tag = item
            btnDelete.tag = item
        }
    }

    object ItemCallback: DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.value == newItem.value
        }

    }
    interface Listener {
        fun deleteItem(item: Item)
        fun clickItem(item: Item)
    }

    override fun onClick(v: View) {
        val item = v.tag as Item
        when (v.id) {
            R.id.btn_delete -> listener.deleteItem(item)
            else -> listener.clickItem(item)
        }
    }
}