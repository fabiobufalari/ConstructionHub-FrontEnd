import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.*
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.constructionhub.frontend.service.WeatherService
import kotlinx.coroutines.*

@Composable
@Preview
fun App() {
    var userName by remember { mutableStateOf("Fábio") }
    var forecast by remember { mutableStateOf<List<String>>(emptyList()) }

    LaunchedEffect(Unit) {
        val weather = WeatherService.getForecast("Halifax")
        forecast = weather.daily.take(5).map {
            val desc = it.weather.firstOrNull()?.description ?: "sem dados"
            "Dia: ${it.dt} | ${desc.capitalize()} | Min: ${it.temp.min}°C, Max: ${it.temp.max}°C"
        }
    }

    androidx.compose.material.Text("Olá, $userName!")
    forecast.forEach {
        androidx.compose.material.Text(it)
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
