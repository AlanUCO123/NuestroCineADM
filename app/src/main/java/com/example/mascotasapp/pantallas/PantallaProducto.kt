package com.example.mascotasapp.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mascotasapp.R
import com.example.mascotasapp.data.DatabaseProvider
import com.example.mascotasapp.data.ProductoEntity
import com.example.mascotasapp.viewmodel.ProductoViewModelFactory
import com.example.mascotasapp.viewmodel.ProductoViewModel

@Composable
fun PantallaProducto() {

    val context = LocalContext.current
    val db = DatabaseProvider.getDatabase(context)
    val factory = ProductoViewModelFactory(db.productoDao(), db.CarritoDao())
    val viewModel: ProductoViewModel = viewModel(factory = factory)

    val productos by viewModel.productos.collectAsState()
    val carrito by viewModel.carrito.collectAsState()

    // Inserción inicial
    LaunchedEffect(Unit) {
        val productosIniciales = listOf(


            ProductoEntity(
                nombre = "Nachos con Queso",
                precio = 283.00,
                stock = 50,
                imagen = R.drawable.cnachos // Referencia a cnachos.png
            ),
            ProductoEntity(
                nombre = "Combo Cuates",
                precio = 240.00,
                stock = 20,
                imagen = R.drawable.combo_cuates // O el que corresponda a pareja
            ),
            ProductoEntity(
                nombre = "Combo Icee",
                precio = 356.00,
                stock = 25,
                imagen = R.drawable.combo_icee // Referencia a combo_icee.png
            ),

            // Para los que no tengan imagen específica, puedes usar una por defecto
            ProductoEntity(
                nombre = "Combo Ciel",
                precio = 185.00,
                stock = 90,
                imagen = R.drawable.combo_ciel // Usando una genérica como ejemplo
            ),

            ProductoEntity(
                nombre = "Nachos con Extra Queso, Papas y Chiles Jalapeños",
                precio = 125.00,
                stock = 99,
                imagen = R.drawable.cnachos

            )
        )
        viewModel.precargarProductos(productosIniciales)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            "Alimentos",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(productos) { producto ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = producto.imagen), // Aquí usas el ID guardado
                            contentDescription = "Imagen de ${producto.nombre}",
                            modifier = Modifier
                                .size(70.dp)
                                .clip(CircleShape)
                                .border(2.dp, Color(0xFF5B3902), CircleShape)
                                .padding(end = 4.dp), // Espacio entre imagen y texto
                                    contentScale = ContentScale.Crop
                        )

                        Column (
                            modifier = Modifier.weight(1f)
                        ){  Text(
                                producto.nombre,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )

                            Text(
                                "Precio: $%.2f".format(producto.precio),
                                color = MaterialTheme.colorScheme.primary,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }

                        Button(
                            onClick = { viewModel.agregarAlCarrito(producto) },
                            enabled = producto.stock > 0
                        ) {
                            Text(
                                "Agregar",
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
            }
        }

        Divider(modifier = Modifier.padding(vertical = 10.dp))

        Text(
            "Carrito de compras",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        if (carrito.isEmpty()) {

            Text(
                "Tu carrito está vacío",
                modifier = Modifier.padding(top = 8.dp)
            )

        } else {

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 10.dp)
            ) {

                items(carrito) { item ->

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp),
                        shape = RoundedCornerShape(16.dp)
                    ) {

                        Row(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Column (
                                modifier = Modifier.weight(1f)
                            ){   Text(
                                    item.nombre,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Medium,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )

                                Text(
                                    "Precio: $%.2f".format(item.precio),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }

                            Button(
                                onClick = { viewModel.eliminarDelCarrito(item.id) },
                                colors = ButtonDefaults.buttonColors(
                                    MaterialTheme.colorScheme.error
                                )
                            ) {
                                Text(
                                    "Eliminar",
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }
                }
            }
        }

        val total = carrito.sumOf { it.precio }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            "Total: $%.2f".format(total),
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Button(
            onClick = { viewModel.pagar() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            enabled = carrito.isNotEmpty()
        ) {
            Text(
                "Pagar",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

