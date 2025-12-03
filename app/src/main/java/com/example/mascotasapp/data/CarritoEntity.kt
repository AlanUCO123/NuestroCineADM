package com.example.mascotasapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "carrito")
data class CarritoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val productoId: Int,   // ID del producto original
    val nombre: String,    // Nombre del producto
    val precio: Double     // Precio individual
)