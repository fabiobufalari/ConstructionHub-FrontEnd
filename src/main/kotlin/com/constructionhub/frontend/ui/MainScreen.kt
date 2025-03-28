package com.constructionhub.frontend.ui

import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import com.constructionhub.frontend.screens.WeatherScreen

@Composable
fun MainScreen() {
    var currentScreen by remember { mutableStateOf("weather") }
    var preferredName by remember { mutableStateOf("Fábio") }
    var cityName by remember { mutableStateOf("Halifax") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Bem-vindo(a), $preferredName!", style = MaterialTheme.typography.titleLarge)
            // Welcome message with preferred name
            Text(text = "Welcome, $preferredName!", style = MaterialTheme.typography.titleSmall)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = preferredName,
                onValueChange = { preferredName = it },
                label = { Text("Nome preferido / Preferred name") },
                modifier = Modifier.weight(1f).padding(end = 8.dp)
            )
            OutlinedTextField(
                value = cityName,
                onValueChange = { cityName = it },
                label = { Text("Cidade / City") },
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (currentScreen) {
            "weather" -> WeatherScreen(cityName = cityName, preferredName = preferredName)
            // Aqui no futuro podemos adicionar mais telas como "projetos" etc
        }
    }
}
