package com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.databases

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class TransactionHelper {

    companion object {
        suspend fun getTransaction(): MutableList<DocumentSnapshot> = withContext(
            Dispatchers.IO) {
            try {

                val db = FirebaseFirestore.getInstance()
                val querySnapshot =
                    db.collection("Transactions").get().await()

                return@withContext querySnapshot.documents
            } catch (e: Exception) {
                // Handle exceptions
                return@withContext mutableListOf()
            }
        }
    }

}