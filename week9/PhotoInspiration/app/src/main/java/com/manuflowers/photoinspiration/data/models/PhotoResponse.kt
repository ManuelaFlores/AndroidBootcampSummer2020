package com.manuflowers.photoinspiration.data.models

import com.squareup.moshi.Json

/**
 * PhotoResponse, the model retrieved from the server
 **/
data class PhotoResponse(
    @field:Json(name = "id") val id: String,
    @field:Json(name = "created_at")  val created_at: String?,
    @field:Json(name = "alt_description") val alt_description: String?,
    @field:Json(name = "urls") val urls: Urls,
    @field:Json(name = "user") val user: User
)

data class Urls(
    @field:Json(name = "regular") val regular: String,
    @field:Json(name = "small") val small: String
)

data class User(
    @field:Json(name = "name") val name: String,
    @field:Json(name = "profile_image") val profile_image: ProfileImage,
    @field:Json(name = "bio") val bio: String?,
    @field:Json(name = "location") val location: String?,
    @field:Json(name = "links") val links: Links
)

data class ProfileImage(
    @field:Json(name = "medium") val medium: String
)

data class Links(
    @field:Json(name = "html") val html: String?
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