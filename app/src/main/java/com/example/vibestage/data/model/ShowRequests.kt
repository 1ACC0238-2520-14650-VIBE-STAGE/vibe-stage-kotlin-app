package com.example.vibestage.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreateShowRequest(
    @Json(name = "title") val title: String,
    @Json(name = "description") val description: String,
    @Json(name = "location") val location: String,
    @Json(name = "date") val date: String,
    @Json(name = "genre") val genre: String?,
    @Json(name = "artistId") val artistId: Int?,
    @Json(name = "eventId") val eventId: Int?
)

@JsonClass(generateAdapter = true)
data class UpdateShowRequest(
    @Json(name = "title") val title: String?,
    @Json(name = "description") val description: String?,
    @Json(name = "location") val location: String?,
    @Json(name = "date") val date: String?,
    @Json(name = "genre") val genre: String?,
    @Json(name = "isAvailable") val isAvailable: Boolean?
)

@JsonClass(generateAdapter = true)
data class MessageResponse(
    @Json(name = "message") val message: String
)

