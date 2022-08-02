package com.giancarlosfigueroa.searchmeli.feature_search.data.remote

import com.giancarlosfigueroa.searchmeli.feature_search.data.remote.dto.ResponseSearch
import com.giancarlosfigueroa.searchmeli.feature_search.domain.model.Product
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*

interface SearchService {
    suspend fun search(value: String): ResponseSearch?
    suspend fun searchById(id: String): Product?
}