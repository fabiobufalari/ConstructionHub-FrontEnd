package com.constructionhub.frontend.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestDTO(
    val username: String,
    val password: String
)
