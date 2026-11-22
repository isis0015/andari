package com.example.andari.ui.screens

import andari.shared.generated.resources.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.andari.model.Catalogo
import com.example.andari.model.Producto
import com.example.andari.ui.components.Caja
import com.example.andari.ui.components.Glifo
import com.example.andari.ui.components.Imagen
import com.example.andari.ui.components.MarcoFigma
import com.example.andari.ui.components.Texto
import com.example.andari.ui.theme.AndariColores
import com.example.andari.ui.theme.Fuentes

enum class Categoria { DAMA, CABALLERO, NINO }


@Composable
fun MenuDamaScreen(
    onArticulo: (String) -> Unit,
    onCaballero: () -> Unit,
    onNino: () -> Unit,
    onPerfil: () -> Unit = {},
    onCarrito: () -> Unit = {},
    onFavoritos: () -> Unit = {},
    favoritosIds: List<String>,
    onToggleFavorito: (Producto) -> Unit
) {
    MenuCatalogo(
        seleccion = Categoria.DAMA,
        productos = Catalogo.dama,
        onArticulo = onArticulo,
        onDama = {},
        onCaballero = onCaballero,
        onNino = onNino,
        onCasita = {},
        onPerfil = onPerfil,
        onCarrito = onCarrito,
        onFavoritos = onFavoritos,
        favoritosIds = favoritosIds,
        onToggleFavorito = onToggleFavorito
    )
}


@Composable
fun MenuCaballeroScreen(
    onArticulo: (String) -> Unit,
    onDama: () -> Unit,
    onNino: () -> Unit,
    onPerfil: () -> Unit = {},
    onCarrito: () -> Unit = {},
    onFavoritos: () -> Unit = {},
    favoritosIds: List<String>,
    onToggleFavorito: (Producto) -> Unit
) {
    MenuCatalogo(
        seleccion = Categoria.CABALLERO,
        productos = Catalogo.caballero,
        onArticulo = onArticulo,
        onDama = onDama,
        onCaballero = {},
        onNino = onNino,
        onCasita = onDama,
        onPerfil = onPerfil,
        onCarrito = onCarrito,
        onFavoritos = onFavoritos,
        favoritosIds = favoritosIds,
        onToggleFavorito = onToggleFavorito
    )
}


@Composable
fun MenuNinoScreen(
    onArticulo: (String) -> Unit,
    onDama: () -> Unit,
    onCaballero: () -> Unit,
    onPerfil: () -> Unit = {},
    onCarrito: () -> Unit = {},
    onFavoritos: () -> Unit = {},
    favoritosIds: List<String>,
    onToggleFavorito: (Producto) -> Unit
) {
    MenuCatalogo(
        seleccion = Categoria.NINO,
        productos = Catalogo.nino,
        onArticulo = onArticulo,
        onDama = onDama,
        onCaballero = onCaballero,
        onNino = {},
        onCasita = onDama,
        onPerfil = onPerfil,
        onCarrito = onCarrito,
        onFavoritos = onFavoritos,
        favoritosIds = favoritosIds,
        onToggleFavorito = onToggleFavorito
    )
}


