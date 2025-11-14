package com.codelabs.wegot.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codelabs.wegot.databinding.ItemCardPrediksiSiklusBinding
import com.codelabs.wegot.model.local.data.SiklusItem

class SiklusAdapter : RecyclerView.Adapter<SiklusAdapter.ViewHolder>() {
    private val items = mutableListOf<SiklusItem>()

    fun setItems(newItems: List<SiklusItem>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemCardPrediksiSiklusBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SiklusItem) {
            binding.appCompatTextView4.text = item.title
            binding.tvEstimasiPanen.text = item.label
            binding.tvResultPrediksiTotal.text = item.result
            binding.tvEstimasiTanggal.text = item.date
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

