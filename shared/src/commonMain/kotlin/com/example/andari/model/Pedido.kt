package com.example.andari.model

enum class MetodoPago(val titulo: String, val descripcion: String) {
    TARJETA("Tarjeta de crédito o débito", "Pagas ahora con tu tarjeta"),
    EFECTIVO("Efectivo contra entrega", "Pagas cuando recibas tus huaraches"),
    TRANSFERENCIA("Transferencia bancaria", "Te enviaremos los datos para transferir")
}

// Datos que captura la pantalla Pedido (los datos de la tarjeta NO se guardan)
data class DatosPedido(
    val nombre: String,
    val telefono: String,
    val calle: String,
    val colonia: String,
    val ciudad: String,
    val estado: String,
    val codigoPostal: String,
    val referencias: String,
    val metodoPago: MetodoPago
) {
    val direccionCompleta: String
        get() = "$calle, Col. $colonia, $ciudad, $estado, C.P. $codigoPostal"
}

data class Pedido(
    val numero: String,
    val datos: DatosPedido,
    val items: List<CartItem>,
    val total: Double
)
