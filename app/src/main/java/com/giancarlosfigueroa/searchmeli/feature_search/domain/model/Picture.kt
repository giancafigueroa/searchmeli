package com.giancarlosfigueroa.searchmeli.feature_search.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Picture(
    val id: String,
    val url: String,
    val secure_url: String,
    val size: String,
    val max_size: String,
    val quality: String,
    )
