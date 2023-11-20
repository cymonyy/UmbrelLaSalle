package com.mobdeve.s15.nadela.oliva.quinzon.umbrellasalleapp.models

class ItemModel {

    var id: String = ""
    var borrowed: Boolean = false
    var status: String = ""
    var requested: Boolean = false

    constructor(id: String, borrowed: Boolean, status: String, requested: Boolean) {
        this.id = id
        this.borrowed = borrowed
        this.status = status
        this.requested = requested
    }

    constructor()

}