@Composable
private fun MenuCatalogo(
    seleccion: Categoria,
    productos: List<Producto>,
    onArticulo: (String) -> Unit,
    onDama: () -> Unit,
    onCaballero: () -> Unit,
    onNino: () -> Unit,
    onCasita: () -> Unit,
    onPerfil: () -> Unit,
    onCarrito: () -> Unit,
    onFavoritos: () -> Unit,
    favoritosIds: List<String>,
    onToggleFavorito: (Producto) -> Unit
) {
    val inter = Fuentes.inter()
    val nunito = Fuentes.nunito()
    val fa = Fuentes.fontAwesome()
    val esDama = seleccion == Categoria.DAMA
    val esCaballero = seleccion == Categoria.CABALLERO
    val esNino = seleccion == Categoria.NINO
    val fondo = if (esNino) Res.drawable.bg_menu_nino else Res.drawable.bg_menu

    MarcoFigma(Color.White, fondo, 361f, 800f) {


        val bajar = 30f
        Imagen(Res.drawable.logo_andari, 63f, 18f + bajar, 225f, 81f, "Andarí")

        // Buscador
        Caja(55f, 155f, 251f, 43f, AndariColores.CampoBusqueda, 30f)
        Texto("Buscar", 69f, 167f, 16f, AndariColores.NaranjaBuscar, inter, w = 112f, peso = FontWeight.Bold)
        Texto("\uF002", 264f, 167f, 16f, AndariColores.NaranjaLupa, fa)

        Texto(
            "¡Conoce un poco ", 49f, 210f, 14f, AndariColores.GrisDescripcion, inter,
            w = 270f, peso = FontWeight.Bold, alinear = TextAlign.Center
        )
        Texto(
            "de nuestro catálogo!", 49f, 227f, 14f, AndariColores.GrisDescripcion, inter,
            w = 270f, peso = FontWeight.Bold, alinear = TextAlign.Center
        )


        when (seleccion) {
            Categoria.DAMA -> Caja(64f, 263f, 58f, 34f, AndariColores.Negro, 10f)
            Categoria.CABALLERO -> Caja(137f, 264f, 88f, 34f, AndariColores.Negro, 10f)
            Categoria.NINO -> Caja(230f, 269f, 67f, 26f, AndariColores.Negro, 10f)
        }
        Texto(
            "Dama", 64f, 269f, 18f, if (esDama) AndariColores.BlancoSuave else Color.Black, nunito,
            w = 58f, peso = FontWeight.Black, alinear = TextAlign.Center, onClick = onDama
        )
        Texto(
            "Caballero", 132f, 268f, 18f, if (esCaballero) AndariColores.BlancoSuave else Color.Black, nunito,
            w = 98f, peso = FontWeight.Black, alinear = TextAlign.Center, onClick = onCaballero
        )
        Texto(
            "Niño", 236f, 270f, 18f, if (esNino) AndariColores.BlancoSuave else Color.Black, nunito,
            w = 57f, peso = FontWeight.Black, alinear = TextAlign.Center, onClick = onNino
        )


        productos.forEach { p ->
            val d = p.disenoMenu
            if (d.tarjetaConFondo) {
                Caja(d.tarjetaX, d.tarjetaY, 133f, 151f, Color.White, 15f)
            }
            Imagen(p.imagenMenu, d.imgX, d.imgY, d.imgW, d.imgH, p.nombreMenu)
            Texto(
                p.nombreMenu, d.nombreCx - 53f, d.nombreY, 10f, AndariColores.GrisTexto, inter,
                w = 106f, peso = FontWeight.Bold, alinear = TextAlign.Center
            )
            Texto(
                "$" + p.precioMenu, d.precioCx - 18f, d.precioY, 10f, Color.Black, inter,
                w = 36f, peso = FontWeight.Bold, alinear = TextAlign.Center
            )


            Caja(d.tarjetaX, d.tarjetaY, 133f, 151f, Color.Transparent, 15f, onClick = { onArticulo(p.id) })


            val esFavorito = favoritosIds.contains(p.id)
            val iconoCorazon = if (esFavorito) Res.drawable.ic_corazon_rojo else Res.drawable.btn_corazon

            Imagen(
                iconoCorazon,
                d.corazonX,
                d.corazonY,
                29f,
                28f,
                "Favorito",
                onClick = { onToggleFavorito(p) }
            )
        }

        
        Imagen(Res.drawable.ic_home_menu, 85f, 691f, 26f, 26f, "Inicio", onClick = onCasita)
        Imagen(Res.drawable.ic_corazon_menu, 142f, 694f, 22f, 20f, "Favoritos", onClick = onFavoritos)
        Imagen(Res.drawable.ic_carrito_menu, 195f, 691f, 26f, 26f, "Carrito", onClick = onCarrito)
        Imagen(Res.drawable.ic_perfil_menu, 253f, 691f, 20f, 26f, "Perfil", onClick = onPerfil)
    }
}