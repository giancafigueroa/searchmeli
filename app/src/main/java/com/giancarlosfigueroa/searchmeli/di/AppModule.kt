package com.giancarlosfigueroa.searchmeli.di

import android.app.Application
import androidx.room.Room
import com.giancarlosfigueroa.searchmeli.feature_search.data.data_source.ProductDatabase
import com.giancarlosfigueroa.searchmeli.feature_search.data.repository.ProductRepositoryImpl
import com.giancarlosfigueroa.searchmeli.feature_search.domain.repository.ProductRepository
import com.giancarlosfigueroa.searchmeli.feature_search.domain.use_case.GetProductById
import com.giancarlosfigueroa.searchmeli.feature_search.domain.use_case.GetProducts
import com.giancarlosfigueroa.searchmeli.feature_search.domain.use_case.ProductUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
   @Provides
    @Singleton
    fun provideProductDatabase(app:Application):ProductDatabase{
        return Room.databaseBuilder(
            app,
            ProductDatabase::class.java,
            ProductDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideProductRepository(db:ProductDatabase):ProductRepository{
        return ProductRepositoryImpl(db.productDao)
    }
    @Provides
    @Singleton
    fun provideProductUsesCases(repository: ProductRepository):ProductUseCases{
        return ProductUseCases(
            getProduct = GetProducts(repository),
            getProductById = GetProductById(repository)
        )
    }
}