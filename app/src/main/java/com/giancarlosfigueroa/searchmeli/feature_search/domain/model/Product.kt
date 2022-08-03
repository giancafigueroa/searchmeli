package com.giancarlosfigueroa.searchmeli.feature_search.domain.model

import kotlinx.serialization.Serializable



@Serializable
data class Product (

	val id : String,
	val title : String,
	val price : Int,
	val original_price : Int?,
	val thumbnail : String,
	val address : Address?,
	val shipping : Shipping,
	val attributes : List<Attributes>,
	val pictures : List<Picture>?,


)