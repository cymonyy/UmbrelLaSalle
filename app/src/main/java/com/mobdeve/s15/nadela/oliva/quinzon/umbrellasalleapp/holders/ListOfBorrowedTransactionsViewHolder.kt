package com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.holders

import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.databinding.StudentTransactionItemLayoutBinding
import com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.models.TransactionModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ListOfBorrowedTransactionsViewHolder(itemView: StudentTransactionItemLayoutBinding): ViewHolder(itemView.root) {

    private var status = itemView.tvStatus
    private var station = itemView.tvStation
    private var expectedReturnDate = itemView.tvExpectedReturnDate


    fun bindData(transaction: TransactionModel){
        this.status.text = "Status: " + transaction.status
        this.station.text = "Station: " + transaction.station

        val dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val localDate: LocalDate = LocalDate.parse(transaction.expectedReturnDate, dateFormatter)
        this.expectedReturnDate.text = localDate.toString()


    }

}