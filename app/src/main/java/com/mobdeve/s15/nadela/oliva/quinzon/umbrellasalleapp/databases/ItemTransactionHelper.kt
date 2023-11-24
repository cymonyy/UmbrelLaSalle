package com.mobdeve.qrscannertemp.helper

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.toObject
import com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.models.ItemModel
import com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.models.TransactionModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


class ItemTransactionHelper {
    private val _data = MutableLiveData<TransactionModel>()
    val data: LiveData<TransactionModel>
            get() = _data

        @OptIn(DelicateCoroutinesApi::class)
        fun getTransactionForAdminForms(transactionID: String) {
            GlobalScope.launch(Dispatchers.Main) {
                try {

                    withContext(Dispatchers.IO){
                        val db = FirebaseFirestore.getInstance()

                        val document = db.collection("Transactions").document(transactionID).get().await()
                        val transaction: TransactionModel? = document.toObject<TransactionModel>()

                        if (transaction != null) {
                            transaction.id = transactionID
                            _data.postValue(transaction)
                        }
                    }
                } catch (e: FirebaseFirestoreException) {
                    // Handle Firestore-specific exceptions
                    return@launch
                }
            }
        }



    @OptIn(DelicateCoroutinesApi::class)
    fun updateTransactionStatus(transactionID: String, newStatus: String) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                withContext(Dispatchers.IO) {
                    val db = FirebaseFirestore.getInstance()

                    // Retrieve the transaction document
                    val transactionDocument = db.collection("Transactions").document(transactionID)
                    val transaction = transactionDocument.get().await().toObject<TransactionModel>()

                    if (transaction != null) {
                        // Update the status in the transaction model
                        transaction.status = newStatus

                        // Update the transaction document in Firestore
                        transactionDocument.set(transaction).await()
                    }
                }
            } catch (e: Exception) {
                // Handle exceptions
                e.printStackTrace()
            }
        }
    }

    companion object {
        suspend fun getItemWithStatus(item: String): ItemModel? = withContext(
            Dispatchers.IO) {
            try {
                val db = FirebaseFirestore.getInstance()

                val document = db.collection("Items").document(item).get().await()
                val item = document.toObject<ItemModel>()
                if (item != null) {
                    item.id = document.id
                }

                return@withContext item
            } catch (e: Exception) {
                // Handle exceptions
                return@withContext null
            }

        }

        @OptIn(DelicateCoroutinesApi::class)
        suspend fun updateItemsStatus(itemID: String, newStatus: String) {
            GlobalScope.launch(Dispatchers.Main) {
                try {
                    withContext(Dispatchers.IO) {
                        val db = FirebaseFirestore.getInstance()

                        // Retrieve the item document
                        val itemDocument = db.collection("Items").document(itemID)
                        val item = itemDocument.get().await().toObject<ItemModel>()

                        if (item != null) {
                            // Update the status in the item model
                            item.status = newStatus

                            // Update the item document in Firestore
                            itemDocument.set(item).await()
                        }
                    }
                } catch (e: Exception) {
                    // Handle exceptions
                    e.printStackTrace()
                }
            }
        }
    }

}






