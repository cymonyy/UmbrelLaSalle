package com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.adapters.ListOfBorrowedTransactionsAdapter
import com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.databases.TransactionsHelper
import com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.databinding.StudentListOfBorrowedTransactionsBinding
import com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.fragments.AddTransactionBottomSheetDialogFragment
import com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.models.TransactionModel

class StudentViewTransactions: AppCompatActivity(),  ListOfBorrowedTransactionsAdapter.ScrollToPositionCallback {

    private lateinit var viewBinding: StudentListOfBorrowedTransactionsBinding
    private var transactions: MutableList<TransactionModel> = mutableListOf()
    private lateinit var listAdapter: ListOfBorrowedTransactionsAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    private var transactionsHelper = TransactionsHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.viewBinding = StudentListOfBorrowedTransactionsBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        // Initialize RecyclerView and Adapter
        this.recyclerView = viewBinding.rvTransactions
        recyclerView.layoutManager = LinearLayoutManager(this)

        listAdapter = ListOfBorrowedTransactionsAdapter(mutableListOf()) // Initialize with empty list
        recyclerView.adapter = listAdapter

        // Set the callback in the adapter
        listAdapter.setScrollToPositionCallback(this@StudentViewTransactions);


        viewBinding.ibAddButton.setOnClickListener {
            showBottomSheetDialog()

        }

        progressBar = viewBinding.progressBar

        transactionsHelper.data.observe(this, Observer { newData ->
            run {
                transactions = newData
                listAdapter.updateData(transactions)
            }
        })

        // Load data from Firestore
        loadStudentTransactions()

    }


    override fun onScrollToPosition(position: Int) {
        recyclerView.smoothScrollToPosition(position)
    }


    private fun showBottomSheetDialog() {
        // Use BottomSheetFragment with view binding
        val bottomSheetFragment = AddTransactionBottomSheetDialogFragment(intent.getStringExtra("userID").toString())
        bottomSheetFragment.setBottomSheetListener(object : AddTransactionBottomSheetDialogFragment.BottomSheetListener {
            override fun onDataSent(transaction: TransactionModel) {
                listAdapter.addData(transaction)
                recyclerView.smoothScrollToPosition(listAdapter.itemCount - 1)
            }
        })
        bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
    }


    private fun loadStudentTransactions(){
        progressBar.visibility = View.VISIBLE
        val user = intent.getStringExtra("userID").toString()
        transactionsHelper.getStudentTransactions(user)
        progressBar.visibility = View.GONE
    }



}