package com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.adapters

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.databinding.StudentTransactionItemLayoutBinding
import com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.holders.ListOfBorrowedTransactionsViewHolder
import com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.models.TransactionModel

class ListOfBorrowedTransactionsAdapter(private var data: MutableList<TransactionModel>): Adapter<ListOfBorrowedTransactionsViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListOfBorrowedTransactionsViewHolder {
        val itemViewBinding: StudentTransactionItemLayoutBinding = StudentTransactionItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ListOfBorrowedTransactionsViewHolder(itemViewBinding, parent.context)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ListOfBorrowedTransactionsViewHolder, position: Int) {
        holder.bindData(data[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newData: MutableList<TransactionModel>) {
        data = newData
        Log.d("DataSetAfter", data.last().toString())
        notifyDataSetChanged()
    }
}