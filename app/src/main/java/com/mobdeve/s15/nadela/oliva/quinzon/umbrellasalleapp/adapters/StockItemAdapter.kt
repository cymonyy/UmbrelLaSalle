package com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.R
import com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.databinding.InventoryItemBinding
import com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.holders.StockItemViewHolder
import com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.models.StockItemModel

class StockItemAdapter: RecyclerView.Adapter<StockItemViewHolder>(){

    private val stockItemList = mutableListOf<StockItemModel>()
//    private val db = FirebaseFirestore.getInstance()
//    private val stockItemsCollection =
//        db.collection("Stations").document("geaml6skGP9188rx75DU ").collection("Stock")
    private val itemColors = intArrayOf(
        android.graphics.Color.parseColor("#20C2AF"),
        android.graphics.Color.parseColor("#C2B220"),
        android.graphics.Color.parseColor("#C23D20")
    )

    init {
//        fetchDataFromFireStore()
        createDummyData()
    }

    private fun createDummyData(){
        val dummyData = mutableListOf<StockItemModel>()

        dummyData.add(StockItemModel("Umbrella", 10, 20, 5))
        dummyData.add(StockItemModel("Rain Coat", 5, 15, 3))
        dummyData.add(StockItemModel("Rain Boots", 8, 10, 2))

        stockItemList.clear()
        stockItemList.addAll(dummyData)
    }

//    private fun fetchDataFromFireStore(){
//        stockItemsCollection.get()
//            .addOnSuccessListener{result ->
//                stockItemList.clear()
//
//                for (document in result){
//                    val stockItemFirebase = document.toObject((StockItemModel::class.java))
//                    val stockItemModel = StockItemModel(
//                        stockItemFirebase.name,
//                        stockItemFirebase.totalStock,
//                        stockItemFirebase.totalStock,
//                        stockItemFirebase.borrowedCount
//                    )
//                    stockItemList.add(stockItemModel)
//                }
//                notifyDataSetChanged()
//            }
//            .addOnFailureListener{exception ->
//            Log.w("TAG", "Error getting documents.", exception)}
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockItemViewHolder {
        val binding = InventoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StockItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return stockItemList.size
    }

    override fun onBindViewHolder(holder: StockItemViewHolder, position: Int) {
        val stockItem = stockItemList[position]
        holder.bind(stockItem)

        val colorIndex = position % itemColors.size
        holder.itemView.findViewById<CardView>(R.id.cvItemCard).setCardBackgroundColor(itemColors[colorIndex])
    }


}