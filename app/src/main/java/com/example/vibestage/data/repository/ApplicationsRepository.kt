package com.example.vibestage.data.repository

import com.example.vibestage.data.local.TokenManager
import com.example.vibestage.data.model.Application
import com.example.vibestage.data.model.CreateApplicationRequest
import com.example.vibestage.data.remote.ApiService
import com.example.vibestage.data.remote.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ApplicationsRepository(private val tokenManager: TokenManager) {

    private val apiService: ApiService by lazy {
        RetrofitClient.getClient(tokenManager).create(ApiService::class.java)
    }

    fun createApplication(eventId: Int, message: String): Flow<Resource<Application>> = flow {
        try {
            emit(Resource.Loading())

            val request = CreateApplicationRequest(eventId = eventId, message = message)
            val response = apiService.createApplication(request)

            if (response.isSuccessful && response.body() != null) {
                emit(Resource.Success(response.body()!!))
            } else {
                emit(Resource.Error(response.message() ?: "Error al crear postulación"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Error de conexión"))
        }
    }.flowOn(Dispatchers.IO)

    fun getMyApplications(): Flow<Resource<List<Application>>> = flow {
        try {
            emit(Resource.Loading())

            val response = apiService.getMyApplications()

            if (response.isSuccessful && response.body() != null) {
                emit(Resource.Success(response.body()!!))
            } else {
                emit(Resource.Error(response.message() ?: "Error al obtener postulaciones"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Error de conexión"))
        }
    }.flowOn(Dispatchers.IO)

    fun deleteApplication(id: Int): Flow<Resource<String>> = flow {
        try {
            emit(Resource.Loading())

            val response = apiService.deleteApplication(id)

            if (response.isSuccessful) {
                val message = response.body()?.message ?: "Postulación eliminada"
                emit(Resource.Success(message))
            } else {
                emit(Resource.Error(response.message() ?: "Error al eliminar postulación"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Error de conexión"))
        }
    }.flowOn(Dispatchers.IO)
}
