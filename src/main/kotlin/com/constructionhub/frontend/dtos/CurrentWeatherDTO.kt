package com.constructionhub.frontend.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeatherDTO(
    val name: String,
    val weather: List<WeatherDescription>,
    val main: TemperatureInfo,
    val wind: WindInfo
)

@Serializable
data class WeatherForecastDTO(
    val city: CityInfo,
    val list: List<ForecastItem>
)

@Serializable
data class WeatherDescription(
    val main: String,
    val description: String,
    val icon: String
)

@Serializable
data class TemperatureInfo(
    val temp: Double,
    @SerialName("feels_like") val feelsLike: Double,
    @SerialName("temp_min") val tempMin: Double,
    @SerialName("temp_max") val tempMax: Double,
    val humidity: Int
)

@Serializable
data class WindInfo(
    val speed: Double
)

@Serializable
data class ForecastItem(
    val dt: Long,
    val main: TemperatureInfo,
    val weather: List<WeatherDescription>,
    val wind: WindInfo,
    @SerialName("dt_txt") val dtTxt: String
)

@Serializable
data class CityInfo(
    val name: String,
    val country: String
)
