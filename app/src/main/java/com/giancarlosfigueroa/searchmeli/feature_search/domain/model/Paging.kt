package com.giancarlosfigueroa.searchmeli.feature_search.domain.model

import kotlinx.serialization.Serializable



@Serializable
data class Paging (

	val total : Int,
	val primary_results : Int,
	val offset : Int,
	val limit : Int
)