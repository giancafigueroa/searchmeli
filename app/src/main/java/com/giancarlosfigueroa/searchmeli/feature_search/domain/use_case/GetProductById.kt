package com.giancarlosfigueroa.searchmeli.feature_search.domain.use_case

import com.giancarlosfigueroa.searchmeli.feature_search.domain.model.Product
import com.giancarlosfigueroa.searchmeli.feature_search.domain.repository.ProductRepository

class GetProductById(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(id:String): Product? {
        return  repository.getProductById(id)
    }
}