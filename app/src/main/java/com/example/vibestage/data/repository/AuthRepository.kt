package com.example.vibestage.data.repository

import com.example.vibestage.data.local.TokenManager
import com.example.vibestage.data.model.AuthResponse
import com.example.vibestage.data.model.LoginRequest
import com.example.vibestage.data.model.RegisterRequest
import com.example.vibestage.data.remote.ApiService
import com.example.vibestage.data.remote.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class AuthRepository(private val tokenManager: TokenManager) {

    private val apiService: ApiService by lazy {
        RetrofitClient.getClient(tokenManager).create(ApiService::class.java)
    }

    fun register(name: String, email: String, password: String): Flow<Resource<AuthResponse>> = flow {
        try {
            emit(Resource.Loading())

            val request = RegisterRequest(
                name = name,
                email = email,
                password = password,
                role = "artist"
            )

            val response = apiService.register(request)

            if (response.isSuccessful && response.body() != null) {
                val authResponse = response.body()!!

                // Guardar token y datos del usuario
                val token = authResponse.accessToken ?: authResponse.token
                if (token != null) {
                    tokenManager.saveToken(token)
                    tokenManager.saveUserData(
                        userId = authResponse.user.id.toString(),
                        name = authResponse.user.name,
                        email = authResponse.user.email
                    )
                }

                emit(Resource.Success(authResponse))
            } else {
                emit(Resource.Error(response.message() ?: "Error al registrar usuario"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Error de conexión"))
        }
    }.flowOn(Dispatchers.IO)

    fun login(email: String, password: String): Flow<Resource<AuthResponse>> = flow {
        try {
            emit(Resource.Loading())

            val request = LoginRequest(email = email, password = password)
            val response = apiService.login(request)

            if (response.isSuccessful && response.body() != null) {
                val authResponse = response.body()!!

                // Guardar token y datos del usuario
                val token = authResponse.accessToken ?: authResponse.token
                if (token != null) {
                    tokenManager.saveToken(token)
                    tokenManager.saveUserData(
                        userId = authResponse.user.id.toString(),
                        name = authResponse.user.name,
                        email = authResponse.user.email
                    )
                }

                emit(Resource.Success(authResponse))
            } else {
                emit(Resource.Error(response.message() ?: "Credenciales inválidas"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Error de conexión"))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun logout() {
        tokenManager.clearAll()
        RetrofitClient.resetClient()
    }
}

