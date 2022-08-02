package com.giancarlosfigueroa.searchmeli.feature_search.domain.repository

import com.giancarlosfigueroa.searchmeli.feature_search.domain.model.Product
import kotlinx.coroutines.flow.Flow


interface ProductRepository {
    fun getProducts():Flow<List<Product>>
    suspend fun getProductById(id:Int):Product?
}