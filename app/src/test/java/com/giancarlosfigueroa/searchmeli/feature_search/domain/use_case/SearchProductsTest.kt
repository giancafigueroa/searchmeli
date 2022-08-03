package com.giancarlosfigueroa.searchmeli.feature_search.domain.use_case

import com.giancarlosfigueroa.searchmeli.feature_search.data.repository.FakeProductRepository
import com.giancarlosfigueroa.searchmeli.feature_search.domain.exceptions.InvalidSearchValue
import com.giancarlosfigueroa.searchmeli.feature_search.domain.model.Product
import com.giancarlosfigueroa.searchmeli.feature_search.domain.model.Shipping
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class SearchProductsTest {

    private lateinit var searchProducts: SearchProducts
    private lateinit var fakeProductRepository: FakeProductRepository

    @Before
    fun setUp() {
        fakeProductRepository = FakeProductRepository()
        searchProducts = SearchProducts(fakeProductRepository)
        (0..9).forEach {

            fakeProductRepository.addProductFake(
                Product(
                    id = it.toString(),
                    title = "Item $it",
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
    fun `Search product with name item`() = runBlocking {
        val products = searchProducts("item")
        assert(products.size == 10)
    }

    @Test
    fun `Search product with name not exist`() = runBlocking {
        val products = searchProducts("Gian")
        assert(products.isEmpty())
    }

    @Test
    fun `Search product with blank value`() {
        assertThrows(InvalidSearchValue::class.java) {
            runBlocking {
                searchProducts("   ")
            }
        }
    }

}