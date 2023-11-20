package com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.models

class TransactionModel {

    var id: String = ""
    var borrower: String = ""
    var station: String = ""
    var status: String = ""
    var transactionDate: String = ""
    var expectedDate: String = ""
    var actualReturnDate: String = ""
    var requestedItems: List<ItemModel> = mutableListOf()
    var requestNote: String = ""
    var returnNote: String = ""

    constructor()
    constructor(
        id: String,
        borrower: String,
        station: String,
        status: String,
        transactionDate: String,
        expectedDate: String,
        actualReturnDate: String,
        requestedItems: List<ItemModel>,
        requestNote: String,
        returnNote: String
    ) {
        this.id = id
        this.borrower = borrower
        this.station = station
        this.status = status
        this.transactionDate = transactionDate
        this.expectedDate = expectedDate
        this.actualReturnDate = actualReturnDate
        this.requestedItems = requestedItems
        this.requestNote = requestNote
        this.returnNote = returnNote
    }


}