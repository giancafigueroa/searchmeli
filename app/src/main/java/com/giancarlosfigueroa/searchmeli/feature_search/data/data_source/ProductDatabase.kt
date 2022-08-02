package com.giancarlosfigueroa.searchmeli.feature_search.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.giancarlosfigueroa.searchmeli.feature_search.domain.model.Product

@Database(
    entities = [Product::class],
    version = 1
)
abstract class ProductDatabase:RoomDatabase() {
    abstract val productDao:ProductDao
    companion object{
        const val DATABASE_NAME="products_db"
    }
}