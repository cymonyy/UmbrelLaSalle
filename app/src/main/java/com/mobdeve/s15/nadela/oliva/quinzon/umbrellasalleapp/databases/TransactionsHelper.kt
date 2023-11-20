package com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.databases

import android.util.Log
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.models.TransactionModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class TransactionsHelper {

    companion object {
        suspend fun getStudentTransactions(userID: String): List<DocumentSnapshot> = withContext(Dispatchers.IO) {
            try {
                val db = FirebaseFirestore.getInstance()
                val querySnapshot = db.collection("Transactions").whereEqualTo("borrower", userID).get().await()
                return@withContext querySnapshot.documents
            } catch (e: Exception) {
                // Handle exceptions
                return@withContext mutableListOf()
            }
        }
    }



    /*
    private var db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private lateinit var  transactions: MutableList<TransactionModel>
    fun getStudentTransactions(userID: String): MutableList<TransactionModel>{
        transactions =  mutableListOf()

        db.collection("Transactions").
            whereEqualTo("borrower", userID).get()
            .addOnSuccessListener {documents ->
                for(document in  documents){
                    Log.d("document", document.id)
                    transactions.add(document.toObject<TransactionModel>())
                    Log.d("document", transactions.last().toString())
                }

                Log.d("size of transaction on success", transactions.size.toString())
            }
            .addOnFailureListener{e ->
                Log.e("ERROR GETTING DATA", e.message.toString())
            }

        Log.d("size of transaction", transactions.size.toString())

        return transactions
    }

    */

}