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
fun PantallaCartelera() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Peliculas en Cartelera") },
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
            ){
                CarteleraCard(
                    imagen = R.drawable.mg,
                    nombre = "Metegol",
                    descripcion = "Amadeo es un chico tímido y virtuoso que deberá " +
                            "enfrentarse a un habilidoso rival sobre el campo de fútbol," +
                            " conocido con el apodo de El Crack."
                )

                Spacer(modifier = Modifier.width(16.dp))

                CarteleraCard(
                    imagen = R.drawable.intensamente,
                    nombre = "Intensamente 2",
                    descripcion = "Ahora que es adolescente, Riley experimenta nuevos sentimientos " +
                            "como Ansiedad y Envidia, que se imponen a los viejos mientras" +
                            " ella duda sobre si abandonar a sus antiguas amigas por otras de la escuela secundaria."
                )

                Spacer(modifier = Modifier.width(16.dp))

                CarteleraCard(
                    imagen = R.drawable.ps,
                    nombre = "El Reino del Planeta de los Simios",
                    descripcion = "Muchos años después de la época de César," +
                            " los simios se han dividido en clanes y los hombres se han vuelto salvajes."
                )

                Spacer(modifier = Modifier.width(40.dp))
            }
        }
    )
}

@Composable
fun CarteleraCard(imagen: Int, nombre: String, descripcion: String) {
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
fun GreetingPreview() {
    PantallaCartelera()
}