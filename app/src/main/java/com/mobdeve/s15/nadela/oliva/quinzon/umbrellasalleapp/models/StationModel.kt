package com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.models

class StationModel {

    var id: String = ""
    var admins: List<UserModel> = mutableListOf()
    var stock: List<StockItemModel> = mutableListOf()
    var existingTransactions: List<TransactionModel> = mutableListOf()

    constructor()



}