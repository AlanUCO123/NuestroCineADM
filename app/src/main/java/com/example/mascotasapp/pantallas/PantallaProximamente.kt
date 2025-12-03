package com.example.mascotasapp.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mascotasapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaProximamente() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Proximamente en cines") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFDEB887),
                    titleContentColor = Color.Black
                )
            )
        },
        content = { padding ->
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(color = 0xFFFFFFFF))
                    .padding(paddingValues = padding)
                    // 2. Cambia a .horizontalScroll
                    .horizontalScroll(state = rememberScrollState())
                    .padding(all = 16.dp),
                // 3. Cambia a verticalAlignment
                verticalAlignment = Alignment.CenterVertically
            ) {
                ProximamenteCard(
                    imagen = R.drawable.rf5,
                    nombre = "Rapidos y Furiosos 5in control",
                    descripcion = "En Río de Janeiro, Dominic Toretto y Brian O'Conner reúnen a su pandilla de corredores callejeros para llevar a cabo un atraco, mientras están en la mira de un poderoso narcotraficante brasileño y un peligroso agente federal."
                )

                Spacer(modifier = Modifier.height(16.dp))

                ProximamenteCard(
                    imagen = R.drawable.interestelar,
                    nombre = "Interestelar",
                    descripcion = "Gracias a un descubrimiento, un grupo de científicos y exploradores, encabezados por Cooper, se embarcan en un viaje espacial para encontrar un lugar con las condiciones necesarias para reemplazar a la Tierra y comenzar una nueva vida allí."
                )

                Spacer(modifier = Modifier.height(16.dp))

                ProximamenteCard(
                    imagen = R.drawable.creed,
                    nombre = "Creed 3",
                    descripcion = "Adonis Creed, aún dominando el mundo del boxeo, prospera en su carrera y en su vida familiar, pero un amigo de la infancia y antiguo prodigio del boxeo reaparece tras salir de la cárcel y está ansioso por demostrar que merece otra oportunidad."
                )

                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    )
}

@Composable
fun ProximamenteCard(imagen: Int, nombre: String, descripcion: String) {
    Card(
        modifier = Modifier
            .width(280.dp)
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = imagen),
                contentDescription = nombre,
                modifier = Modifier
                    .size(180.dp)
                    .padding(8.dp),
                contentScale = ContentScale.Crop
            )

            Text(
                text = nombre,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF000000)
            )

            Text(
                text = descripcion,
                fontSize = 16.sp,
                color = Color.DarkGray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}
@Preview(showBackground = true) // 'showBackground = true' añade un fondo para mejor visibilidad
@Composable
fun ProximamentePreview() {
    PantallaProximamente()
}