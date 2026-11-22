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
fun LoginScreen(
    onEntrar: () -> Unit,
    onOlvide: () -> Unit,
    onInicio: () -> Unit
) {
    val nunito = Fuentes.nunito()
    val inter = Fuentes.inter()
    val montserrat = Fuentes.montserratSubrayada()
    val fa = Fuentes.fontAwesome()

    var correo by remember { mutableStateOf("") }
    var clave by remember { mutableStateOf("") }

    MarcoFigma(Color.White, Res.drawable.bg_login, 361f, 800f) {
        Imagen(Res.drawable.logo_andari, 33f, 93f, 295f, 106f, "Andarí")


        Glifo("\uF007", 180.5f, 262f, 40f, AndariColores.DoradoClaro, fa)

        Texto(
            "¡Bienvenido!", 76f, 333f, 15f, Color.Black, nunito,
            w = 212f, peso = FontWeight.Bold, alinear = TextAlign.Center
        )

        // E-mail
        Campo(
            correo, { correo = it }, "E- mail:", 80f, 404f, 210f, 25f, 15f,
            AndariColores.GrisEtiqueta, Color.Black, nunito, FontWeight.Bold
        )
        Linea(80f, 428.5f, 210f, AndariColores.LineaCampo)

        // Contraseña
        Campo(
            clave, { clave = it }, "Contraseña:", 76f, 486f, 214f, 25f, 15f,
            AndariColores.GrisEtiqueta, Color.Black, nunito, FontWeight.Bold,
            esPassword = true
        )
        Linea(76f, 519.5f, 214f, AndariColores.LineaCampo)


        Caja(92f, 577f, 189f, 48f, AndariColores.DoradoClaro, 10f, onClick = onEntrar)
        Texto(
            "Entrar", 148f, 589f, 17f, Color.Black, montserrat,
            w = 90f, peso = FontWeight.Bold, onClick = onEntrar
        )

        Texto(
            "¿Olviaste tú contraseña? ", 110f, 641f, 12f, AndariColores.TextoOlvidaste, inter,
            peso = FontWeight.Bold, subrayado = true, onClick = onOlvide
        )


        Imagen(Res.drawable.ic_home_login, 43f, 707f, 45f, 39f, "Inicio", onClick = onInicio)
        Glifo("\uF2EA", 300f, 707f, 36f, Color.Black, fa, onClick = onInicio)
    }
}
