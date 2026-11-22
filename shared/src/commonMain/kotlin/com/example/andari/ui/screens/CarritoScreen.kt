package com.example.andari.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import andari.shared.generated.resources.Res
import andari.shared.generated.resources.logo_andari
import andari.shared.generated.resources.logo_subtitulo
import com.example.andari.model.CartItem
import org.jetbrains.compose.resources.painterResource


private val GradientTop = Color(0xFFF5D9A8)
private val GradientBottom = Color(0xFFFFF8EE)
private val SoftYellow = Color(0xFFF8D9A0)
private val CardBackground = Color.White
private val TextDark = Color(0xFF2C2C2C)
private val TextHint = Color(0xFF9E9E9E)
private val SoftRed = Color(0xFFE57373)

@Composable
fun CarritoScreen(
    items: List<CartItem>,
    onBackClick: () -> Unit = {},
    onIncreaseQuantity: (CartItem) -> Unit = {},
    onDecreaseQuantity: (CartItem) -> Unit = {},
    onRemoveItem: (CartItem) -> Unit = {},
    onCheckoutClick: () -> Unit = {}
) {
    val total = items.sumOf { it.subtotal }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(GradientTop, GradientBottom)
                )
            )
    ) {
        Column(modifier = Modifier.fillMaxSize().statusBarsPadding()) {


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Atrás",
                        tint = TextDark
                    )
                }

                Spacer(Modifier.weight(1f))


                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(Res.drawable.logo_andari),
                        contentDescription = "Andarí",
                        modifier = Modifier
                            .width(160.dp)
                            .height(48.dp),
                        contentScale = ContentScale.Fit
                    )
                    Image(
                        painter = painterResource(Res.drawable.logo_subtitulo),
                        contentDescription = "Huaraches artesanales",
                        modifier = Modifier
                            .width(120.dp)
                            .height(10.dp),
                        contentScale = ContentScale.Fit
                    )
                }

                Spacer(Modifier.weight(1f))


                Spacer(Modifier.size(48.dp))
            }


            Text(
                text = "Mi Carrito",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = TextDark,
                modifier = Modifier
                    .padding(horizontal = 24.dp, vertical = 8.dp)
            )


            if (items.isEmpty()) {
                // Estado vacío
                EmptyCartContent(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )
            } else {

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
                    colors = CardDefaults.cardColors(containerColor = CardBackground),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        LazyColumn(
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 16.dp, vertical = 12.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(items, key = { it.id to it.talla }) { item ->
                                CartItemRow(
                                    item = item,
                                    onIncrease = { onIncreaseQuantity(item) },
                                    onDecrease = { onDecreaseQuantity(item) },
                                    onRemove = { onRemoveItem(item) }
                                )
                            }
                        }


                        HorizontalDivider(
                            color = Color(0xFFEEEEEE),
                            thickness = 1.dp
                        )

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "Total",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = TextDark
                                )
                                Text(
                                    text = "$${total.toInt()}",
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = TextDark
                                )
                            }

                            Spacer(Modifier.height(16.dp))

                            Button(
                                onClick = onCheckoutClick,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(52.dp),
                                shape = RoundedCornerShape(26.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = SoftYellow,
                                    contentColor = TextDark
                                ),
                                elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
                            ) {
                                Text(
                                    text = "FINALIZAR COMPRA",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    letterSpacing = 1.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun EmptyCartContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.7f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = null,
                tint = TextHint,
                modifier = Modifier.size(48.dp)
            )
        }

        Spacer(Modifier.height(24.dp))

        Text(
            text = "Tu carrito está vacío",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = TextDark,
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Agrega huaraches desde el catálogo\ny aparecerán aquí.",
            fontSize = 14.sp,
            color = TextHint,
            textAlign = TextAlign.Center,
            lineHeight = 20.sp
        )
    }
}

@Composable
private fun CartItemRow(
    item: CartItem,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit,
    onRemove: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF9F9F9)),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Foto del producto
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                if (item.imagen != null) {
                    Image(
                        painter = painterResource(item.imagen),
                        contentDescription = item.nombre,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(4.dp),
                        contentScale = ContentScale.Fit
                    )
                }
            }

            Spacer(Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.nombre,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextDark,
                    maxLines = 2
                )
                Spacer(Modifier.height(2.dp))
                Text(
                    text = "Talla ${item.talla}",
                    fontSize = 12.sp,
                    color = TextHint
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "$${item.precio.toInt()}",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextDark
                )
            }


            Column(horizontalAlignment = Alignment.End) {
                IconButton(
                    onClick = onRemove,
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Eliminar",
                        tint = SoftRed,
                        modifier = Modifier.size(18.dp)
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    // Disminuir
                    Box(
                        modifier = Modifier
                            .size(28.dp)
                            .clip(CircleShape)
                            .background(SoftYellow)
                            .clickable(onClick = onDecrease),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Remove,
                            contentDescription = "Menos",
                            tint = TextDark,
                            modifier = Modifier.size(16.dp)
                        )
                    }

                    Text(
                        text = "${item.cantidad}",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextDark,
                        modifier = Modifier.widthIn(min = 24.dp),
                        textAlign = TextAlign.Center
                    )

                    // Aumentar
                    Box(
                        modifier = Modifier
                            .size(28.dp)
                            .clip(CircleShape)
                            .background(SoftYellow)
                            .clickable(onClick = onIncrease),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Más",
                            tint = TextDark,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }
    }
}