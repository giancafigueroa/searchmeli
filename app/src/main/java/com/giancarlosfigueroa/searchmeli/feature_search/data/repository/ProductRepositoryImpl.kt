package com.giancarlosfigueroa.searchmeli.feature_search.data.repository


import com.giancarlosfigueroa.searchmeli.feature_search.data.remote.SearchService
import com.giancarlosfigueroa.searchmeli.feature_search.domain.model.Product
import com.giancarlosfigueroa.searchmeli.feature_search.domain.repository.ProductRepository

class ProductRepositoryImpl(
    private val searchService: SearchService
):ProductRepository {
    override suspend fun searchProducts(searchValue:String): List<Product> {
        return searchService.search(searchValue)?.results ?: emptyList()

    }

    override suspend fun getProductById(id: String): Product? {
        return searchService.searchById(id)
    }


}