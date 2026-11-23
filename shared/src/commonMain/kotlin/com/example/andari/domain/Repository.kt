package com.example.andari.domain

import com.example.andari.model.CartItem
import com.example.andari.model.DatosPedido
import com.example.andari.model.Pedido
import com.example.andari.model.Favorito
import com.example.andari.model.Producto

interface Repository {

    fun buscarProducto(id: String): Producto

    fun obtenerCarrito(): List<CartItem>
    fun agregarAlCarrito(producto: Producto, talla: Int, cantidad: Int)
    fun aumentarCantidad(item: CartItem)
    fun disminuirCantidad(item: CartItem)
    fun quitarDelCarrito(item: CartItem)
    fun totalCarrito(): Double
    fun confirmarPedido(datos: DatosPedido): Pedido


    fun obtenerFavoritos(): List<Favorito>
    fun agregarFavorito(producto: Producto)
    fun quitarFavorito(favorito: Favorito)
    fun alternarFavorito(producto: Producto)
}