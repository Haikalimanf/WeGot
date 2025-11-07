package com.codelabs.wegot.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codelabs.wegot.databinding.ItemCardPrediksiSiklusBinding
import com.codelabs.wegot.model.remote.response.prediksi.CartsItem

class SiklusAdapter : RecyclerView.Adapter<SiklusAdapter.ViewHolder>() {
    private val items = mutableListOf<CartsItem>()

    fun setItems(newItems: List<CartsItem>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemCardPrediksiSiklusBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CartsItem) {
            binding.tvResultPrediksiTotal.text = item.id.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCardPrediksiSiklusBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
}
