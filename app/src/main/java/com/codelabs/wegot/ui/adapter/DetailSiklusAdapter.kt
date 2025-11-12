package com.codelabs.wegot.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codelabs.wegot.R
import com.codelabs.wegot.ui.prediksi.detail_siklus.SiklusItem

class DetailSiklusAdapter(
    private val items: List<SiklusItem>,
    private val onFaseClick: (SiklusItem) -> Unit
) : RecyclerView.Adapter<DetailSiklusAdapter.SiklusViewHolder>() {

    inner class SiklusViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.tvTitle)
        val date: TextView = view.findViewById(R.id.tvDate)
        val description: TextView = view.findViewById(R.id.tvDesc)
        val arrowIcon: ImageView = view.findViewById(R.id.arrow_icon)
        val lineConnector: View = view.findViewById(R.id.line_connector)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SiklusViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_card_detail_siklus, parent, false)
        return SiklusViewHolder(view)
    }

    override fun onBindViewHolder(holder: SiklusViewHolder, position: Int) {
        val item = items[position]

        holder.title.text = item.title
        holder.date.text = item.date
        holder.description.text = item.description
        holder.arrowIcon.visibility = if (item.isClickable) View.VISIBLE else View.GONE

        if (position == items.size - 1) {
            holder.lineConnector.visibility = View.GONE
        } else {
            holder.lineConnector.visibility = View.VISIBLE
        }

        holder.itemView.setOnClickListener {
            if (item.isClickable) {
                onFaseClick(item)
            }
        }
    }

    override fun getItemCount(): Int = items.size
}