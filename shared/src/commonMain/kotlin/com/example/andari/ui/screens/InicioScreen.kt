package com.example.andari.ui.screens

import andari.shared.generated.resources.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import com.example.andari.ui.components.Imagen
import com.example.andari.ui.components.MarcoFigma
import com.example.andari.ui.components.Texto
import com.example.andari.ui.theme.AndariColores
import com.example.andari.ui.theme.Fuentes


@Composable
fun InicioScreen(onComenzar: () -> Unit, onRegistrate: () -> Unit) {
    val inter = Fuentes.inter()
    val montserrat = Fuentes.montserratSubrayada()

    MarcoFigma(AndariColores.FondoInicio, Res.drawable.bg_inicio, 361f, 818f) {
        Imagen(Res.drawable.logo_andari_texto, 7f, 85f, 346f, 126f, "Andarí")
        Imagen(Res.drawable.inicio_huaraches, 0f, 73f, 361f, 565f, "Huaraches")
        Imagen(Res.drawable.logo_subtitulo, 47f, 548f, 267f, 24f, "Huaraches artesanales")


        Imagen(Res.drawable.btn_comenzar, 75f, 635f, 217f, 62f, onClick = onComenzar)
        Texto(
            "Comenzar", 128f, 654f, 17f, Color.Black, montserrat,
            w = 113f, peso = FontWeight.Bold, onClick = onComenzar
        )

       
        Row(modifier = androidx.compose.ui.Modifier.en(67f, 713f)) {
            Text(
                "¿No tienes una cuenta?", color = Color.Black, fontFamily = inter,
                fontWeight = FontWeight.Normal, fontSize = sp(15f)
            )
            Text(" ", fontFamily = inter, fontSize = sp(15f))
            Text(
                "Regístrate", color = AndariColores.DoradoClaro, fontFamily = inter,
                fontWeight = FontWeight.ExtraBold, fontStyle = FontStyle.Italic,
                fontSize = sp(15f), textDecoration = TextDecoration.Underline,
                modifier = androidx.compose.ui.Modifier.clickable { onRegistrate() }
            )
        }
    }
}
