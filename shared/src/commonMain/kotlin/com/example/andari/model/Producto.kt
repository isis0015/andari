package com.example.andari.model

import andari.shared.generated.resources.*
import org.jetbrains.compose.resources.DrawableResource


data class DisenoArticulo(
    val imgX: Float, val imgY: Float, val imgW: Float, val imgH: Float,
    val tituloAncho: Float,
    val precioCx: Float, val precioY: Float,
    val artesaniaCx: Float, val artesaniaY: Float
)


data class DisenoMenu(
    val tarjetaX: Float, val tarjetaY: Float, val tarjetaConFondo: Boolean,
    val imgX: Float, val imgY: Float, val imgW: Float, val imgH: Float,
    val nombreCx: Float, val nombreY: Float,
    val precioCx: Float, val precioY: Float,
    val corazonX: Float, val corazonY: Float
)

data class Producto(
    val id: String,
    val nombre: String,
    val nombreMenu: String,
    val precio: Int,
    val descripcion: String,
    val tallas: List<Int>,
    val tallaInicial: Int,
    val imagenArticulo: DrawableResource,
    val imagenMenu: DrawableResource,
    val disenoArticulo: DisenoArticulo,
    val disenoMenu: DisenoMenu,
    val precioMenu: Int = precio
)

object Catalogo {

    val dama: List<Producto> = listOf(
        Producto(
            id = "forro",
            nombre = "Dama Forro de Colores Trenza",
            nombreMenu = "Dama Forro Colores Trenza",
            precio = 520,
            descripcion = "Huarache artesanal elaborado a mano por artesanos mexicanos. Hecho con piel genuina y de alta calidad con una suela comoda y duradera, ideal para el diario. El trensado colorido refleja nuestra tradición.",
            tallas = listOf(23, 24, 25, 26),
            tallaInicial = 24,
            imagenArticulo = Res.drawable.articulo_forro_colores_trenza,
            imagenMenu = Res.drawable.menu_forro,
            disenoArticulo = DisenoArticulo(63f, 116f, 268f, 167f, 189f, 267f, 348f, 261f, 383f),
            disenoMenu = DisenoMenu(42f, 325f, true, 69f, 335f, 87f, 67f, 109f, 400f, 69f, 444f, 135f, 435f)
        ),
        Producto(
            id = "bordado",
            nombre = "Dama Bordado Pulsera",
            nombreMenu = "Dama Bordado Pulsera",
            precio = 580,
            descripcion = "Huarache artesanal elaborado a mano por artesanos mexicanos. Confeccionado en piel de alta calidad, con bordados florales y trenzado multicolor que resaltan nuestra tradición. Cómodo, duradero e ideal para el diario.",
            tallas = listOf(23, 24, 25, 26),
            tallaInicial = 24,
            imagenArticulo = Res.drawable.articulo_bordado_pulsera,
            imagenMenu = Res.drawable.menu_bordado,
            disenoArticulo = DisenoArticulo(60f, 143f, 245f, 141f, 221f, 286f, 374f, 276f, 400f),
            disenoMenu = DisenoMenu(186f, 325f, true, 211f, 343f, 90f, 46f, 248f, 403f, 222f, 444f, 279f, 435f)
        ),
        Producto(
            id = "tacon",
            nombre = "Dama Tacon Plataforma",
            nombreMenu = "Dama tacón plataforma",
            precio = 450,
            descripcion = "Tacon con diseño elegante, elaborada con materiales de calidad y correas ajustables. Su plataforma con acabado trenzado brinda altura y comodidad, ideal para lucir un estilo moderno y sofisticado en cualquier ocasión.",
            tallas = listOf(23, 24, 25, 26),
            tallaInicial = 25,
            imagenArticulo = Res.drawable.articulo_tacon_plataforma,
            imagenMenu = Res.drawable.menu_tacon,
            disenoArticulo = DisenoArticulo(81f, 111f, 203f, 181f, 189f, 292f, 336f, 297f, 368f),
            disenoMenu = DisenoMenu(42f, 490f, true, 72f, 496f, 74f, 79f, 109f, 579f, 69f, 610f, 139f, 601f)
        ),
        Producto(
            id = "esqueleto",
            nombre = "Dama Esqueleto",
            nombreMenu = "Dama Esqueleto",
            precio = 590,
            descripcion = "Huarache artesanal elaborado a mano por artesanos mexicanos. Hecho con piel genuina y de alta calidad con una suela comoda y duradera, ideal para el diario. El trensado colorido refleja nuestra tradición.",
            tallas = listOf(23, 24, 25, 26),
            tallaInicial = 24,
            imagenArticulo = Res.drawable.articulo_esqueleto,
            imagenMenu = Res.drawable.menu_esqueleto,
            disenoArticulo = DisenoArticulo(50f, 117f, 268f, 168f, 189f, 267f, 348f, 261f, 383f),
            disenoMenu = DisenoMenu(186f, 490f, false, 213f, 510f, 88f, 64f, 248f, 579f, 217f, 611f, 279f, 602f)
        )
    )

