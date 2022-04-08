package com.example.adiblarappp.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "adib")
class Adib : Serializable{
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
    var name: String = ""
    var years: String = ""
    var type: String = ""
    var desc: String = ""
    var image: String = ""
    var isSaved: Boolean? = null

    constructor()

    constructor(
        id: Int?,
        name: String,
        years: String,
        type: String,
        desc: String,
        image: String,
        isSaved: Boolean?
    ) {
        this.id = id
        this.name = name
        this.years = years
        this.type = type
        this.desc = desc
        this.image = image
        this.isSaved = isSaved
    }

    constructor(
        name: String,
        years: String,
        type: String,
        desc: String,
        image: String,
        isSaved: Boolean?
    ) {
        this.name = name
        this.years = years
        this.type = type
        this.desc = desc
        this.image = image
        this.isSaved = isSaved
    }

    override fun toString(): String {
        return "Adib(id=$id, name='$name', years='$years', type='$type', desc='$desc', image='$image', isSaved=$isSaved)"
    }


}