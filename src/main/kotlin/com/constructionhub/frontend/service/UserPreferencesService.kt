package com.constructionhub.frontend.service

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.File

object UserPreferencesService {

    private val file = File("user_prefs.json")

    @Serializable
    data class Preferences(
        val displayName: String = "Visitante" // valor padrão
    )

    private var preferences: Preferences = loadPreferences()

    private fun loadPreferences(): Preferences {
        return if (file.exists()) {
            try {
                val json = file.readText()
                Json.decodeFromString(json)
            } catch (e: Exception) {
                Preferences()
            }
        } else {
            Preferences()
        }
    }

    fun getDisplayName(): String {
        return preferences.displayName
    }

    fun setDisplayName(name: String) {
        preferences = preferences.copy(displayName = name)
        savePreferences()
    }

    private fun savePreferences() {
        try {
            val json = Json.encodeToString(preferences)
            file.writeText(json)
        } catch (e: Exception) {
            println("Erro ao salvar preferências: ${e.message}")
        }
    }
}
