package com.example.andari.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import andari.shared.generated.resources.Res
import andari.shared.generated.resources.logo_andari
import andari.shared.generated.resources.logo_subtitulo
import com.example.andari.model.Pedido
import com.example.andari.ui.theme.AndariColores
import org.jetbrains.compose.resources.painterResource

@Composable
fun CompraRealizadaScreen(
    pedido: Pedido?,
    onSeguirComprando: () -> Unit = {}
) {
    Box(
        Modifier.fillMaxSize()
            .background(Brush.verticalGradient(listOf(Color(0xFFF5D9A8), Color(0xFFFFF8EE))))
    ) {
        Column(
            Modifier.fillMaxSize().statusBarsPadding().padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(24.dp))
            Image(
                painterResource(Res.drawable.logo_andari), "Andarí",
                Modifier.width(160.dp).height(48.dp), contentScale = ContentScale.Fit
            )
            Image(
                painterResource(Res.drawable.logo_subtitulo), "Huaraches artesanales",
                Modifier.width(120.dp).height(10.dp), contentScale = ContentScale.Fit
            )
            Spacer(Modifier.weight(1f))

            Column(
                Modifier.fillMaxWidth().clip(RoundedCornerShape(24.dp)).background(Color.White).padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Box(
                    Modifier.size(88.dp).clip(CircleShape).background(Color(0xFFFCE2AF)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.CheckCircle, null, tint = AndariColores.Cafe, modifier = Modifier.size(56.dp))
                }
                Text(
                    "¡Compra realizada!", fontSize = 24.sp, fontWeight = FontWeight.Bold,
                    color = AndariColores.TextoOscuro, textAlign = TextAlign.Center
                )
                Text(
                    "Gracias por apoyar el trabajo artesanal.",
                    fontSize = 14.sp, color = AndariColores.GrisTexto, textAlign = TextAlign.Center
                )
                if (pedido != null) {
                    Spacer(Modifier.height(6.dp))
                    Dato("Pedido", pedido.numero)
                    Dato("Total", "$${pedido.total.toInt()}")
                    Dato("Pago", pedido.datos.metodoPago.titulo)
                    Dato("Enviar a", pedido.datos.direccionCompleta)
                }
            }

            Spacer(Modifier.weight(1f))
            BotonAndari("SEGUIR COMPRANDO", onSeguirComprando)
        }
    }
}

@Composable
private fun Dato(etiqueta: String, valor: String) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(etiqueta, fontSize = 13.sp, color = AndariColores.GrisTexto)
        Spacer(Modifier.width(12.dp))
        Text(
            valor, fontSize = 13.sp, fontWeight = FontWeight.SemiBold,
            color = AndariColores.TextoOscuro, textAlign = TextAlign.End, modifier = Modifier.weight(1f)
        )
    }
}
