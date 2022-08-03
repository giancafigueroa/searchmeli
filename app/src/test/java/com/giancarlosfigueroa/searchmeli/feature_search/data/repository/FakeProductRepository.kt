package com.giancarlosfigueroa.searchmeli.feature_search.data.repository

import com.giancarlosfigueroa.searchmeli.feature_search.domain.model.Product
import com.giancarlosfigueroa.searchmeli.feature_search.domain.repository.ProductRepository

class FakeProductRepository :ProductRepository{
    private val products= mutableListOf<Product>()
    fun addProductFake(product: Product){
        products.add(product)
    }
    override suspend fun searchProducts(searchValue: String): List<Product> {
        return products.filter { it.title.uppercase()
            .contains(searchValue.uppercase()) }
    }

    override suspend fun getProductById(id: String): Product? {
        return products.find { it.id==id }
    }
}