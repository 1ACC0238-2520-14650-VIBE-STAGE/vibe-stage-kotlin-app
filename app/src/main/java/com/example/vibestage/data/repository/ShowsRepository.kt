package com.example.vibestage.data.repository

import com.example.vibestage.data.local.TokenManager
import com.example.vibestage.data.model.Show
import com.example.vibestage.data.remote.ApiService
import com.example.vibestage.data.remote.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ShowsRepository(private val tokenManager: TokenManager) {

    private val apiService: ApiService by lazy {
        RetrofitClient.getClient(tokenManager).create(ApiService::class.java)
    }

    fun getShows(
        genre: String? = null,
        location: String? = null,
        dateFrom: String? = null,
        dateTo: String? = null,
        page: Int? = null,
        limit: Int? = null
    ): Flow<Resource<List<Show>>> = flow {
        try {
            emit(Resource.Loading())

            val response = apiService.getShows(genre, location, dateFrom, dateTo, page, limit)

            if (response.isSuccessful && response.body() != null) {
                emit(Resource.Success(response.body()!!))
            } else {
                emit(Resource.Error(response.message() ?: "Error al obtener shows"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Error de conexión"))
        }
    }.flowOn(Dispatchers.IO)

    fun getShowById(id: Int): Flow<Resource<Show>> = flow {
        try {
            emit(Resource.Loading())

            val response = apiService.getShowById(id)

            if (response.isSuccessful && response.body() != null) {
                emit(Resource.Success(response.body()!!))
            } else {
                emit(Resource.Error(response.message() ?: "Show no encontrado"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Error de conexión"))
        }
    }.flowOn(Dispatchers.IO)
}
