package com.codelabs.wegot.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codelabs.wegot.databinding.ItemCardHistoryPencacahanBinding
import com.codelabs.wegot.model.local.data.HistoryItem

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {
    private val items = mutableListOf<HistoryItem>()

    fun setItems(newItems: List<HistoryItem>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemCardHistoryPencacahanBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HistoryItem) {
            binding.appCompatTextView4.text = item.date
            binding.tvJumlahVolumeSampah.text = "Jumlah Volume Sampah : ${item.volume}"
            binding.tvHasilPencacahan.text = "Hasil Pencacahan : ${item.result}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCardHistoryPencacahanBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
}
