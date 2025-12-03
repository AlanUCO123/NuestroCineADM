package com.example.mascotasapp.navegacion

import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Upcoming

@Composable
fun BottomNavBar(navController: NavHostController) {
    // 1. ¡Lista de items actualizada con los íconos correctos!
    val items = listOf(
        // "Inicio" -> Ícono de Casa
        BottomNavItem("Inicio", "inicio", Icons.Default.Home),
        // "Cartelera" -> Ícono de Película o Boletos
        BottomNavItem("Cartelera", "cartelera", Icons.Default.Movie),
        // "Alimentos" -> Ícono de Comida Rápida o Restaurante
        BottomNavItem("Alimentos", "alimentos", Icons.Default.Fastfood),
        // "Proximamente" -> Ícono de "Próximo" o Calendario
        BottomNavItem("Pronto", "proximamente", Icons.Default.Upcoming),
        // "Perfil" -> Ícono de Persona o Cuenta
        BottomNavItem("Perfil", "perfil", Icons.Default.Person)
    )

    NavigationBar(containerColor = Color(0xFFDEB887)) {
        val currentRoute = currentRoute(navController)
        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = { navController.navigate(item.route) },
                // 2. ¡Más simple! El Icon ahora usa el ImageVector directamente
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(item.title) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFF000000),
                    selectedTextColor = Color(0xFF000000),
                    unselectedIconColor = Color.Black,
                    unselectedTextColor = Color.Black
                )
            )
        }
    }
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}
