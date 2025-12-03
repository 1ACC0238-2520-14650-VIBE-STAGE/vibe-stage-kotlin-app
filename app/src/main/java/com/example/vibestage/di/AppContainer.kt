package com.example.vibestage.di

import android.content.Context
import com.example.vibestage.data.local.TokenManager
import com.example.vibestage.data.repository.ApplicationsRepository
import com.example.vibestage.data.repository.AuthRepository
import com.example.vibestage.data.repository.ShowsRepository
import com.example.vibestage.presentation.artist.ApplicationsViewModel
import com.example.vibestage.presentation.artist.ShowsViewModel
import com.example.vibestage.presentation.auth.AuthViewModel

/**
 * Objeto singleton para proveer dependencias de manera simple
 * En una app más compleja, considera usar Hilt o Koin
 */
object AppContainer {

    private var tokenManager: TokenManager? = null
    private var authRepository: AuthRepository? = null
    private var showsRepository: ShowsRepository? = null
    private var applicationsRepository: ApplicationsRepository? = null

    fun initialize(context: Context) {
        tokenManager = TokenManager(context.applicationContext)
        authRepository = AuthRepository(tokenManager!!)
        showsRepository = ShowsRepository(tokenManager!!)
        applicationsRepository = ApplicationsRepository(tokenManager!!)
    }

    fun provideAuthViewModel(): AuthViewModel {
        checkInitialization()
        return AuthViewModel(authRepository!!)
    }

    fun provideShowsViewModel(): ShowsViewModel {
        checkInitialization()
        return ShowsViewModel(showsRepository!!)
    }

    fun provideApplicationsViewModel(): ApplicationsViewModel {
        checkInitialization()
        return ApplicationsViewModel(applicationsRepository!!)
    }

    fun provideTokenManager(): TokenManager {
        checkInitialization()
        return tokenManager!!
    }

    private fun checkInitialization() {
        if (tokenManager == null) {
            throw IllegalStateException("AppContainer no ha sido inicializado. Llama a initialize() primero.")
        }
    }
}

