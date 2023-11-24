package com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.R
import com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.databinding.InventoryItemBinding
import com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.holders.StockItemViewHolder
import com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.models.StockItemModel

class StockItemAdapter(private var stockItemList: MutableList<StockItemModel>):
    Adapter<StockItemViewHolder>(){

    private val itemColors = intArrayOf(
        android.graphics.Color.parseColor("#20C2AF"),
        android.graphics.Color.parseColor("#C2B220"),
        android.graphics.Color.parseColor("#C23D20"),

//        R.color.cyan, R.color.bronze, R.color.scarlet
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockItemViewHolder {
        val binding = InventoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StockItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return stockItemList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newData: MutableList<StockItemModel>){
        stockItemList = newData
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: StockItemViewHolder, position: Int) {
        val stockItem = stockItemList[position]
        holder.bind(stockItem)
        val colorIndex = position % itemColors.size
        holder.itemView.findViewById<CardView>(R.id.cvItemCard).setCardBackgroundColor(itemColors[colorIndex])
    }


}