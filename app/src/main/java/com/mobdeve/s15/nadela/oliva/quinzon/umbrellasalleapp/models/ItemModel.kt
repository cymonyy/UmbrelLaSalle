package com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.models

class ItemModel {

    var id: String = ""
    var borrowed: Boolean = false
    var status: String = "Intact"
    var requested: Boolean = false
    var itemCategory: String = ""
    var station: String = ""

    constructor(borrowed: Boolean, status: String, requested: Boolean, itemCategory: String, station: String) {
        this.borrowed = borrowed
        this.status = status
        this.requested = requested
        this.itemCategory = itemCategory
        this.station = station
    }

    constructor()

}