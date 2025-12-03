package com.example.vibestage.data.remote

object ApiConfig {
    // Cambia esta URL por la IP de tu backend
    // Si usas emulador: http://10.0.2.2:3000
    // Si usas dispositivo físico en misma red: http://TU_IP_LOCAL:3000
    // Si usas backend en producción: https://tu-dominio.com
    const val BASE_URL = "http://10.0.2.2:3000/"

    // Timeouts en segundos
    const val CONNECT_TIMEOUT = 30L
    const val READ_TIMEOUT = 30L
    const val WRITE_TIMEOUT = 30L
}

