package com.example.mascotasapp.navegacion

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mascotasapp.pantallas.PantallaProducto
import com.example.mascotasapp.pantallas.PantallaInicio
import com.example.mascotasapp.pantallas.PantallaCartelera
import com.example.mascotasapp.pantallas.PantallaPerfil
import com.example.mascotasapp.pantallas.PantallaProximamente

@Composable
fun MyApp() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "inicio",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("inicio") { PantallaInicio() }
            composable("cartelera") { PantallaCartelera() }
            composable("alimentos") { PantallaProducto() }
            composable("proximamente") { PantallaProximamente() }
            composable("perfil") { PantallaPerfil() }
        }
    }
}
