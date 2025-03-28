package com.constructionhub.frontend.service

import com.constructionhub.frontend.dtos.CurrentWeatherDTO
import com.constructionhub.frontend.dtos.WeatherForecastDTO
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.*

object WeatherService {
    private const val API_KEY = "18a33de44ddae8fec74dca1504eaf4cc"
    private const val BASE_URL = "https://api.openweathermap.org/data/2.5"
    private val client = AuthService.getAuthenticatedClient()

    suspend fun getWeatherForecast(city: String): CurrentWeatherDTO? = withContext(Dispatchers.IO) {
        try {
            val response = client.get("$BASE_URL/weather") {
                parameter("q", city)
                parameter("appid", API_KEY)
                parameter("units", "metric")
            }
            response.body()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun get15DayForecast(city: String): WeatherForecastDTO? = withContext(Dispatchers.IO) {
        try {
            val response = client.get("$BASE_URL/forecast") {
                parameter("q", city)
                parameter("cnt", 40)
                parameter("appid", API_KEY)
                parameter("units", "metric")
            }
            response.body()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}