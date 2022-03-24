package com.example.counter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.counter.databinding.PurchaseItemBinding

class RcAdapter : ListAdapter<Purchase, RcAdapter.ItemHolder>(ItemComparator()) {

    class ItemHolder(private val binding: PurchaseItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(purchase: Purchase) = with(binding){
            nameText.text = purchase.name
            priceText.text = purchase.price.toString()
        }
        companion object {
            fun create(parent: ViewGroup): ItemHolder {
                return ItemHolder(PurchaseItemBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false))
            }
        }
    }

    class ItemComparator : DiffUtil.ItemCallback<Purchase>(){
        override fun areItemsTheSame(oldItem: Purchase, newItem: Purchase): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Purchase, newItem: Purchase): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(getItem(position))
    }
}