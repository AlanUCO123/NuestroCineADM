package com.example.mascotasapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mascotasapp.data.CarritoDao
import com.example.mascotasapp.data.CarritoEntity
import com.example.mascotasapp.data.ProductoDao
import com.example.mascotasapp.data.ProductoEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProductoViewModel(
    private val productoDao: ProductoDao,
    private val carritoDao: CarritoDao
) : ViewModel() {

    // OBTENCIÓN DE PRODUCTOS
    val productos: StateFlow<List<ProductoEntity>> =
        productoDao.getAllProductos()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

    // OBTENCIÓN DEL CARRITO
    val carrito: StateFlow<List<CarritoEntity>> =
        carritoDao.getAllItemsCarrito()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )
    // 3. LÓGICA DE INSERCIÓN/PRECARGA
    fun precargarProductos(lista: List<ProductoEntity>) {
        viewModelScope.launch {
            val existentes = productoDao.obtenerProductosUnaVez()

            val nuevos = lista.filter { nuevo ->
                existentes.none { it.nombre == nuevo.nombre }
            }

            if (nuevos.isNotEmpty()) {
                productoDao.insertarProductos(nuevos)
            }
        }
    }

    fun agregarAlCarrito(producto: ProductoEntity) {
        viewModelScope.launch {
            val itemCarrito = CarritoEntity(
                nombre = producto.nombre,
                precio = producto.precio,
                productoId = producto.id
            )
            carritoDao.insert(itemCarrito)
        }
    }

    fun eliminarDelCarrito(id: Int) {
        viewModelScope.launch {
            carritoDao.deleteById(id)
        }
    }

    fun pagar() {
        viewModelScope.launch {
            carritoDao.clearAll()
        }
    }
}
