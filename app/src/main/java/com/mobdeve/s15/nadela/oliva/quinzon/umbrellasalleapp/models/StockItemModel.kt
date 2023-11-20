package com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.models

class StockItemModel {

    var name: String = ""
    var available: Int = 0
    var totalStock : Int = 0
    var borrowedCount : Int = 0

    constructor(name: String, available: Int, totalStock: Int, borrowedCount: Int){
        this.name = name
        this.available = available
        this.totalStock = totalStock
        this.borrowedCount = borrowedCount
    }

    constructor()

}