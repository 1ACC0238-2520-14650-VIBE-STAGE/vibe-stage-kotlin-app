package com.example.vibestage.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Application(
    @Json(name = "id") val id: Int,
    @Json(name = "artistId") val artistId: Int,
    @Json(name = "eventId") val eventId: Int,
    @Json(name = "message") val message: String,
    @Json(name = "status") val status: String,
    @Json(name = "createdAt") val createdAt: String?
)

@JsonClass(generateAdapter = true)
data class CreateApplicationRequest(
    @Json(name = "eventId") val eventId: Int,
    @Json(name = "message") val message: String
)

@JsonClass(generateAdapter = true)
data class ApplicationResponse(
    @Json(name = "id") val id: Int,
    @Json(name = "message") val message: String?,
    @Json(name = "application") val application: Application?
)

