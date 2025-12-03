package com.example.vibestage.data.remote

object ApiConfig {
    // IMPORTANTE: Cambia esta URL según donde esté corriendo tu backend

    // OPCIÓN 1: Backend local en tu PC con emulador Android
    // const val BASE_URL = "http://10.0.2.2:3000/"

    // OPCIÓN 2: Backend local en tu PC con dispositivo físico (usa tu IP local)
    // Ejecuta en CMD: ipconfig y busca tu IPv4 (ej: 192.168.1.100)
    // const val BASE_URL = "http://192.168.1.100:3000/"

    // OPCIÓN 3: Backend desplegado en Railway (RECOMENDADO para pruebas)
    // Necesitas desplegar tu backend en Railway y obtener la URL pública
    // Por ahora usaremos localhost, pero deberás cambiar esto cuando despliegues
    const val BASE_URL = "http://10.0.2.2:3000/"

    // Si vas a usar Railway, la URL será algo como:
    // const val BASE_URL = "https://vibe-stage-backend-production.up.railway.app/"

    // Timeouts en segundos
    const val CONNECT_TIMEOUT = 30L
    const val READ_TIMEOUT = 30L
    const val WRITE_TIMEOUT = 30L
}
