package com.giancarlosfigueroa.searchmeli.feature_search.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey val id:Int?=null,
    val name:String
) {
}