    val caballero: List<Producto> = listOf(
        Producto(
            id = "cab_trenzado",
            nombre = "Huarache Trenzado",
            nombreMenu = "Huarache Trenzado",
            precio = 520,
            descripcion = "Huarache color café con diseño trenzado y acabado artesanal que le brinda un estilo único. Ideal para complementar tus outfits casuales con un toque tradicional y elegante.",
            tallas = listOf(25, 26, 27, 28),
            tallaInicial = 26,
            imagenArticulo = Res.drawable.articulo_cab_trenzado,
            imagenMenu = Res.drawable.menu_cab_trenzado,
            disenoArticulo = DisenoArticulo(71f, 126f, 219f, 168f, 221f, 286f, 374f, 276f, 400f),
            disenoMenu = DisenoMenu(42f, 325f, true, 75f, 330f, 74f, 57f, 109f, 400f, 69f, 444f, 135f, 435f)
        ),
        Producto(
            id = "cab_vinipiel",
            nombre = "Huarache tipo Sandalia Vinipiel",
            nombreMenu = "Huarache tipo Sandalia Vinipiel",
            precio = 580,
            descripcion = "Sandalia tipo huarache en color café oscuro, elaborada con tiras de vinipiel entrelazadas. Su diseño moderno y versátil es perfecto para lucir un estilo casual en cualquier ocasión.",
            tallas = listOf(25, 26, 27, 28),
            tallaInicial = 26,
            imagenArticulo = Res.drawable.articulo_cab_vinipiel,
            imagenMenu = Res.drawable.menu_cab_vinipiel,
            disenoArticulo = DisenoArticulo(45f, 75f, 262f, 262f, 221f, 286f, 374f, 276f, 400f),
            disenoMenu = DisenoMenu(186f, 325f, true, 210f, 321f, 91f, 91f, 248f, 403f, 222f, 444f, 279f, 435f)
        ),
        Producto(
            id = "cab_cuero",
            nombre = "Huarache de Cuero",
            nombreMenu = "Huarache de Cuero",
            precio = 450,
            descripcion = "Huarache en tono café con diseño de varias tiras y acabado clásico. Una opción práctica y atemporal para quienes buscan un calzado versátil que combine fácilmente con diferentes prendas.",
            tallas = listOf(25, 26, 27, 28),
            tallaInicial = 26,
            imagenArticulo = Res.drawable.articulo_cab_cuero,
            imagenMenu = Res.drawable.menu_cab_cuero,
            disenoArticulo = DisenoArticulo(51f, 37f, 262f, 262f, 221f, 286f, 374f, 276f, 400f),
            disenoMenu = DisenoMenu(42f, 490f, true, 63f, 479f, 96f, 96f, 109f, 579f, 69f, 610f, 139f, 601f)
        ),
        Producto(
            id = "cab_bordado",
            nombre = "Huarache Sandalia con Bordado",
            nombreMenu = "Huarache Sandalia con Bordado",
            precio = 590,
            descripcion = "Sandalia tipo huarache en tono beige con detalles bordados que resaltan su diseño artesanal. Ideal para quienes buscan un calzado original, con un toque tradicional y perfecto para los días cálidos.",
            tallas = listOf(25, 26, 27, 28),
            tallaInicial = 26,
            imagenArticulo = Res.drawable.articulo_cab_bordado,
            imagenMenu = Res.drawable.menu_cab_bordado,
            disenoArticulo = DisenoArticulo(59f, 131f, 242f, 152f, 221f, 286f, 374f, 276f, 400f),
            disenoMenu = DisenoMenu(186f, 490f, false, 216f, 523f, 75f, 48f, 248f, 579f, 217f, 611f, 279f, 602f)
        )
    )

