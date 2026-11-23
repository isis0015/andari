package com.example.andari.data

import com.example.andari.domain.Repository
import com.example.andari.model.CartItem
import com.example.andari.model.DatosPedido
import com.example.andari.model.Pedido
import com.example.andari.model.Favorito
import com.example.andari.model.Producto

class RepoImpl(private val manager: Manager) : Repository {


    override fun buscarProducto(id: String): Producto = manager.buscarProducto(id)


    override fun obtenerCarrito(): List<CartItem> = manager.obtenerCarrito()

    override fun agregarAlCarrito(producto: Producto, talla: Int, cantidad: Int) {
        manager.agregarAlCarrito(producto, talla, cantidad)
    }

    override fun aumentarCantidad(item: CartItem) {
        manager.aumentarCantidad(item)
    }

    override fun disminuirCantidad(item: CartItem) {
        manager.disminuirCantidad(item)
    }

    override fun quitarDelCarrito(item: CartItem) {
        manager.quitarDelCarrito(item)
    }

    override fun totalCarrito(): Double = manager.totalCarrito()

    override fun confirmarPedido(datos: DatosPedido): Pedido = manager.confirmarPedido(datos)

    // Favoritos
    override fun obtenerFavoritos(): List<Favorito> = manager.obtenerFavoritos()

    override fun agregarFavorito(producto: Producto) {
        manager.agregarFavorito(producto)
    }

    override fun quitarFavorito(favorito: Favorito) {
        manager.quitarFavorito(favorito)
    }

    override fun alternarFavorito(producto: Producto) {
        manager.alternarFavorito(producto)
    }
}