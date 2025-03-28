// Initial project: Kotlin frontend for the construction management system (Jetpack Compose Desktop)
// Features included:
// - Simple login screen
// - Project list screen
// - Base layout with sidebar and header
// - Structure ready for multi-tenant companies
// - Internationalization (i18n) support

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "ConstructionHub-FrontEnd") {
        App()
    }
}

@Composable
fun App() {
    var loggedIn by remember { mutableStateOf(false) }

    if (!loggedIn) {
        LoginScreen { loggedIn = true }
    } else {
        BaseLayout(content = { ProjectListScreen() })
    }
}

@Composable
fun LoginScreen(onLoginSuccess: () -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Login", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))
        BasicTextField(value = username, onValueChange = { username = it }, modifier = Modifier.padding(8.dp))
        BasicTextField(value = password, onValueChange = { password = it }, modifier = Modifier.padding(8.dp))
        Spacer(Modifier.height(16.dp))
        Button(onClick = onLoginSuccess) { Text("Sign in") }
    }
}

@Composable
fun BaseLayout(content: @Composable () -> Unit) {
    Row(Modifier.fillMaxSize()) {
        Sidebar()
        Column(Modifier.fillMaxSize()) {
            Header()
            content()
        }
    }
}

@Composable
fun Sidebar() {
    Column(
        modifier = Modifier.width(200.dp).fillMaxHeight().padding(8.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text("Menu", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(16.dp))
        Text("Projects")
        Text("Materials")
        Text("Users")
    }
}

@Composable
fun Header() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text("ConstructionHub-FrontEnd")
        LanguageSelector()
    }
}

@Composable
fun LanguageSelector() {
    var selected by remember { mutableStateOf("en") }
    Row {
        Text("Language:")
        Spacer(Modifier.width(8.dp))
        DropdownMenuButton(options = listOf("en", "pt", "fr"), selected = selected, onSelect = { selected = it })
    }
}

@Composable
fun DropdownMenuButton(options: List<String>, selected: String, onSelect: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        Button(onClick = { expanded = true }) { Text(selected) }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            options.forEach { option ->
                DropdownMenuItem(onClick = {
                    onSelect(option)
                    expanded = false
                }, text = { Text(option) })
            }
        }
    }
}

@Composable
fun ProjectListScreen() {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Project List", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(8.dp))
        // Mock data
        val projects = listOf("Downtown House", "Residential Building", "Industrial Warehouse")
        projects.forEach { project ->
            Text("- $project")
        }
    }
}
