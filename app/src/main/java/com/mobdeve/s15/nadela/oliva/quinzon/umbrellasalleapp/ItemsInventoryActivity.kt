package com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.adapters.StockItemAdapter
import com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.databinding.AdminHomepageContainerBinding

class ItemsInventoryActivity: AppCompatActivity() {

    private lateinit var binding: AdminHomepageContainerBinding

    override fun onCreate(savedInstanceState: Bundle??) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_homepage_container)

        val recyclerView: RecyclerView = findViewById(R.id.rvItemsInventory)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = StockItemAdapter()
        recyclerView.adapter = adapter

    }

}