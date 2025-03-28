package com.constructionhub.frontend.service

import com.constructionhub.frontend.model.Project
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

object ProjectService {

    private const val BASE_URL = "http://localhost:8081/api/projects"

    suspend fun getProjects(): List<Project> {
        val client = AuthService.getAuthenticatedClient()
        return client.get(BASE_URL).body()
    }
}
