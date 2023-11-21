package com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.databases

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class StockItemHelper {

    companion object {
        suspend fun getStudentTransactions(userID: String): MutableList<DocumentSnapshot> = withContext(
            Dispatchers.IO) {
            try {

                val db = FirebaseFirestore.getInstance()
                val querySnapshot =
                    db.collection("Stations").document("geaml6skGP9188rx75DU").collection("Stock").get().await()

                return@withContext querySnapshot.documents
            } catch (e: Exception) {
                // Handle exceptions
                return@withContext mutableListOf()
            }
        }
    }

}