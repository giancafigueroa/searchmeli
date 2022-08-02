package com.giancarlosfigueroa.searchmeli.feature_search.data.remote

import com.giancarlosfigueroa.searchmeli.feature_search.domain.model.Product
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import kotlin.text.get

class SearchServiceImpl(
    private val client: HttpClient
) : SearchService {
    override suspend fun search(value: String): List<Product> {
        return try {


            client.get {
                url(HttpRoutes.SEARCH)
                parameter("q", value)
            }
        } catch (e: RedirectResponseException) {
            //3xx - response
            println("Error 300")
            emptyList()
        } catch (e: ClientRequestException) {
            //4xx - response
            println("Error 400")
            emptyList()
        } catch (e: ServerResponseException) {
            //5xx - response
            println("Error 500")
            emptyList()
        } catch (e:Exception){
            emptyList()
        }
    }

}