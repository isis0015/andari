package com.example.andari.ui.screens

import andari.shared.generated.resources.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.andari.model.Producto
import com.example.andari.ui.components.Caja
import com.example.andari.ui.components.Glifo
import com.example.andari.ui.components.Imagen
import com.example.andari.ui.components.MarcoFigma
import com.example.andari.ui.components.Texto
import com.example.andari.ui.theme.AndariColores
import com.example.andari.ui.theme.Fuentes


@Composable
fun ArticuloScreen(
    producto: Producto,
    onVolverAlMenu: () -> Unit,   // la flecha de regresar vuelve al menú de donde se llegó
    onAgregar: (producto: Producto, talla: Int, cantidad: Int) -> Unit = { _, _, _ -> },
    onCarrito: () -> Unit = {}
) {
    val raleway = Fuentes.raleway()
    val inter = Fuentes.inter()
    val fa = Fuentes.fontAwesome()
    val d = producto.disenoArticulo

    var talla by remember(producto.id) { mutableStateOf(producto.tallaInicial) }
    var cantidad by remember(producto.id) { mutableStateOf(1) }

    MarcoFigma(AndariColores.FondoArticulo, Res.drawable.bg_articulo, 361f, 800f) {


        val bajar = 30f
        Glifo("\uF060", 33.5f, 24f + bajar, 25f, Color.Black, fa, onClick = onVolverAlMenu)
        Imagen(Res.drawable.logo_andari, 63f, 18f + bajar, 225f, 81f, "Andarí")


        Imagen(producto.imagenArticulo, d.imgX, d.imgY, d.imgW, d.imgH, producto.nombre)

        // Título, precio y leyenda
        Texto(
            producto.nombre, 28f, 335f, 30f, AndariColores.TextoOscuro, raleway,
            w = d.tituloAncho, peso = FontWeight.ExtraBold, interlineado = 30f
        )
        Texto(
            "$" + producto.precio, d.precioCx - 37f, d.precioY, 24f, Color.Black, inter,
            w = 74f, peso = FontWeight.Bold, alinear = TextAlign.Center
        )
        Texto(
            "Artesania Mexicana", d.artesaniaCx - 63f, d.artesaniaY, 12f, AndariColores.GrisTexto, inter,
            w = 126f, peso = FontWeight.Bold, alinear = TextAlign.Center
        )

        // Tallas
        Texto("Talla", 34f, 448f, 24f, AndariColores.TextoOscuro, raleway, w = 70f, peso = FontWeight.Bold, interlineado = 30f)
        val posiciones = listOf(104f, 161f, 218f, 273f)
        producto.tallas.forEachIndexed { i, t ->
            val x = posiciones[i]
            Caja(
                x, 444f, 42f, 36f,
                if (t == talla) AndariColores.ChipSeleccionado else Color.White,
                15f, onClick = { talla = t }
            )
            Texto(
                t.toString(), x - 3f, 452f, 20f, Color.Black, inter,
                w = 48f, peso = FontWeight.Bold, alinear = TextAlign.Center, onClick = { talla = t }
            )
        }
        Texto(
            "Todas las tallas tienen la medida MX", 36f, 488f, 12f, AndariColores.GrisTexto, inter,
            w = 215f, peso = FontWeight.Bold, alinear = TextAlign.Center
        )

        // Descripción
        Texto(
            producto.descripcion, 31f, 517f, 14f, AndariColores.GrisDescripcion, inter,
            w = 287f, peso = FontWeight.Bold
        )

        // Cantidad
        Texto("Cantidad", 31f, 634f, 20f, AndariColores.TextoOscuro, raleway, w = 108f, peso = FontWeight.ExtraBold, interlineado = 30f)
        Imagen(Res.drawable.ic_menos, 134f, 646f, 18f, 4f, "Menos")
        Caja(124f, 630f, 38f, 34f, Color.Transparent, 0f, onClick = { if (cantidad > 1) cantidad-- })
        Caja(165f, 634f, 39f, 28f, Color.White, 15f)
        Texto(
            cantidad.toString(), 161f, 638f, 16f, Color.Black, inter,
            w = 48f, peso = FontWeight.Bold, alinear = TextAlign.Center
        )
        Imagen(Res.drawable.ic_mas, 217f, 639f, 18f, 18f, "Más")
        Caja(210f, 630f, 32f, 34f, Color.Transparent, 0f, onClick = { cantidad++ })


        Caja(45f, 702f, 229f, 54f, AndariColores.Cafe, 12f, onClick = { onAgregar(producto, talla, cantidad) })
        Imagen(Res.drawable.ic_bolsa, 73f, 716f, 24f, 26f, "Bolsa")
        Texto(
            "Agregar Al Carrito", 120f, 715f, 14f, Color.White, raleway,
            w = 130f, peso = FontWeight.SemiBold, interlineado = 22f
        )

        
        Imagen(Res.drawable.ic_carrito_menu, 288f, 715f, 28f, 28f, "Carrito", onClick = onCarrito)
    }
}