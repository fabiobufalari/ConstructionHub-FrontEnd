package com.constructionhub.frontend.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseDTO(
    val token: String,
    val username: String,
    val role: String
)
