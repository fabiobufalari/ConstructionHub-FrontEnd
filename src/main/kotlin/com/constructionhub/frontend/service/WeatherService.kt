package com.constructionhub.frontend.service

import com.constructionhub.frontend.dtos.CurrentWeatherDTO
import com.constructionhub.frontend.dtos.WeatherForecastDTO
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.Json

object WeatherService {
    private const val API_KEY = "18a33de44ddae8fec74dca1504eaf4cc"

    private val client = HttpClient {
        expectSuccess = true
    }

    suspend fun getCurrentWeather(city: String): CurrentWeatherDTO {
        val response: HttpResponse = client.get("https://api.openweathermap.org/data/2.5/weather") {
            parameter("q", city)
            parameter("appid", API_KEY)
            parameter("units", "metric")
        }
        return response.body()
    }

    suspend fun getForecast(city: String): WeatherForecastDTO {
        val response: HttpResponse = client.get("https://api.openweathermap.org/data/2.5/forecast") {
            parameter("q", city)
            parameter("appid", API_KEY)
            parameter("units", "metric")
        }
        return response.body()
    }
}
