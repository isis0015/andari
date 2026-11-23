package com.example.andari.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import andari.shared.generated.resources.Res
import andari.shared.generated.resources.logo_andari
import andari.shared.generated.resources.logo_subtitulo
import com.example.andari.model.CartItem
import com.example.andari.model.DatosPedido
import com.example.andari.model.MetodoPago
import com.example.andari.ui.theme.AndariColores
import org.jetbrains.compose.resources.painterResource

private val FondoArriba = Color(0xFFF5D9A8)
private val FondoAbajo = Color(0xFFFFF8EE)
private val Amarillo = Color(0xFFF8D9A0)

@Composable
fun PedidoScreen(
    items: List<CartItem>,
    total: Double,
    onBackClick: () -> Unit = {},
    onAceptar: (DatosPedido) -> Unit = {}
) {
    var nombre by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var calle by remember { mutableStateOf("") }
    var colonia by remember { mutableStateOf("") }
    var ciudad by remember { mutableStateOf("") }
    var estado by remember { mutableStateOf("") }
    var cp by remember { mutableStateOf("") }
    var referencias by remember { mutableStateOf("") }
    var metodo by remember { mutableStateOf(MetodoPago.EFECTIVO) }

    // Datos de tarjeta: solo se validan, NO se guardan
    var numTarjeta by remember { mutableStateOf("") }
    var titular by remember { mutableStateOf("") }
    var vence by remember { mutableStateOf("") }
    var cvv by remember { mutableStateOf("") }

    var intentoEnviar by remember { mutableStateOf(false) }

    val datosOk = nombre.isNotBlank() && telefono.length == 10 && calle.isNotBlank() &&
            colonia.isNotBlank() && ciudad.isNotBlank() && estado.isNotBlank() && cp.length == 5
    val tarjetaOk = metodo != MetodoPago.TARJETA ||
            (numTarjeta.length == 16 && titular.isNotBlank() && vence.length == 4 && cvv.length >= 3)
    val formularioOk = datosOk && tarjetaOk && items.isNotEmpty()

    Box(
        Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(FondoArriba, FondoAbajo)))
    ) {
        Column(Modifier.fillMaxSize().statusBarsPadding().imePadding()) {

            // Encabezado: regresar + logo
            Row(
                Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, "Atrás", tint = AndariColores.TextoOscuro)
                }
                Spacer(Modifier.weight(1f))
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painterResource(Res.drawable.logo_andari), "Andarí",
                        Modifier.width(160.dp).height(48.dp), contentScale = ContentScale.Fit
                    )
                    Image(
                        painterResource(Res.drawable.logo_subtitulo), "Huaraches artesanales",
                        Modifier.width(120.dp).height(10.dp), contentScale = ContentScale.Fit
                    )
                }
                Spacer(Modifier.weight(1f))
                Spacer(Modifier.size(48.dp))
            }

            Text(
                "Pedido", fontSize = 24.sp, fontWeight = FontWeight.Bold,
                color = AndariColores.TextoOscuro,
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)
            )

            if (items.isEmpty()) {
                Column(
                    Modifier.fillMaxWidth().weight(1f).padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        "No hay productos en tu pedido", fontSize = 18.sp, fontWeight = FontWeight.Bold,
                        color = AndariColores.TextoOscuro, textAlign = TextAlign.Center
                    )
                    Spacer(Modifier.height(16.dp))
                    BotonAndari("VOLVER", onClick = onBackClick)
                }
            } else {
                Column(
                    Modifier.weight(1f).verticalScroll(rememberScrollState()).padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    // 1. Resumen
                    TarjetaSeccion("Resumen de tu pedido") {
                        items.forEach { item -> FilaResumen(item) }
                        HorizontalDivider(color = Color(0xFFEEEEEE))
                        Spacer(Modifier.height(8.dp))
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("Total", fontSize = 18.sp, fontWeight = FontWeight.SemiBold, color = AndariColores.TextoOscuro)
                            Text("$${total.toInt()}", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = AndariColores.TextoOscuro)
                        }
                    }

                    // 2. Dirección
                    TarjetaSeccion("Dirección de entrega") {
                        CampoPedido(nombre, { nombre = it }, "Nombre completo", intentoEnviar && nombre.isBlank())
                        CampoPedido(
                            telefono, { telefono = it.filter(Char::isDigit).take(10) }, "Teléfono (10 dígitos)",
                            intentoEnviar && telefono.length != 10, KeyboardType.Phone
                        )
                        CampoPedido(calle, { calle = it }, "Calle y número", intentoEnviar && calle.isBlank())
                        CampoPedido(colonia, { colonia = it }, "Colonia", intentoEnviar && colonia.isBlank())
                        CampoPedido(ciudad, { ciudad = it }, "Ciudad", intentoEnviar && ciudad.isBlank())
                        CampoPedido(estado, { estado = it }, "Estado", intentoEnviar && estado.isBlank())
                        CampoPedido(
                            cp, { cp = it.filter(Char::isDigit).take(5) }, "Código postal",
                            intentoEnviar && cp.length != 5, KeyboardType.Number
                        )
                        CampoPedido(referencias, { referencias = it }, "Referencias (opcional)", false)
                    }

                    // 3. Método de pago
                    TarjetaSeccion("Método de pago") {
                        MetodoPago.entries.forEach { m ->
                            OpcionPago(m, seleccionado = metodo == m, onClick = { metodo = m })
                        }
                        if (metodo == MetodoPago.TARJETA) {
                            Spacer(Modifier.height(4.dp))
                            CampoPedido(
                                numTarjeta, { numTarjeta = it.filter(Char::isDigit).take(16) },
                                "Número de tarjeta (16 dígitos)",
                                intentoEnviar && numTarjeta.length != 16, KeyboardType.Number
                            )
                            CampoPedido(titular, { titular = it }, "Nombre del titular", intentoEnviar && titular.isBlank())
                            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                                Box(Modifier.weight(1f)) {
                                    CampoPedido(
                                        vence, { vence = it.filter(Char::isDigit).take(4) }, "Vence (MMAA)",
                                        intentoEnviar && vence.length != 4, KeyboardType.Number
                                    )
                                }
                                Box(Modifier.weight(1f)) {
                                    CampoPedido(
                                        cvv, { cvv = it.filter(Char::isDigit).take(4) }, "CVV",
                                        intentoEnviar && cvv.length < 3, KeyboardType.Number
                                    )
                                }
                            }
                        }
                    }

                    if (intentoEnviar && !formularioOk) {
                        Text(
                            "Revisa los campos marcados en rojo para continuar.",
                            color = Color(0xFFC62828), fontSize = 13.sp
                        )
                    }
                    Spacer(Modifier.height(4.dp))
                }

                // Botón fijo abajo
                Column(Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 14.dp)) {
                    BotonAndari("ACEPTAR") {
                        intentoEnviar = true
                        if (formularioOk) {
                            onAceptar(
                                DatosPedido(
                                    nombre.trim(), telefono, calle.trim(), colonia.trim(), ciudad.trim(),
                                    estado.trim(), cp, referencias.trim(), metodo
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
internal fun BotonAndari(texto: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth().height(52.dp),
        shape = RoundedCornerShape(26.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Amarillo, contentColor = AndariColores.TextoOscuro),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
    ) {
        Text(texto, fontSize = 15.sp, fontWeight = FontWeight.SemiBold, letterSpacing = 1.sp)
    }
}

@Composable
private fun TarjetaSeccion(titulo: String, contenido: @Composable ColumnScope.() -> Unit) {
    Column(
        Modifier.fillMaxWidth().clip(RoundedCornerShape(20.dp)).background(Color.White).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(titulo, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = AndariColores.TextoOscuro)
        contenido()
    }
}

@Composable
private fun FilaResumen(item: CartItem) {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Box(
            Modifier.size(48.dp).clip(RoundedCornerShape(10.dp)).background(Color(0xFFF9F9F9)),
            contentAlignment = Alignment.Center
        ) {
            if (item.imagen != null) {
                Image(
                    painterResource(item.imagen), item.nombre,
                    Modifier.fillMaxSize().padding(3.dp), contentScale = ContentScale.Fit
                )
            }
        }
        Spacer(Modifier.width(10.dp))
        Column(Modifier.weight(1f)) {
            Text(item.nombre, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = AndariColores.TextoOscuro, maxLines = 2)
            Text("Talla ${item.talla} · ${item.cantidad} x $${item.precio.toInt()}", fontSize = 12.sp, color = AndariColores.GrisTexto)
        }
        Text("$${item.subtotal.toInt()}", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = AndariColores.TextoOscuro)
    }
}

@Composable
private fun CampoPedido(
    valor: String,
    onCambio: (String) -> Unit,
    etiqueta: String,
    error: Boolean,
    teclado: KeyboardType = KeyboardType.Text
) {
    OutlinedTextField(
        value = valor,
        onValueChange = onCambio,
        label = { Text(etiqueta, fontSize = 13.sp) },
        isError = error,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = teclado),
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = AndariColores.CampoPerfil,
            unfocusedContainerColor = AndariColores.CampoPerfil,
            focusedBorderColor = AndariColores.Cafe,
            unfocusedBorderColor = Color.Transparent,
            focusedLabelColor = AndariColores.Cafe,
            unfocusedLabelColor = AndariColores.TextoSecundario,
            focusedTextColor = AndariColores.TextoOscuro,
            unfocusedTextColor = AndariColores.TextoOscuro,
            cursorColor = AndariColores.Cafe
        )
    )
}

@Composable
private fun OpcionPago(metodo: MetodoPago, seleccionado: Boolean, onClick: () -> Unit) {
    val borde = if (seleccionado) AndariColores.Cafe else Color(0xFFE3E3E3)
    Row(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(if (seleccionado) Color(0xFFFFF6E3) else Color.White)
            .border(1.dp, borde, RoundedCornerShape(14.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 6.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = seleccionado, onClick = onClick,
            colors = RadioButtonDefaults.colors(selectedColor = AndariColores.Cafe)
        )
        Column(Modifier.weight(1f)) {
            Text(metodo.titulo, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = AndariColores.TextoOscuro)
            Text(metodo.descripcion, fontSize = 12.sp, color = AndariColores.GrisTexto)
        }
    }
}
