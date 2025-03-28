package com.constructionhub.frontend.service

import com.constructionhub.frontend.service.TokenStorage
import java.net.HttpURLConnection
import java.net.URL

object ApiService {

    fun getProtectedResource(): String? {
        val token = TokenStorage.getToken() ?: return "Token não encontrado"

        val url = URL("http://localhost:8081/api/auth/protected") // ou o endpoint correto no seu backend
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        connection.setRequestProperty("Authorization", "Bearer $token")

        return if (connection.responseCode == 200) {
            connection.inputStream.bufferedReader().use { it.readText() }
        } else {
            "Erro: ${connection.responseCode}"
        }
    }
}
