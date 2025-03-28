package com.constructionhub.frontend.service

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

import com.constructionhub.frontend.model.LoginRequestDTO
import com.constructionhub.frontend.model.LoginResponseDTO
import io.ktor.client.plugins.*


object AuthService {

    private const val BASE_URL = "http://localhost:8081/api/auth"
    private var jwtToken: String? = null

    // Cliente HTTP configurado para serializar JSON e logar as chamadas
    // HTTP client configured to serialize JSON and log requests
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
        install(Logging) {
            level = LogLevel.INFO
        }
        install(HttpTimeout) {
            requestTimeoutMillis = 5000
        }
    }

    // Realiza o login e armazena o token JWT em memoria
    // Performs login and stores the JWT token in memory
    suspend fun login(username: String, password: String): Boolean = withContext(Dispatchers.IO) {
        try {
            val response: LoginResponseDTO = client.post("$BASE_URL/login") {
                contentType(ContentType.Application.Json)
                setBody(LoginRequestDTO(username, password))
            }.body()

            jwtToken = response.token
            println("Token recebido: $jwtToken")
            return@withContext true
        } catch (e: Exception) {
            e.printStackTrace()
            return@withContext false
        }
    }

    // Retorna o client com token no header Authorization: Bearer
    // Returns the client with token added to Authorization header
    fun getAuthenticatedClient(): HttpClient {
        return HttpClient {
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true })
            }
            defaultRequest {
                header(HttpHeaders.Authorization, "Bearer $jwtToken")
            }
            install(Logging) {
                level = LogLevel.BODY
            }
        }
    }

    fun isAuthenticated(): Boolean {
        return jwtToken != null
    }

    fun logout() {
        jwtToken = null
    }
}
