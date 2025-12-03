package com.example.mascotasapp.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun PantallaPerfil() {
    var nombre by remember { mutableStateOf("") }

    var correo by remember { mutableStateOf("") }

    var descripcion by remember { mutableStateOf("") }



    val snackbarHostState = remember { SnackbarHostState() }

    val scope = rememberCoroutineScope()





    Scaffold(
            snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
            topBar = {
                TopAppBar(
                    title = { Text("Perfil de Usuario") },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFFDEB887),
                        titleContentColor = Color.Black
                    )
                )
            },
            content = { padding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFFFFFFFF)) //
                        .padding(padding)
                        .padding(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Tu Perfil",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(bottom = 12.dp),
                        color = Color.DarkGray
                    )

                    // Imagen de perfil


                    Spacer(modifier = Modifier.height(16.dp))

                    TextField(
                        value = nombre,
                        onValueChange = { nombre = it },
                        label = { Text("Nombre de Usuario") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White
                        )
                    )

                    TextField(
                        value = correo,
                        onValueChange = { correo = it },
                        label = { Text("Correo electrónico") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White
                        )
                    )

                    TextField(
                        value = descripcion,
                        onValueChange = { descripcion = it },
                        label = { Text("Número de teléfono") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            scope.launch {
                                snackbarHostState.showSnackbar("Gracias por registrarte en Cine ADM")
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFDEB887),
                            contentColor = Color.Black
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Guardar perfil", fontWeight = FontWeight.Bold)
                    }
                }
            }
        )
    }
    @Preview(showBackground = true) // 'showBackground = true' añade un fondo para mejor visibilidad
    @Composable
    fun PerfilPreview() {
        PantallaPerfil()
    }
