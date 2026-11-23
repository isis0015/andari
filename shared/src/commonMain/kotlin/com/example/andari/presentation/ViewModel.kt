package com.example.andari.presentation

import com.example.andari.domain.Repository
import com.example.andari.model.CartItem
import com.example.andari.model.DatosPedido
import com.example.andari.model.Pedido
import com.example.andari.model.Favorito
import com.example.andari.model.Producto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import moe.tlaster.precompose.viewmodel.ViewModel as PreComposeViewModel

class ViewModel(private val repo: Repository) : PreComposeViewModel() {

    data class UIState(
        val carrito: List<CartItem> = emptyList(),
        val totalCarrito: Double = 0.0,
        val favoritos: List<Favorito> = emptyList(),
        val ultimoPedido: Pedido? = null
    )

    private val _uiState = MutableStateFlow(UIState())
    val uiState: StateFlow<UIState> = _uiState.asStateFlow()

    init {
        actualizarEstado()
    }


    private fun actualizarEstado() {
        _uiState.update { estado ->
            estado.copy(
                carrito = repo.obtenerCarrito().toList(),
                totalCarrito = repo.totalCarrito(),
                favoritos = repo.obtenerFavoritos().toList()
            )
        }
    }


    fun buscarProducto(id: String): Producto = repo.buscarProducto(id)


    fun agregarAlCarrito(producto: Producto, talla: Int, cantidad: Int) {
        repo.agregarAlCarrito(producto, talla, cantidad)
        actualizarEstado()
    }

    fun aumentarCantidad(item: CartItem) {
        repo.aumentarCantidad(item)
        actualizarEstado()
    }

    fun disminuirCantidad(item: CartItem) {
        repo.disminuirCantidad(item)
        actualizarEstado()
    }

    fun quitarDelCarrito(item: CartItem) {
        repo.quitarDelCarrito(item)
        actualizarEstado()
    }


    fun confirmarPedido(datos: DatosPedido) {
        val pedido = repo.confirmarPedido(datos)
        actualizarEstado()
        _uiState.update { it.copy(ultimoPedido = pedido) }
    }


    fun agregarFavorito(producto: Producto) {
        repo.agregarFavorito(producto)
        actualizarEstado()
    }

    fun quitarFavorito(favorito: Favorito) {
        repo.quitarFavorito(favorito)
        actualizarEstado()
    }

    fun alternarFavorito(producto: Producto) {
        repo.alternarFavorito(producto)
        actualizarEstado()
    }
}