package com.example.vibestage.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ShowsResponse(
    @Json(name = "shows") val shows: List<Show>,
    @Json(name = "total") val total: Int,
    @Json(name = "page") val page: Int,
    @Json(name = "limit") val limit: Int
)

@JsonClass(generateAdapter = true)
data class Show(
    @Json(name = "id") val id: Int,
    @Json(name = "title") val title: String,
    @Json(name = "description") val description: String,
    @Json(name = "genre") val genre: String,
    @Json(name = "location") val location: String,
    @Json(name = "date") val date: String,
    @Json(name = "budget") val budget: Double?,
    @Json(name = "promoterId") val promoterId: Int,
    @Json(name = "imageUrl") val imageUrl: String?,
    @Json(name = "status") val status: String,
    @Json(name = "createdAt") val createdAt: String?
)

