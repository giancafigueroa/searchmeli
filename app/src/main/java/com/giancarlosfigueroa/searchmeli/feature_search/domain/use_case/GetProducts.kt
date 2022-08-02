package com.giancarlosfigueroa.searchmeli.feature_search.domain.use_case

import com.giancarlosfigueroa.searchmeli.feature_search.domain.model.Product
import com.giancarlosfigueroa.searchmeli.feature_search.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class GetProducts(
    private val repository:ProductRepository
) {
    operator fun invoke():Flow<List<Product>>{
        return  repository.getProducts()
    }
}