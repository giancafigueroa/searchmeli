package com.giancarlosfigueroa.searchmeli.feature_search.domain.use_case

import com.giancarlosfigueroa.searchmeli.feature_search.domain.exceptions.InvalidSearchValue
import com.giancarlosfigueroa.searchmeli.feature_search.domain.model.Product
import com.giancarlosfigueroa.searchmeli.feature_search.domain.repository.ProductRepository
import kotlin.jvm.Throws

class SearchProducts(
    private val repository: ProductRepository
) {
    @Throws(InvalidSearchValue::class)
    suspend operator fun invoke(searchValue: String): List<Product> {
        if(searchValue.isBlank()){
            throw InvalidSearchValue("Valor is blank")
        }
        return repository.searchProducts(searchValue)
    }
}