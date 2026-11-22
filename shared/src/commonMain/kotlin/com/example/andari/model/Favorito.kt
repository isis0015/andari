package com.example.andari.model

import org.jetbrains.compose.resources.DrawableResource

data class Favorito(
    val id: String,
    val nombre: String,
    val talla: String,
    val precio: Int,
    val imagen: DrawableResource? = null
)