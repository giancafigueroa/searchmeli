package com.giancarlosfigueroa.searchmeli.feature_search.domain.use_case

import com.giancarlosfigueroa.searchmeli.feature_search.data.repository.FakeProductRepository
import com.giancarlosfigueroa.searchmeli.feature_search.domain.exceptions.InvalidId
import com.giancarlosfigueroa.searchmeli.feature_search.domain.model.Product
import com.giancarlosfigueroa.searchmeli.feature_search.domain.model.Shipping
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetProductByIdTest{
    private lateinit var getProductById: GetProductById
    private lateinit var fakeProductRepository: FakeProductRepository

    @Before
    fun setUp() {
        fakeProductRepository = FakeProductRepository()
        getProductById = GetProductById(fakeProductRepository)
        (0..9).forEach {

            fakeProductRepository.addProductFake(
                Product(
                    id = it.toString(),
                    title = "item $it",
                    price = 20000,
                    thumbnail = "",
                    shipping = Shipping(
                        true
                    ),
                    attributes = emptyList(),
                    address = null,
                    original_price = null,
                    pictures = null
                )
            )
        }
    }

    @Test
    fun `Search product by id 2`() = runBlocking {
        val product = getProductById("2")
        assert(product?.title=="item 2")
    }

    @Test
    fun `Search product with blank value`() {
        assertThrows(InvalidId::class.java) {
            runBlocking {
                getProductById("")
            }
        }
    }
    @Test
    fun `Search product with name not exist`()=runBlocking {
        assertNull(getProductById("Gian"))
    }

}