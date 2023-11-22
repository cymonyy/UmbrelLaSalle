package com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.databinding.ComponentProductItemLayoutBinding
import com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.holders.TransactionProductItemCardsViewHolder

class TransactionProductItemCardsAdapter() : Adapter<TransactionProductItemCardsViewHolder>() {

    private lateinit var data: MutableList<String>
    private lateinit var onItemClickListener: OnItemClickListener
    private var selectedItems = mutableSetOf<String>()
    private var selectable: Boolean = false

    interface OnItemClickListener {
        fun onItemClick(item: String)
    }

    constructor(requestItems: MutableList<String>) : this() {
        this.data = requestItems
    }

    constructor(requestItems: MutableList<String>, onItemClickListener: Any) : this() {
        this.data = requestItems
        this.onItemClickListener = onItemClickListener as OnItemClickListener
        this.selectable = true
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TransactionProductItemCardsViewHolder {
        val itemViewBinding: ComponentProductItemLayoutBinding = ComponentProductItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return TransactionProductItemCardsViewHolder(itemViewBinding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: TransactionProductItemCardsViewHolder, position: Int) {
        val item = data[position]
        val isSelected = selectedItems.contains(item)
        holder.bindData(item, isSelected, selectable)

        if (selectable){
            holder.itemView.isSelected = isSelected
            holder.itemView.setOnClickListener{
                onItemClickListener.onItemClick(item)
                notifyItemChanged(position)
            }
        }
    }

    fun getSelectedItems(): MutableSet<String> = selectedItems

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newData: MutableList<String>) {
        this.selectedItems = mutableSetOf()
        this.data = newData
        Log.d("DataSetAfter", data.last().toString())
        notifyDataSetChanged()
    }


}