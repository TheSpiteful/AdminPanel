package com.example.adminpanel.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.adminpanel.Cell
import com.example.adminpanel.R

class CellAdapter(private val cells: ArrayList<Cell>) : RecyclerView.Adapter<CellAdapter.CellViewHolder>() {

    class CellViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvId: TextView = itemView.findViewById(R.id.tv_id)
        val tvSize: TextView = itemView.findViewById(R.id.tv_size)
        val tvStatus: TextView = itemView.findViewById(R.id.tv_status)
        val tvDatetime: TextView = itemView.findViewById(R.id.tv_datetime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CellViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)
        return CellViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CellViewHolder, position: Int) {
        val cell = cells[position]

        holder.tvId.text = cell.id
        holder.tvSize.text = cell.size.toString()
        holder.tvStatus.text = cell.status
        holder.tvDatetime.text = cell.datetime.toString()
    }

    override fun getItemCount(): Int {
        return cells.size
    }
}


