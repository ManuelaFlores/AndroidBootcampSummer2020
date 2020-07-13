package com.manuflowers.photoinspiration.data.models

import kotlinx.serialization.Serializable

@Serializable
data class PhotoResponse(
    val id: String,
    val created_at: String?,
    val alt_description: String?,
    val urls: Urls,
    val user: User
)

@Serializable
data class Urls(
    val regular: String,
    val small: String
)

@Serializable
data class User(
    val name: String,
    val profile_image: ProfileImage
)

@Serializable
data class ProfileImage(
    val medium: String
)