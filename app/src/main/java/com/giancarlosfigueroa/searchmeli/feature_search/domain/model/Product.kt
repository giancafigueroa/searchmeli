package com.giancarlosfigueroa.searchmeli.feature_search.domain.model

import kotlinx.serialization.Serializable



@Serializable
data class Product (

	val id : String,
	val site_id : String,
	val title : String,
	val price : Int,
	val original_price : Int?,
	val available_quantity : Int,
	val sold_quantity : Int,
	val buying_mode : String,
	val listing_type_id : String,
	val stop_time : String,
	val condition : String,
	val permalink : String,
	val thumbnail : String,
	val thumbnail_id : String,
	val accepts_mercadopago : Boolean,
	val address : Address?,
	val shipping : Shipping,
	val attributes : List<Attributes>,
	val pictures : List<Picture>?,


)