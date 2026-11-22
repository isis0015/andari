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
import com.example.andari.ui.components.Caja
import com.example.andari.ui.components.Campo
import com.example.andari.ui.components.Glifo
import com.example.andari.ui.components.Imagen
import com.example.andari.ui.components.Linea
import com.example.andari.ui.components.MarcoFigma
import com.example.andari.ui.components.Texto
import com.example.andari.ui.theme.AndariColores
import com.example.andari.ui.theme.Fuentes


@Composable
fun RecuperarScreen(
    onCambiar: () -> Unit,
    onInicio: () -> Unit
) {
    val nunito = Fuentes.nunito()
    val fa = Fuentes.fontAwesome()

    var nueva by remember { mutableStateOf("") }
    var repetir by remember { mutableStateOf("") }

    MarcoFigma(Color.White, Res.drawable.bg_recuperar, 361f, 800f) {

        Texto(
            "RECUPERER", 74f, 84f, 20f, Color.Black, nunito,
            w = 212f, peso = FontWeight.Bold, alinear = TextAlign.Center
        )
        Texto(
            "CONTRASEÑA", 74f, 108f, 20f, Color.Black, nunito,
            w = 212f, peso = FontWeight.Bold, alinear = TextAlign.Center
        )


        Glifo("\uF023", 180.5f, 207f, 40f, Color.Black, fa)


        Campo(
            nueva, { nueva = it }, "Nueva Contraseña:", 60f, 304f, 160f, 25f, 15f,
            AndariColores.GrisEtiqueta, Color.Black, nunito, FontWeight.Bold,
            alinear = TextAlign.Center, esPassword = true
        )
        Glifo("\uF084", 262.5f, 308f, 15f, AndariColores.GrisEtiqueta, fa)
        Linea(74f, 335f, 210f, AndariColores.LineaCampo)


        Campo(
            repetir, { repetir = it }, "Repetir Contraseña:", 72f, 414f, 150f, 25f, 15f,
            AndariColores.GrisEtiqueta, Color.Black, nunito, FontWeight.Bold,
            alinear = TextAlign.Center, esPassword = true
        )
        Glifo("\uF084", 269.5f, 418f, 15f, AndariColores.GrisEtiqueta, fa)
        Linea(68f, 441.5f, 210f, AndariColores.LineaCampo)


        Caja(72f, 533f, 216f, 54f, AndariColores.DoradoClaro, 10f, onClick = onCambiar)
        Texto(
            "Cambiar contraseña", 80.5f, 547f, 18f, Color.Black, nunito,
            w = 200f, peso = FontWeight.Bold, alinear = TextAlign.Center,
            subrayado = true, onClick = onCambiar
        )


        Imagen(Res.drawable.ic_home_recuperar, 37f, 704f, 45f, 39f, "Inicio", onClick = onInicio)
        Glifo("\uF2EA", 302f, 704f, 36f, Color.Black, fa, onClick = onCambiar)
    }
}
