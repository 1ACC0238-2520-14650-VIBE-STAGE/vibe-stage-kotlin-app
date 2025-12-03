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
        @Query("dateFrom") dateFrom: String? = null,
        @Query("dateTo") dateTo: String? = null,
        @Query("page") page: Int? = null,
        @Query("limit") limit: Int? = null
    ): Response<List<Show>>  // El backend retorna un array directamente

    @GET("shows/{id}")
    suspend fun getShowById(@Path("id") id: Int): Response<Show>

    @POST("shows")
    suspend fun createShow(@Body request: CreateShowRequest): Response<Show>

    @PUT("shows/{id}")
    suspend fun updateShow(
        @Path("id") id: Int,
        @Body request: UpdateShowRequest
    ): Response<Show>

    @DELETE("shows/{id}")
    suspend fun deleteShow(@Path("id") id: Int): Response<MessageResponse>

    // Applications endpoints (requiere autenticación)
    @POST("applications")
    suspend fun createApplication(@Body request: CreateApplicationRequest): Response<Application>

    @GET("applications")
    suspend fun getMyApplications(): Response<List<Application>>

    @GET("applications/event/{eventId}")
    suspend fun getApplicationsByEvent(@Path("eventId") eventId: Int): Response<List<Application>>

    @PUT("applications/{id}/accept")
    suspend fun acceptApplication(@Path("id") id: Int): Response<Application>

    @PUT("applications/{id}/reject")
    suspend fun rejectApplication(@Path("id") id: Int): Response<Application>

    @DELETE("applications/{id}")
    suspend fun deleteApplication(@Path("id") id: Int): Response<MessageResponse>
}
