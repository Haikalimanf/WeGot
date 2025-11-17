package com.codelabs.wegot.model.local.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codelabs.wegot.R
import com.codelabs.wegot.databinding.ItemCardPrediksiSiklusBinding

data class SiklusItem(
    val title: String,
    val label: String,
    val result: String,
    val date: String
)

class LocalSiklusAdapter : RecyclerView.Adapter<LocalSiklusAdapter.VH>() {

    private val items = mutableListOf<SiklusItem>()

    fun setItems(newItems: List<SiklusItem>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCardPrediksiSiklusBinding.inflate(inflater, parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class VH(private val binding: ItemCardPrediksiSiklusBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SiklusItem) {
            binding.appCompatTextView4.text = item.title
            binding.tvEstimasiPanen.text = item.label
            binding.tvResultPrediksiTotal.text = item.result
            binding.tvEstimasiTanggal.text = item.date
        }
    }
}
