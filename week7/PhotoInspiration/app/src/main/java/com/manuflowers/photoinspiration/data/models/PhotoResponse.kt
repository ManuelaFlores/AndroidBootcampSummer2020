package com.manuflowers.photoinspiration.data.models

import kotlinx.serialization.Serializable

@Serializable
data class PhotoResponse(
    val id: String,
    val created_at: String
)