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
import com.example.andari.ui.components.MarcoFigma
import com.example.andari.ui.components.Texto
import com.example.andari.ui.theme.AndariColores
import com.example.andari.ui.theme.Fuentes


@Composable
fun CrearCuentaScreen(
    onAtras: () -> Unit,
    onGuardar: () -> Unit
) {
    val raleway = Fuentes.raleway()
    val poppins = Fuentes.poppins()
    val montserrat = Fuentes.montserratSubrayada()
    val fa = Fuentes.fontAwesome()

    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var clave by remember { mutableStateOf("") }
    var verClave by remember { mutableStateOf(false) }

    MarcoFigma(Color.White, Res.drawable.bg_crear_cuenta, 375f, 812f) {

        Imagen(Res.drawable.btn_atras, 18f, 74f, 48f, 48f, "Regresar", onClick = onAtras)

        // Usuario
        Glifo("\uF007", 190.5f, 76f, 40f, AndariColores.IconoUsuario, fa)

        Texto(
            "Crear cuenta", 19f, 170f, 30f, AndariColores.TextoOscuro, raleway,
            w = 196f, peso = FontWeight.SemiBold, interlineado = 20f
        )

        // Nombre
        Texto("Nombre", 20f, 236f, 16f, AndariColores.TextoSecundario, raleway, peso = FontWeight.SemiBold, interlineado = 20f)
        Caja(20f, 268f, 335f, 48f, AndariColores.CampoPerfil, 14f)
        Campo(
            nombre, { nombre = it }, "Juan Perez", 34f, 284f, 300f, 16f, 14f,
            AndariColores.TextoOscuro, AndariColores.TextoOscuro, poppins, FontWeight.Medium
        )

        // Email
        Texto("Email", 20f, 328f, 16f, AndariColores.TextoSecundario, raleway, peso = FontWeight.SemiBold, interlineado = 20f)
        Caja(20f, 360f, 335f, 48f, AndariColores.CampoPerfil, 14f)
        Campo(
            correo, { correo = it }, "juanperez@gmail.com", 34f, 376f, 300f, 16f, 14f,
            AndariColores.TextoOscuro, AndariColores.TextoOscuro, poppins, FontWeight.Medium
        )

        // Contraseña
        Texto("Contraseña", 20f, 438f, 16f, AndariColores.TextoSecundario, raleway, peso = FontWeight.SemiBold, interlineado = 20f)
        Caja(20f, 470f, 335f, 48f, AndariColores.CampoPerfil, 14f)
        if (clave.isEmpty()) {
            // Los puntitos de ejemplo que trae el diseño
            Imagen(Res.drawable.ic_puntos, 32f, 489f, 73f, 10f)
        }
        Campo(
            clave, { clave = it }, "", 34f, 484f, 280f, 20f, 14f,
            AndariColores.TextoOscuro, AndariColores.TextoOscuro, poppins, FontWeight.Medium,
            esPassword = !verClave
        )
        Texto(
            "Ver contraseña", 262f, 526f, 12f, AndariColores.TextoSecundario, poppins,
            w = 93f, alinear = TextAlign.End, onClick = { verClave = !verClave }
        )

        
        Caja(20f, 572f, 335f, 50f, AndariColores.DoradoClaro, 12f, onClick = onGuardar)
        Texto(
            "Guardar", 20f, 586f, 16f, Color.Black, montserrat,
            w = 335f, peso = FontWeight.Bold, alinear = TextAlign.Center, onClick = onGuardar
        )
    }
}
