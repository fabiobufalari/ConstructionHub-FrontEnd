object Strings {
    var currentLanguage = "en"

    private val translations = mapOf(
        "login_title" to mapOf("en" to "Login", "pt" to "Entrar", "fr" to "Connexion"),
        "sign_in" to mapOf("en" to "Sign in", "pt" to "Entrar", "fr" to "Connexion"),
        "language" to mapOf("en" to "Language", "pt" to "Idioma", "fr" to "Langue")
    )

    fun t(key: String): String {
        return translations[key]?.get(currentLanguage) ?: key
    }
}
