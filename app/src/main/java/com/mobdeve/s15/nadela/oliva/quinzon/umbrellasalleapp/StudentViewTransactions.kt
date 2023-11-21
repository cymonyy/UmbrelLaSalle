package com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.toObject
import com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.adapters.ListOfBorrowedTransactionsAdapter
import com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.databases.TransactionsHelper
import com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.databinding.StudentListOfBorrowedTransactionsBinding
import com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.models.TransactionModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StudentViewTransactions: AppCompatActivity() {

    private lateinit var viewBinding: StudentListOfBorrowedTransactionsBinding
    private var transactions: MutableList<TransactionModel> = mutableListOf()
    private lateinit var listAdapter: ListOfBorrowedTransactionsAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.viewBinding = StudentListOfBorrowedTransactionsBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        // Initialize RecyclerView and Adapter
        this.recyclerView = viewBinding.rvTransactions
        recyclerView.layoutManager = LinearLayoutManager(this)

        listAdapter = ListOfBorrowedTransactionsAdapter(mutableListOf()) // Initialize with empty list
        recyclerView.adapter = listAdapter


        // Load data from Firestore
        loadStudentTransactions()
    }



    private fun loadStudentTransactions(){
        lifecycleScope.launch(Dispatchers.Main) {
            try {
                val user = intent.getStringExtra("userID").toString()
                transactions = mutableListOf()
                val documents = withContext(Dispatchers.IO) {
                    TransactionsHelper.getStudentTransactions(user)
                }

                if (documents.isEmpty()) throw Exception("No transactions found")
                processData(documents)
                Log.d("DataSetBefore", transactions.last().toString())
                listAdapter.updateData(transactions)

            } catch (e: Exception) {
                // Handle exceptions
                Log.e("EXCEPTION", e.message.toString())
            }
        }
    }

    private fun processData(documents: List<DocumentSnapshot>){
        // Handle the data on the main thread
        for(document in  documents){
            Log.d("document", document.id)
            document.toObject<TransactionModel>()?.let { transactions.add(it) }
            Log.d("document", transactions.last().status)
        }
    }


}