package com.giancarlosfigueroa.searchmeli.feature_search.domain.use_case

import com.giancarlosfigueroa.searchmeli.feature_search.domain.exceptions.InvalidId
import com.giancarlosfigueroa.searchmeli.feature_search.domain.exceptions.InvalidSearchValue
import com.giancarlosfigueroa.searchmeli.feature_search.domain.model.Product
import com.giancarlosfigueroa.searchmeli.feature_search.domain.repository.ProductRepository
import kotlin.jvm.Throws

class GetProductById(
    private val repository: ProductRepository
) {
    @Throws(InvalidId::class)
    suspend operator fun invoke(id:String): Product? {
        if(id.isBlank()){
            throw InvalidId("Id is Blank")
        }
        return  repository.getProductById(id)
    }
}