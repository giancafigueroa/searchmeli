package com.giancarlosfigueroa.searchmeli.feature_search.domain.use_case

import com.giancarlosfigueroa.searchmeli.feature_search.domain.model.Product
import com.giancarlosfigueroa.searchmeli.feature_search.domain.repository.ProductRepository

class SearchProducts(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(searchValue: String): List<Product> {
        return repository.searchProducts(searchValue)
    }
}