package com.giancarlosfigueroa.searchmeli.feature_search.data.repository

import com.giancarlosfigueroa.searchmeli.feature_search.data.data_source.ProductDao
import com.giancarlosfigueroa.searchmeli.feature_search.domain.model.Product
import com.giancarlosfigueroa.searchmeli.feature_search.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class ProductRepositoryImpl(
    private val dao:ProductDao
):ProductRepository {
    override fun getProducts(): Flow<List<Product>> {
        return dao.getProducts()
    }

    override suspend fun getProductById(id: Int): Product? {
       return dao.getProductById(id)
    }
}