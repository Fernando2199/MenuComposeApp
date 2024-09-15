package com.example.menucomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.menucomposeapp.ui.theme.MenuComposeAppTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.rounded.Search
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MenuComposeAppTheme {
                CustomScaffold()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomScaffold() {
    val navController = rememberNavController()

    Scaffold(
        topBar = { CustomTopBar(navController) },
        bottomBar = { CustomBottomBar(navController) },
        floatingActionButton = { CustomFAB() }
    ) { padding ->
        NavHost(navController = navController, startDestination = "home") {
            composable("home") { CustomContent(padding) }
            composable("profile") { ProfileScreen() }
            composable("settings") { SettingsScreen() }
            composable("favorites") { FavoritesScreen() }
            composable("delete") { DeleteScreen() }
            composable("tools") { ToolsScreen() }
            composable("new") { NewScreen() }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(navController: NavController) {
    TopAppBar(
        title = { Text(text = "Sample Title") },
        navigationIcon = {
            IconButton(onClick = { /* Acción del botón de menú */ }) {
                Icon(Icons.Filled.Menu, contentDescription = "Menú")
            }
        },
        actions = {
            IconButton(onClick = { /* Navegar a la pantalla de búsqueda */ }) {
                Icon(Icons.Rounded.Search, contentDescription = "Buscar")
            }
            IconButton(onClick = { navController.navigate("profile") }) {
                Icon(Icons.Outlined.AccountCircle, contentDescription = "Perfil")
            }
        }
    )
}

@Composable
fun CustomBottomBar(navController: NavController) {
    BottomAppBar {
        IconButton(onClick = { navController.navigate("tools") }) {
            Icon(Icons.Filled.Build, contentDescription = "Herramientas")
        }
        IconButton(onClick = { navController.navigate("settings") }) {
            Icon(Icons.Filled.Menu, contentDescription = "Configuración")
        }
        IconButton(onClick = { navController.navigate("favorites") }) {
            Icon(Icons.Filled.Favorite, contentDescription = "Favoritos")
        }
        IconButton(onClick = { navController.navigate("delete") }) {
            Icon(Icons.Filled.Delete, contentDescription = "Eliminar")
        }
    }
}

@Composable
fun CustomFAB() {
    var count by remember { mutableStateOf(0) }

    FloatingActionButton(
        onClick = { count++ }
    ) {
        Text(
            fontSize = 24.sp,
            text = "+$count" // Muestra el conteo en el botón
        )
    }
}

@Composable
fun CustomContent(padding: PaddingValues) {
    Column(modifier = Modifier.padding(padding)) {
        Text(text = "Contenido de la aplicación")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MenuComposeAppTheme {
        CustomScaffold()
    }
}