    val nino: List<Producto> = listOf(
        Producto(
            id = "nino_suela_negra",
            nombre = "Niño Forro Café con Suela Negra",
            nombreMenu = "Niño Forro Café con Suela Negra",
            precio = 420,
            descripcion = "Huarache infantil en tonos café con detalles en beige y suela negra. Su diseño de tiras y ajuste con velcro brinda practicidad y un estilo casual, ideal para acompañar a los pequeños en sus actividades diarias.",
            tallas = listOf(10, 12, 14, 16),
            tallaInicial = 12,
            imagenArticulo = Res.drawable.articulo_nino_suela_negra,
            imagenMenu = Res.drawable.menu_nino_suela_negra,
            disenoArticulo = DisenoArticulo(80f, 117f, 208f, 208f, 221f, 286f, 374f, 276f, 400f),
            disenoMenu = DisenoMenu(42f, 325f, true, 75f, 332f, 74f, 74f, 109f, 400f, 69f, 444f, 135f, 435f)
        ),
        Producto(
            id = "nino_trenza",
            nombre = "Unisex Forro Trenza",
            nombreMenu = "Unisex Forro Trenza",
            precio = 480,
            descripcion = "Huarache unisex color café miel con diseño de tiras cruzadas y acabado trenzado. Su estilo moderno y versátil lo convierte en una excelente opción para complementar looks casuales durante los días cálidos.",
            tallas = listOf(10, 12, 14, 16),
            tallaInicial = 12,
            imagenArticulo = Res.drawable.articulo_nino_trenza,
            imagenMenu = Res.drawable.menu_nino_trenza,
            disenoArticulo = DisenoArticulo(63f, 104f, 227f, 227f, 221f, 286f, 374f, 276f, 400f),
            disenoMenu = DisenoMenu(186f, 325f, true, 215f, 325f, 82f, 82f, 248f, 403f, 222f, 444f, 279f, 435f)
        ),
        Producto(
            id = "nino_colores",
            nombre = "Unisex Forro de Colores",
            nombreMenu = "Unisex Forro de Colores",
            precio = 450,
            descripcion = "Huarache unisex con una combinación de tonos café, terracota y beige que le da un toque original. Su diseño de varias tiras y hebilla combina comodidad y estilo para el uso diario.",
            tallas = listOf(10, 12, 14, 16),
            tallaInicial = 12,
            imagenArticulo = Res.drawable.articulo_nino_colores,
            imagenMenu = Res.drawable.menu_nino_colores,
            disenoArticulo = DisenoArticulo(69f, 145f, 193f, 144f, 221f, 286f, 374f, 276f, 400f),
            disenoMenu = DisenoMenu(42f, 490f, true, 76f, 513f, 70f, 53f, 109f, 579f, 69f, 610f, 139f, 601f)
        ),
        Producto(
            id = "nino_tonos_cafe",
            nombre = "Unisex Forro en Tonos Café",
            nombreMenu = "Unisex Forro en Tonos Café",
            precio = 450,       // en Figma el artículo dice $450...
            precioMenu = 490,   // ...y la tarjeta del menú dice $490
            descripcion = "Huarache unisex en diferentes tonalidades de café, con detalles decorativos y hebilla lateral. Su diseño de tiras ofrece un estilo clásico y casual, ideal para quienes buscan un calzado versátil y fácil de combinar.",
            tallas = listOf(10, 12, 14, 16),
            tallaInicial = 12,
            imagenArticulo = Res.drawable.articulo_nino_tonos_cafe,
            imagenMenu = Res.drawable.menu_nino_tonos_cafe,
            disenoArticulo = DisenoArticulo(55f, 130f, 223f, 175f, 221f, 286f, 374f, 276f, 400f),
            disenoMenu = DisenoMenu(186f, 490f, false, 213f, 510f, 88f, 64f, 248f, 579f, 217f, 611f, 279f, 602f)
        )
    )

    // Busca en todos los catálogos (Dama, Caballero y Niño)
    fun buscar(id: String): Producto = (dama + caballero + nino).first { it.id == id }
}
