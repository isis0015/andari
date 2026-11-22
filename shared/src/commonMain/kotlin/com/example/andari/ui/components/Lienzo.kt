package com.example.andari.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

/**
 * Convierte los pixeles de Figma (1 px = 1 unidad de diseño) a dp reales
 * Cada elemento queda exactamente donde está en el diseño en cualquier teléfono
 */
class Lienzo(val escala: Float, private val densidad: Density) {

    fun dp(px: Float): Dp = (px * escala).dp

    fun sp(px: Float): TextUnit = with(densidad) { dp(px).toSp() }

    fun Modifier.en(x: Float, y: Float, w: Float? = null, h: Float? = null): Modifier {
        var m = this.offset(dp(x), dp(y))
        if (w != null) m = m.width(dp(w))
        if (h != null) m = m.height(dp(h))
        return m
    }
}

/**
 * [anchoDiseno] y [altoDiseno] son el tamaño del frame en Figma.
 * [fondo] es la imagen con los gradientes de color.
 */
@Composable
fun MarcoFigma(
    colorFondo: Color,
    fondo: DrawableResource,
    anchoDiseno: Float = 361f,
    altoDiseno: Float = 800f,
    contenido: @Composable Lienzo.() -> Unit
) {
    val densidad = LocalDensity.current
    BoxWithConstraints(Modifier.fillMaxSize().background(colorFondo)) {
        val anchoPantalla = maxWidth
        val escala = maxWidth.value / anchoDiseno
        val lienzo = remember(escala, densidad) { Lienzo(escala, densidad) }
        Box(Modifier.width(anchoPantalla).height((altoDiseno * escala).dp)) {
            Image(
                    painter = painterResource(fondo),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )
                lienzo.contenido()
            }
        }
    }


@Composable
fun Lienzo.Imagen(
    recurso: DrawableResource,
    x: Float, y: Float, w: Float, h: Float,
    descripcion: String? = null,
    onClick: (() -> Unit)? = null
) {
    var m = Modifier.en(x, y, w, h)
    if (onClick != null) m = m.clickable { onClick() }
    Image(
        painter = painterResource(recurso),
        contentDescription = descripcion,
        modifier = m,
        contentScale = ContentScale.FillBounds
    )
}

@Composable
fun Lienzo.Texto(
    texto: String,
    x: Float, y: Float,
    tam: Float,
    color: Color,
    fuente: FontFamily,
    w: Float? = null,
    peso: FontWeight? = null,
    estilo: FontStyle? = null,
    alinear: TextAlign = TextAlign.Start,
    interlineado: Float? = null,
    subrayado: Boolean = false,
    onClick: (() -> Unit)? = null
) {
    var m = Modifier.en(x, y, w)
    if (onClick != null) m = m.clickable { onClick() }
    Text(
        text = texto,
        modifier = m,
        color = color,
        fontFamily = fuente,
        fontWeight = peso,
        fontStyle = estilo,
        fontSize = sp(tam),
        lineHeight = if (interlineado != null) sp(interlineado) else TextUnit.Unspecified,
        textAlign = alinear,
        textDecoration = if (subrayado) TextDecoration.Underline else null
    )
}

// Ícono de la fuente Font Awesome, centrado en [cx]
@Composable
fun Lienzo.Glifo(
    codigo: String,
    cx: Float, y: Float,
    tam: Float,
    color: Color,
    fuente: FontFamily,
    onClick: (() -> Unit)? = null
) {
    Texto(
        texto = codigo, x = cx - tam, y = y, tam = tam, color = color, fuente = fuente,
        w = tam * 2, alinear = TextAlign.Center, onClick = onClick
    )
}

// Recuadro de color (botones, tarjetas, chips)
@Composable
fun Lienzo.Caja(
    x: Float, y: Float, w: Float, h: Float,
    color: Color,
    radio: Float = 0f,
    onClick: (() -> Unit)? = null
) {
    var m = Modifier.en(x, y, w, h).background(color, androidx.compose.foundation.shape.RoundedCornerShape(dp(radio)))
    if (onClick != null) m = m.clickable { onClick() }
    Box(m)
}

// Línea de 1 px (subrayado de los campos de texto)
@Composable
fun Lienzo.Linea(x: Float, y: Float, w: Float, color: Color) {
    Box(Modifier.en(x, y, w, 1f).background(color))
}

// Campo de texto: muestra la [sugerencia] (el texto que trae Figma) hasta que escribes
@Composable
fun Lienzo.Campo(
    valor: String,
    onCambio: (String) -> Unit,
    sugerencia: String,
    x: Float, y: Float, w: Float, h: Float,
    tam: Float,
    colorSugerencia: Color,
    colorTexto: Color,
    fuente: FontFamily,
    peso: FontWeight,
    alinear: TextAlign = TextAlign.Start,
    esPassword: Boolean = false
) {
    val estilo = TextStyle(
        color = colorTexto,
        fontFamily = fuente,
        fontWeight = peso,
        fontSize = sp(tam),
        textAlign = alinear
    )
    BasicTextField(
        value = valor,
        onValueChange = onCambio,
        modifier = Modifier.en(x, y, w, h),
        singleLine = true,
        textStyle = estilo,
        cursorBrush = SolidColor(Color.Black),
        visualTransformation = if (esPassword) PasswordVisualTransformation() else VisualTransformation.None,
        decorationBox = { campoInterno ->
            Box(Modifier.fillMaxSize()) {
                if (valor.isEmpty()) {
                    Text(
                        text = sugerencia,
                        modifier = Modifier.fillMaxSize(),
                        color = colorSugerencia,
                        fontFamily = fuente,
                        fontWeight = peso,
                        fontSize = sp(tam),
                        textAlign = alinear
                    )
                }
                campoInterno()
            }
        }
    )
}
