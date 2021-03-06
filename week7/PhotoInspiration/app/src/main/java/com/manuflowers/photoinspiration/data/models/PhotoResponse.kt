package com.manuflowers.photoinspiration.data.models

import kotlinx.serialization.Serializable

/**
 * PhotoResponse, the model retrieved from the server
 **/
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
    val profile_image: ProfileImage,
    val bio: String?,
    val location: String?,
    val links: Links
)

@Serializable
data class ProfileImage(
    val medium: String
)

@Serializable
data class Links(
    val html: String?
)

/**
 * Transforms PhotoResponse model to PhotoEntity model, the model for our database
 **/
fun PhotoResponse.asPhotoEntity(): PhotoEntity = PhotoEntity(
    id = id,
    createdAt = created_at,
    altDescription = alt_description,
    smallUrl = urls.small,
    regularUrl = urls.regular,
    userName = user.name,
    userProfileImage = user.profile_image.medium,
    userBio = user.bio,
    userLocation = user.location,
    userProfileWeb = user.links.html
)