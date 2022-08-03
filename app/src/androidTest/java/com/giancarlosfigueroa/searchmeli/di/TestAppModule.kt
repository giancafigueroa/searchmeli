package com.giancarlosfigueroa.searchmeli.di

import com.giancarlosfigueroa.searchmeli.feature_search.data.remote.SearchServiceImpl
import com.giancarlosfigueroa.searchmeli.feature_search.data.repository.ProductRepositoryImpl
import com.giancarlosfigueroa.searchmeli.feature_search.domain.repository.ProductRepository
import com.giancarlosfigueroa.searchmeli.feature_search.domain.use_case.GetProductById
import com.giancarlosfigueroa.searchmeli.feature_search.domain.use_case.SearchProducts
import com.giancarlosfigueroa.searchmeli.feature_search.domain.use_case.ProductUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Singleton
    fun provideSearchServiceImpl(): SearchServiceImpl {
        return SearchServiceImpl(
            client = HttpClient(Android) {
                install(Logging) {
                    level = LogLevel.ALL
                }
                install(JsonFeature) {
                    serializer = KotlinxSerializer(json = kotlinx.serialization.json.Json {
                        ignoreUnknownKeys = true
                        explicitNulls = false

                    })
                }
            }
        )

    }

    @Provides
    @Singleton
    fun provideProductRepository(serviceImpl: SearchServiceImpl): ProductRepository {
        return ProductRepositoryImpl(serviceImpl)
    }

    @Provides
    @Singleton
    fun provideProductUsesCases(repository: ProductRepository): ProductUseCases {
        return ProductUseCases(
            searchProducts = SearchProducts(repository),
            getProductById = GetProductById(repository)
        )
    }
}