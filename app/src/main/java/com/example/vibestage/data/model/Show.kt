package com.example.vibestage.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ShowsResponse(
    @Json(name = "shows") val shows: List<Show>? = null,
    @Json(name = "total") val total: Int? = null,
    @Json(name = "page") val page: Int? = null,
    @Json(name = "limit") val limit: Int? = null
)

@JsonClass(generateAdapter = true)
data class Show(
    @Json(name = "id") val id: Int,
    @Json(name = "title") val title: String,
    @Json(name = "description") val description: String,
    @Json(name = "location") val location: String,
    @Json(name = "date") val date: String,
    @Json(name = "genre") val genre: String?,
    @Json(name = "isAvailable") val isAvailable: Boolean? = true,
    @Json(name = "artistId") val artistId: Int?,
    @Json(name = "eventId") val eventId: Int?,
    @Json(name = "promoter") val promoter: User? = null
)
