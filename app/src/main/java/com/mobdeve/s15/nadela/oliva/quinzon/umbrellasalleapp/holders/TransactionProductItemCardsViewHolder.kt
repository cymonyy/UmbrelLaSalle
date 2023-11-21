package com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.holders

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.R
import com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.databinding.ComponentProductItemLayoutBinding

class TransactionProductItemCardsViewHolder(itemView: ComponentProductItemLayoutBinding): ViewHolder(itemView.root) {
    private var icon = itemView.ivItemIcon
    private var label = itemView.tvItemLabel

    fun bindData(item: String){
        label.text = item

        when(item){
            "Umbrella" -> icon.setImageResource(R.drawable.icon_item_umbrella)
            "Boots" -> icon.setImageResource(R.drawable.icon_item_boots)
            "Rain Coat" -> icon.setImageResource(R.drawable.icon_item_raincoat)
        }
    }

}