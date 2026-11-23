package com.example.andari.data


import com.example.andari.model.CartItem
import com.example.andari.model.DatosPedido
import com.example.andari.model.Pedido
import com.example.andari.model.Catalogo
import com.example.andari.model.Favorito
import com.example.andari.model.Producto


object Manager {
    private val carrito = mutableListOf<CartItem>()
    private val favoritos = mutableListOf<Favorito>()
    private val pedidos = mutableListOf<Pedido>()


    fun buscarProducto(id: String): Producto = Catalogo.buscar(id)


    fun obtenerCarrito(): List<CartItem> = carrito


    fun agregarAlCarrito(producto: Producto, talla: Int, cantidad: Int) {
        val indice = carrito.indexOfFirst { it.id == producto.id && it.talla == talla }
        if (indice != -1) {
            val actual = carrito[indice]
            carrito[indice] = actual.copy(cantidad = actual.cantidad + cantidad)
        } else {
            carrito.add(
                CartItem(
                    id = producto.id,
                    nombre = producto.nombre,
                    talla = talla,
                    precio = producto.precio.toDouble(),
                    cantidad = cantidad,
                    imagen = producto.imagenMenu
                )
            )
        }
    }

    fun aumentarCantidad(item: CartItem) {
        val indice = indiceEnCarrito(item)
        if (indice != -1) {
            carrito[indice] = carrito[indice].copy(cantidad = carrito[indice].cantidad + 1)
        }
    }

    fun disminuirCantidad(item: CartItem) {
        val indice = indiceEnCarrito(item)
        if (indice != -1 && carrito[indice].cantidad > 1) {
            carrito[indice] = carrito[indice].copy(cantidad = carrito[indice].cantidad - 1)
        }
    }

    fun quitarDelCarrito(item: CartItem) {
        carrito.removeAll { it.id == item.id && it.talla == item.talla }
    }

    fun totalCarrito(): Double = carrito.sumOf { it.subtotal }

    // Genera el pedido con lo que hay en el carrito y vacía el carrito
    fun confirmarPedido(datos: DatosPedido): Pedido {
        val pedido = Pedido(
            numero = "AND-" + (1001 + pedidos.size),
            datos = datos,
            items = carrito.toList(),
            total = totalCarrito()
        )
        pedidos.add(pedido)
        carrito.clear()
        return pedido
    }

    private fun indiceEnCarrito(item: CartItem): Int =
        carrito.indexOfFirst { it.id == item.id && it.talla == item.talla }


    fun obtenerFavoritos(): List<Favorito> = favoritos

    fun esFavorito(id: String): Boolean = favoritos.any { it.id == id }

    fun agregarFavorito(producto: Producto) {
        if (!esFavorito(producto.id)) {
            favoritos.add(
                Favorito(
                    id = producto.id,
                    nombre = producto.nombre,
                    talla = "Talla ${producto.tallaInicial}",
                    precio = producto.precio,
                    imagen = producto.imagenMenu
                )
            )
        }
    }

    fun quitarFavorito(favorito: Favorito) {
        favoritos.removeAll { it.id == favorito.id }
    }


    fun alternarFavorito(producto: Producto) {
        if (esFavorito(producto.id)) {
            favoritos.removeAll { it.id == producto.id }
        } else {
            agregarFavorito(producto)
        }
    }
}