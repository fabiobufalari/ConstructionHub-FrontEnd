package com.constructionhub.frontend.screens

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.constructionhub.frontend.dtos.CurrentWeatherDTO
import com.constructionhub.frontend.dtos.ForecastItem
import com.constructionhub.frontend.service.WeatherService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Composable
fun WeatherScreen(city: String, preferredName: String) {
    val scope = rememberCoroutineScope()
    var currentWeather by remember { mutableStateOf<CurrentWeatherDTO?>(null) }
    var forecast by remember { mutableStateOf<List<ForecastItem>>(emptyList()) }

    LaunchedEffect(Unit) {
        scope.launch(Dispatchers.IO) {
            currentWeather = WeatherService.getCurrentWeather(city)
            forecast = WeatherService.getForecast(city)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Bem-vindo(a), $preferredName!", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text("Welcome, $preferredName!", fontSize = 16.sp, color = Color.Gray)

        Spacer(modifier = Modifier.height(16.dp))

        currentWeather?.let { weather ->
            Text("Clima atual em ${weather.name}", fontSize = 20.sp, fontWeight = FontWeight.Medium)
            Text("Current weather in ${weather.name}", fontSize = 14.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(8.dp))

            Text("${weather.weather[0].description.capitalize()} | ${weather.main.temp}°C")
            Text("Sensação térmica: ${weather.main.feelsLike}°C")
            Text("Umidade: ${weather.main.humidity}%")
            Text("Vento: ${weather.wind.speed} km/h")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("Previsão para os próximos dias", fontSize = 20.sp, fontWeight = FontWeight.Medium)
        Text("Forecast for the next days", fontSize = 14.sp, color = Color.Gray)

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(forecast.take(15)) { item ->
                ForecastItemView(item)
            }
        }
    }
}

@Composable
fun ForecastItemView(item: ForecastItem) {
    val formatter = DateTimeFormatter.ofPattern("dd/MM HH:mm").withZone(ZoneId.systemDefault())
    val date = formatter.format(Instant.ofEpochSecond(item.dt))

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text("$date - ${item.weather[0].description.capitalize()}", fontWeight = FontWeight.SemiBold)
        Text("Temp: ${item.main.temp}°C | Vento: ${item.wind.speed} km/h | Umidade: ${item.main.humidity}%")
    }
}

@Preview
@Composable
fun PreviewWeatherScreen() {
    WeatherScreen(city = "Halifax", preferredName = "Fábio")
}