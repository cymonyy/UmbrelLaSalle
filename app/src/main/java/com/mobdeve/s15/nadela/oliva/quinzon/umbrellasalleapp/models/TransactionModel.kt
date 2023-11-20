package com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.models

class TransactionModel {

    var id: String = ""
    var borrower: String = ""
    var station: String = ""
    var status: String = ""
    var transaction_date: String = ""
    var expected_date: String = ""
    var actual_return_date: String = ""
    var requested_items: List<ItemModel> = mutableListOf()
    var request_note: String = ""
    var return_note: String = ""

}