package com.giancarlosfigueroa.searchmeli.feature_search.domain.model

import kotlinx.serialization.Serializable


@Serializable
data class Attributes (

	val attribute_group_id : String,
	val attribute_group_name : String,
	val id : String,
	val name : String,
	val value_id : Int?,
	val value_name : String?,
)