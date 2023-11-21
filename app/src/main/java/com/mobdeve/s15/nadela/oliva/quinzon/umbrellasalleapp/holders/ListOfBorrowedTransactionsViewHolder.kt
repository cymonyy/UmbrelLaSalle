package com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.holders

import android.content.Context
import androidx.core.content.ContextCompat.getString
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.firebase.database.collection.LLRBNode.Color
import com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.R
import com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.adapters.TransactionProductItemCardsAdapter
import com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.databinding.StudentTransactionItemLayoutBinding
import com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.models.TransactionModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class ListOfBorrowedTransactionsViewHolder(itemView: StudentTransactionItemLayoutBinding, private val context: Context): ViewHolder(itemView.root) {

    private var status = itemView.tvStatus
    private var station = itemView.tvStation
    private var expectedReturnDate = itemView.tvExpectedReturnDate
    private var requestItems = itemView.rvRequestedItems
    private var daysLeft = itemView.tvDaysLeft
    private var card = itemView.cvTransactionCard
    private lateinit var itemsAdapter: TransactionProductItemCardsAdapter


    fun bindData(transaction: TransactionModel){
        this.status.text = HtmlCompat.fromHtml(context.getString(R.string.list_borrowed_transactions_status, transaction.status), HtmlCompat.FROM_HTML_MODE_LEGACY)
        this.station.text = HtmlCompat.fromHtml(context.getString(R.string.list_borrowed_transactions_station, transaction.station), HtmlCompat.FROM_HTML_MODE_LEGACY)

        val dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val expected: LocalDate = LocalDate.parse(transaction.expectedReturnDate, dateFormatter)
        this.expectedReturnDate.text = expected.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))

        requestItems.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        itemsAdapter = TransactionProductItemCardsAdapter(transaction.requestedItems.keys.toMutableList())
        requestItems.adapter = itemsAdapter

        // Calculate the number of days left
        val left = ChronoUnit.DAYS.between(LocalDate.now(), expected)
        daysLeft.text = when(transaction.status == "Approved"){
            true -> HtmlCompat.fromHtml(context.getString(R.string.list_borrowed_transactions_days_left, left.toInt().toString()), HtmlCompat.FROM_HTML_MODE_LEGACY)
            false -> ""
        }

        when(transaction.status) {
            "Approved" -> card.setCardBackgroundColor(context.getColor(R.color.transaction_approved))
            "Requested" -> card.setCardBackgroundColor(context.getColor(R.color.transaction_requested))
            "Denied" -> card.setCardBackgroundColor(context.getColor(R.color.transaction_denied))
            "Returned" -> card.setCardBackgroundColor(context.getColor(R.color.transaction_returned))
        }

    }

}