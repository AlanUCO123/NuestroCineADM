package com.example.mascotasapp.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
fun PantallaInicio() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cine ADM") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFDEB887), // azul claro
                    titleContentColor = Color.Black
                )
            )
        },
        content = { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFFFFFFF))
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    // 🐾 Logo circular
                    Image(
                        painter = painterResource(id = R.drawable.cadm),
                        contentDescription = "Logo de Cine ADM",
                        modifier = Modifier
                            .size(160.dp)
                            .clip(CircleShape)
                            .border(3.dp, Color(0xFF4F3303), CircleShape)
                            .padding(4.dp),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Bienvenido a Cine ADM",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF6D4C41),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Esperamos que tengas una experiencia inolvidable",
                        fontSize = 18.sp,
                        color = Color.DarkGray,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 24.dp)
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true) // 'showBackground = true' añade un fondo para mejor visibilidad
@Composable
fun Preview() {
        PantallaInicio()
}
