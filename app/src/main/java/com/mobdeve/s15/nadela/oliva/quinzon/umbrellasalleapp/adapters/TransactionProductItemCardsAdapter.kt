package com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.databinding.ComponentProductItemLayoutBinding
import com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.holders.TransactionProductItemCardsViewHolder

class TransactionProductItemCardsAdapter(private val requestItems: MutableList<String>): Adapter<TransactionProductItemCardsViewHolder>() {
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
        return requestItems.size
    }

    override fun onBindViewHolder(holder: TransactionProductItemCardsViewHolder, position: Int) {
        holder.bindData(requestItems[position])
    }
}