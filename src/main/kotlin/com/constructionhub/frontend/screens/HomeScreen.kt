package com.constructionhub.frontend.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.constructionhub.frontend.model.WeatherDTO
import com.constructionhub.frontend.service.WeatherService
import com.constructionhub.frontend.session.SessionManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*
import com.constructionhub.frontend.service.WeatherService.getCurrentWeather




@Composable
fun HomeScreen(onLogout: () -> Unit) {
    val userName = remember { SessionManager.getPreferredName() ?: "Usuário" }
    val currentWeather = remember { mutableStateOf<WeatherDTO?>(null) }
    val forecastList = remember { mutableStateOf<List<WeatherDTO>>(emptyList()) }

    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            currentWeather.value = WeatherService.getCurrentWeather("Halifax")
            forecastList.value = WeatherService.get15DayForecast("Halifax")
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Olá, $userName!", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        currentWeather.value?.let { weather ->
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Clima atual em Halifax:", fontWeight = FontWeight.SemiBold)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("${weather.description}, ${weather.temperature}°C", fontSize = 20.sp)
                }
            }
        }

        if (forecastList.value.isNotEmpty()) {
            Text("Próximos 15 dias:", fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                forecastList.value.forEach { day ->
                    ForecastItem(day)
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(onClick = onLogout, modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text("Sair")
        }
    }
}

@Composable
fun ForecastItem(day: WeatherDTO) {
    val date = LocalDate.parse(day.date)
    val dayName = date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(dayName.capitalize(Locale.getDefault()), fontWeight = FontWeight.Bold)
                Text("${day.description}, ${day.temperature}°C")
            }
        }
    }
}
