package com.constructionhub.frontend.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.constructionhub.frontend.model.Project
import com.constructionhub.frontend.service.ProjectService
import kotlinx.coroutines.launch

@Composable
fun ProjectScreen(onLogout: () -> Unit) {
    var projects by remember { mutableStateOf<List<Project>>(emptyList()) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(true) {
        scope.launch {
            try {
                projects = ProjectService.getProjects()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Lista de Projetos", fontSize = 20.sp)
        Spacer(Modifier.height(10.dp))

        projects.forEach {
            Text("📁 ${it.name}", fontSize = 16.sp)
            Spacer(Modifier.height(4.dp))
        }

        Spacer(Modifier.height(20.dp))

        Button(onClick = onLogout) {
            Text("Logout")
        }
    }
}
