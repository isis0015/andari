package com.example.andari.Previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.andari.model.Catalogo
import com.example.andari.ui.screens.ArticuloScreen
import com.example.andari.ui.screens.CompraRealizadaScreen
import com.example.andari.ui.screens.PedidoScreen
import com.example.andari.model.CartItem
import com.example.andari.ui.screens.CrearCuentaScreen
import com.example.andari.ui.screens.InicioScreen
import com.example.andari.ui.screens.LoginScreen
import com.example.andari.ui.screens.MenuCaballeroScreen
import com.example.andari.ui.screens.MenuDamaScreen
import com.example.andari.ui.screens.MenuNinoScreen
import com.example.andari.ui.screens.RecuperarScreen

// Si las imágenes no aparecen en el preview, corre la app en el emulador (el preview a veces no carga recursos).

@Preview(showSystemUi = true)
@Composable
fun InicioPreview() { InicioScreen({}, {}) }

@Preview(showSystemUi = true)
@Composable
fun LoginPreview() { LoginScreen({}, {}, {}) }

@Preview(showSystemUi = true)
@Composable
fun RecuperarPreview() { RecuperarScreen({}, {}) }

@Preview(showSystemUi = true)
@Composable
fun CrearCuentaPreview() { CrearCuentaScreen({}, {}) }

// --- CORREGIDOS LOS MENÚS ---
@Preview(showSystemUi = true)
@Composable
fun MenuDamaPreview() {
    MenuDamaScreen(
        onArticulo = {},
        onCaballero = {},
        onNino = {},
        favoritosIds = emptyList(),
        onToggleFavorito = {}
    )
}

@Preview(showSystemUi = true)
@Composable
fun MenuCaballeroPreview() {
    MenuCaballeroScreen(
        onArticulo = {},
        onDama = {},
        onNino = {},
        favoritosIds = emptyList(),
        onToggleFavorito = {}
    )
}

@Preview(showSystemUi = true)
@Composable
fun MenuNinoPreview() {
    MenuNinoScreen(
        onArticulo = {},
        onDama = {},
        onCaballero = {},
        favoritosIds = emptyList(),
        onToggleFavorito = {}
    )
}

// --- CORREGIDOS LOS ARTÍCULOS (Agregando las 3 lambdas) ---

@Preview(showSystemUi = true)
@Composable
fun ArticuloForroPreview() {
    ArticuloScreen(
        producto = Catalogo.dama[0],
        onVolverAlMenu = {},
        onAgregar = { _, _, _ -> },
        onCarrito = {}
    )
}

@Preview(showSystemUi = true)
@Composable
fun ArticuloBordadoPreview() {
    ArticuloScreen(
        producto = Catalogo.dama[1],
        onVolverAlMenu = {},
        onAgregar = { _, _, _ -> },
        onCarrito = {}
    )
}

@Preview(showSystemUi = true)
@Composable
fun ArticuloTaconPreview() {
    ArticuloScreen(
        producto = Catalogo.dama[2],
        onVolverAlMenu = {},
        onAgregar = { _, _, _ -> },
        onCarrito = {}
    )
}

@Preview(showSystemUi = true)
@Composable
fun ArticuloEsqueletoPreview() {
    ArticuloScreen(
        producto = Catalogo.dama[3],
        onVolverAlMenu = {},
        onAgregar = { _, _, _ -> },
        onCarrito = {}
    )
}

@Preview(showSystemUi = true)
@Composable
fun ArticuloCabTrenzadoPreview() {
    ArticuloScreen(
        producto = Catalogo.caballero[0],
        onVolverAlMenu = {},
        onAgregar = { _, _, _ -> },
        onCarrito = {}
    )
}

@Preview(showSystemUi = true)
@Composable
fun ArticuloCabVinipielPreview() {
    ArticuloScreen(
        producto = Catalogo.caballero[1],
        onVolverAlMenu = {},
        onAgregar = { _, _, _ -> },
        onCarrito = {}
    )
}

@Preview(showSystemUi = true)
@Composable
fun ArticuloCabCueroPreview() {
    ArticuloScreen(
        producto = Catalogo.caballero[2],
        onVolverAlMenu = {},
        onAgregar = { _, _, _ -> },
        onCarrito = {}
    )
}

@Preview(showSystemUi = true)
@Composable
fun ArticuloCabBordadoPreview() {
    ArticuloScreen(
        producto = Catalogo.caballero[3],
        onVolverAlMenu = {},
        onAgregar = { _, _, _ -> },
        onCarrito = {}
    )
}

@Preview(showSystemUi = true)
@Composable
fun ArticuloNinoSuelaNegraPreview() {
    ArticuloScreen(
        producto = Catalogo.nino[0],
        onVolverAlMenu = {},
        onAgregar = { _, _, _ -> },
        onCarrito = {}
    )
}

@Preview(showSystemUi = true)
@Composable
fun ArticuloNinoTrenzaPreview() {
    ArticuloScreen(
        producto = Catalogo.nino[1],
        onVolverAlMenu = {},
        onAgregar = { _, _, _ -> },
        onCarrito = {}
    )
}

@Preview(showSystemUi = true)
@Composable
fun ArticuloNinoColoresPreview() {
    ArticuloScreen(
        producto = Catalogo.nino[2],
        onVolverAlMenu = {},
        onAgregar = { _, _, _ -> },
        onCarrito = {}
    )
}

@Preview(showSystemUi = true)
@Composable
fun ArticuloNinoTonosCafePreview() {
    ArticuloScreen(
        producto = Catalogo.nino[3],
        onVolverAlMenu = {},
        onAgregar = { _, _, _ -> },
        onCarrito = {}
    )
}

@Preview(showSystemUi = true)
@Composable
fun PedidoPreview() {
    PedidoScreen(
        items = listOf(CartItem("forro", "Huarache forro colores", 25, 450.0, 2)),
        total = 900.0
    )
}

@Preview(showSystemUi = true)
@Composable
fun CompraRealizadaPreview() { CompraRealizadaScreen(pedido = null) }
