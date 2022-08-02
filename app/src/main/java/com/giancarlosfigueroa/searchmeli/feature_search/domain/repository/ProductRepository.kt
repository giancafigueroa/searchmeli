package com.giancarlosfigueroa.searchmeli.feature_search.domain.repository

import com.giancarlosfigueroa.searchmeli.feature_search.domain.model.Product


interface ProductRepository {
    suspend fun searchProducts(searchValue: String):List<Product>
    suspend fun getProductById(id:String): Product?
}