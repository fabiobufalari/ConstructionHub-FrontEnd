package com.constructionhub.frontend.model

import kotlinx.serialization.Serializable

@Serializable
data class WeatherDTO(
    val daily: List<DailyWeather>
)

@Serializable
data class DailyWeather(
    val dt: Long,
    val temp: Temperature,
    val weather: List<WeatherDescription>
)

@Serializable
data class Temperature(
    val day: Double,
    val min: Double,
    val max: Double
)

@Serializable
data class WeatherDescription(
    val main: String,
    val description: String,
    val icon: String
)
