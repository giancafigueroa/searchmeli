package com.giancarlosfigueroa.searchmeli.feature_search.data.remote

import com.giancarlosfigueroa.searchmeli.feature_search.data.remote.dto.ResponseSearch
import com.giancarlosfigueroa.searchmeli.feature_search.domain.model.Product
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*

class SearchServiceImpl(
    private val client: HttpClient
) : SearchService {
    override suspend fun search(value: String):ResponseSearch? {
        return try {

            client.get {
                url(HttpRoutes.SEARCH)
                parameter("q", value)
            }
        } catch (e: RedirectResponseException) {
            //3xx - response
            println("Error 300")
            null
        } catch (e: ClientRequestException) {
            //4xx - response
            println("Error 400")
            null
        } catch (e: ServerResponseException) {
            //5xx - response
            println("Error 500")
            null
        } catch (e:Exception){
            null
        }
    }

    override suspend fun searchById(id: String): Product? {
        return try {

            client.get {
                url(HttpRoutes.GET_ITEMS+"/${id}")
            }
        } catch (e: RedirectResponseException) {
            //3xx - response
            println("Error 300")
            null
        } catch (e: ClientRequestException) {
            //4xx - response
            println("Error 400")
            null
        } catch (e: ServerResponseException) {
            //5xx - response
            println("Error 500")
            null
        } catch (e:Exception){
            null
        }
    }


}