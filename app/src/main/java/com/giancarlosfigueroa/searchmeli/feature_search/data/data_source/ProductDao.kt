package com.giancarlosfigueroa.searchmeli.feature_search.data.data_source

import androidx.room.Dao
import androidx.room.Query
import com.giancarlosfigueroa.searchmeli.feature_search.domain.model.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Query("SELECT * FROM product")
    fun getProducts():Flow<List<Product>>

    @Query(value="SELECT * FROM product WHERE id=:id")
    suspend  fun getProductById(id:Int):Product?
}