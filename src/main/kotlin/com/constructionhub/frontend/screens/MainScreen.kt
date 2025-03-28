package com.constructionhub.frontend.dtos.weather

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponseDTO(
    val city: CityDTO,
    val list: List<ForecastDTO>
)

@Serializable
data class CityDTO(
    val name: String,
    val country: String
)

@Serializable
data class ForecastDTO(
    val dt: Long,
    val temp: TemperatureDTO,
    val weather: List<WeatherDetailDTO>
)

@Serializable
data class TemperatureDTO(
    val day: Double,
    val min: Double,
    val max: Double
)

@Serializable
data class WeatherDetailDTO(
    val main: String,
    val description: String,
    val icon: String
)
