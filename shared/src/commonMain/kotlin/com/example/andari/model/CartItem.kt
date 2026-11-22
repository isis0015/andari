package com.example.andari.model

import org.jetbrains.compose.resources.DrawableResource

data class CartItem(
    val id: String,
    val nombre: String,
    val talla: Int,
    val precio: Double,
    val cantidad: Int = 1,
    val imagen: DrawableResource? = null
) {
    val subtotal: Double get() = precio * cantidad
}