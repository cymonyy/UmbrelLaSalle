package com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.holders

import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.databinding.InventoryItemBinding
import com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.models.StockItemModel

class StockItemViewHolder(private val binding: InventoryItemBinding): RecyclerView.ViewHolder(binding.root){

    fun bind(stockItem: StockItemModel){
        binding.tvItemName.text = stockItem.itemCategory
        binding.tvStockValue.text = stockItem.available.toString() + "/" + stockItem.totalStock.toString()
    }

}