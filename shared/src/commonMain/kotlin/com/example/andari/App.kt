package com.example.andari
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.andari.data.Manager
import com.example.andari.data.RepoImpl
import com.example.andari.presentation.ViewModel
import com.example.andari.ui.screens.ArticuloScreen
import com.example.andari.ui.screens.CarritoScreen
import com.example.andari.ui.screens.CrearCuentaScreen
import com.example.andari.ui.screens.FavoritosScreen
import com.example.andari.ui.screens.InicioScreen
import com.example.andari.ui.screens.LoginScreen
import com.example.andari.ui.screens.MenuCaballeroScreen
import com.example.andari.ui.screens.MenuDamaScreen
import com.example.andari.ui.screens.MenuNinoScreen
import com.example.andari.ui.screens.PerfilScreen
import com.example.andari.ui.screens.RecuperarScreen
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.viewmodel.viewModel

@Composable
fun App() {
    PreComposeApp {
        val navigator = rememberNavigator()


        val viewModel = viewModel(modelClass = ViewModel::class) {
            ViewModel(RepoImpl(Manager))
        }
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navigator = navigator,
            initialRoute = "/inicio"
        ) {
            scene("/inicio") {
                InicioScreen(
                    onComenzar = { navigator.navigate("/login") },
                    onRegistrate = { navigator.navigate("/crear_cuenta") }
                )
            }
            scene("/login") {
                LoginScreen(
                    onEntrar = { navigator.navigate("/menu_dama") },
                    onOlvide = { navigator.navigate("/recuperar") },
                    onInicio = { navigator.navigate("/inicio") }
                )
            }
            scene("/recuperar") {
                RecuperarScreen(
                    onCambiar = { navigator.goBack() },
                    onInicio = { navigator.navigate("/inicio") }
                )
            }
            scene("/crear_cuenta") {
                CrearCuentaScreen(
                    onAtras = { navigator.goBack() },
                    onGuardar = { navigator.navigate("/menu_dama") }
                )
            }
            scene("/menu_dama") {
                MenuDamaScreen(
                    onArticulo = { id -> navigator.navigate("/articulo/$id") },
                    onCaballero = { navigator.navigate("/menu_caballero") },
                    onNino = { navigator.navigate("/menu_nino") },
                    onPerfil = { navigator.navigate("/perfil") },
                    onCarrito = { navigator.navigate("/carrito") },
                    onFavoritos = { navigator.navigate("/favoritos") },
                    favoritosIds = uiState.favoritos.map { it.id },
                    onToggleFavorito = { producto -> viewModel.alternarFavorito(producto) }
                )
            }
            scene("/menu_caballero") {
                MenuCaballeroScreen(
                    onArticulo = { id -> navigator.navigate("/articulo/$id") },
                    onDama = { navigator.navigate("/menu_dama") },
                    onNino = { navigator.navigate("/menu_nino") },
                    onPerfil = { navigator.navigate("/perfil") },
                    onCarrito = { navigator.navigate("/carrito") },
                    onFavoritos = { navigator.navigate("/favoritos") },
                    favoritosIds = uiState.favoritos.map { it.id },
                    onToggleFavorito = { producto -> viewModel.alternarFavorito(producto) }
                )
            }
            scene("/menu_nino") {
                MenuNinoScreen(
                    onArticulo = { id -> navigator.navigate("/articulo/$id") },
                    onDama = { navigator.navigate("/menu_dama") },
                    onCaballero = { navigator.navigate("/menu_caballero") },
                    onPerfil = { navigator.navigate("/perfil") },
                    onCarrito = { navigator.navigate("/carrito") },
                    onFavoritos = { navigator.navigate("/favoritos") },
                    favoritosIds = uiState.favoritos.map { it.id },
                    onToggleFavorito = { producto -> viewModel.alternarFavorito(producto) }
                )
            }
            scene("/articulo/{id}") { entrada ->
                val id = entrada.path<String>("id") ?: "forro"
                ArticuloScreen(
                    producto = viewModel.buscarProducto(id),
                    onVolverAlMenu = { navigator.goBack() },
                    onAgregar = { producto, talla, cantidad ->
                        viewModel.agregarAlCarrito(producto, talla, cantidad)
                        navigator.navigate("/carrito")
                    },
                    onCarrito = { navigator.navigate("/carrito") }
                )
            }
            scene("/carrito") {
                CarritoScreen(
                    items = uiState.carrito,
                    onBackClick = { navigator.goBack() },
                    onIncreaseQuantity = { item -> viewModel.aumentarCantidad(item) },
                    onDecreaseQuantity = { item -> viewModel.disminuirCantidad(item) },
                    onRemoveItem = { item -> viewModel.quitarDelCarrito(item) },
                    onCheckoutClick = { /* pendiente: pantalla de pago */ }
                )
            }
            scene("/favoritos") {
                FavoritosScreen(
                    favoritos = uiState.favoritos,
                    onBackClick = { navigator.goBack() },
                    onCarritoClick = { navigator.navigate("/carrito") },
                    onPerfilClick = { navigator.navigate("/perfil") },
                    onFavoritoClick = { favorito -> navigator.navigate("/articulo/${favorito.id}") },
                    onRemoveFavorito = { favorito -> viewModel.quitarFavorito(favorito) }
                )
            }
            scene("/perfil") {
                PerfilScreen(
                    onBackClick = { navigator.goBack() },
                    onLogoClick = { navigator.navigate("/menu_dama") }
                )
            }
        }
    }
}