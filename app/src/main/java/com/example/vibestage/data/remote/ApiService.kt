package com.example.vibestage.data.remote

import com.example.vibestage.data.model.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    // Auth endpoints
    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<AuthResponse>

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<AuthResponse>

    // Shows endpoints
    @GET("shows")
    suspend fun getShows(
        @Query("genre") genre: String? = null,
        @Query("location") location: String? = null,
        @Query("page") page: Int? = null,
        @Query("limit") limit: Int? = null
    ): Response<ShowsResponse>

    @GET("shows/{id}")
    suspend fun getShowById(@Path("id") id: Int): Response<Show>

    // Applications endpoints (requiere autenticación)
    @POST("applications")
    suspend fun createApplication(@Body request: CreateApplicationRequest): Response<ApplicationResponse>

    @GET("applications")
    suspend fun getMyApplications(): Response<List<Application>>

    @DELETE("applications/{id}")
    suspend fun deleteApplication(@Path("id") id: Int): Response<Map<String, String>>
}
