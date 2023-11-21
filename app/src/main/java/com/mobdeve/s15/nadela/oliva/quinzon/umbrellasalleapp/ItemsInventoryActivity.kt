package com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent.DispatcherState
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.toObject
import com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.adapters.StockItemAdapter
import com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.databases.StockItemHelper
import com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.databinding.AdminHomepageContainerBinding
import com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.models.StockItemModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ItemsInventoryActivity: AppCompatActivity() {

    private lateinit var binding: AdminHomepageContainerBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StockItemAdapter
    private lateinit var data: MutableList<StockItemModel>

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        this.binding = AdminHomepageContainerBinding.inflate(layoutInflater)
        setContentView(binding.root)


        recyclerView = this.binding.rvItemsInventory
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = StockItemAdapter(mutableListOf())
        recyclerView.adapter = adapter

        loadStockItems()

    }
    private fun loadStockItems(){
        lifecycleScope.launch(Dispatchers.Main) {
            try{
                data = mutableListOf()
                val documents = withContext(Dispatchers.IO){
                    StockItemHelper.getStockItems()
                }

                if (documents.isEmpty()) throw Exception("No transactions found")

                processData(documents)
                adapter.updateData(data)

            } catch (e: Exception){
                Log.e("EXCEPTION", e.message.toString())
            }
        }
    }

    private fun processData(documents: List<DocumentSnapshot>){
        // Handle the data on the main thread
        for(document in  documents){
            document.toObject<StockItemModel>()?.let { data.add(it) }
        }
    }
}