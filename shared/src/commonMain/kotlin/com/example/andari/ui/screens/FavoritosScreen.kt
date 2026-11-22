package com.example.andari.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import andari.shared.generated.resources.Res
import andari.shared.generated.resources.ic_carrito_menu
import andari.shared.generated.resources.inicio_huaraches
import andari.shared.generated.resources.logo_andari
import com.example.andari.model.Favorito
import org.jetbrains.compose.resources.painterResource

private val FondoArriba = Color(0xFFFCE3B5)
private val FondoCentro = Color(0xFFF9F9FB)
private val FondoAbajo = Color(0xFFFFCF6E)
private val ColorTarjeta = Color(0xFFF3F3F3)
private val SoftRed = Color(0xFFE57373)

@Composable
fun FavoritosScreen(
    favoritos: List<Favorito> = emptyList(),
    onBackClick: () -> Unit = {},
    onCarritoClick: () -> Unit = {},
    onFavoritoClick: (Favorito) -> Unit = {},
    onRemoveFavorito: (Favorito) -> Unit = {},
    onPerfilClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    0.0f to FondoArriba,
                    0.25f to FondoCentro,
                    0.60f to FondoCentro,
                    1.0f to FondoAbajo
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .safeContentPadding()
                .padding(horizontal = 16.dp)
        ) {
            // Barra superior: regresar y logo
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Regresar",
                        tint = Color.Black,
                        modifier = Modifier.size(28.dp)
                    )
                }
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(Res.drawable.logo_andari),
                        contentDescription = "Logo Andari",
                        modifier = Modifier
                            .fillMaxWidth(0.6f)
                            .height(48.dp),
                        contentScale = ContentScale.Fit
                    )
                    Image(
                        painter = painterResource(Res.drawable.inicio_huaraches),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth(0.45f)
                            .height(10.dp),
                        contentScale = ContentScale.Fit
                    )
                }

                Spacer(modifier = Modifier.size(48.dp))
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Favoritos",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 16.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))


            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(ColorTarjeta)
            ) {
                if (favoritos.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .padding(24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Aún no tienes favoritos.\nToca el corazón de un huarache para guardarlo aquí.",
                            fontSize = 13.sp,
                            color = Color.Gray,
                            textAlign = TextAlign.Center,
                            lineHeight = 18.sp
                        )
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 12.dp, vertical = 12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(favoritos, key = { it.id }) { favorito ->
                            FavoritoItem(
                                favorito = favorito,
                                onClick = { onFavoritoClick(favorito) },
                                onRemove = { onRemoveFavorito(favorito) }
                            )
                        }
                    }
                }


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = onCarritoClick) {
                        Image(
                            painter = painterResource(Res.drawable.ic_carrito_menu),
                            contentDescription = "Carrito",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    IconButton(onClick = onPerfilClick) {
                        Icon(
                            imageVector = Icons.Outlined.PersonOutline,
                            contentDescription = "Perfil",
                            tint = Color.Black,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun FavoritoItem(
    favorito: Favorito,
    onClick: () -> Unit,
    onRemove: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .clickable { onClick() }
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Foto del producto
        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xFFF3F3F3)),
            contentAlignment = Alignment.Center
        ) {
            if (favorito.imagen != null) {
                Image(
                    painter = painterResource(favorito.imagen),
                    contentDescription = favorito.nombre,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp),
                    contentScale = ContentScale.Fit
                )
            }
        }

        Spacer(modifier = Modifier.width(12.dp))

        // Nombre, talla y precio
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = favorito.nombre,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 13.sp,
                color = Color.Black,
                maxLines = 2
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = favorito.talla,
                fontSize = 11.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "$${favorito.precio}",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = Color.Black
            )
        }

        // Quitar de favoritos
        IconButton(
            onClick = onRemove,
            modifier = Modifier.size(36.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.Delete,
                contentDescription = "Quitar de favoritos",
                tint = SoftRed,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Preview
@Composable
fun FavoritosScreenPreview() {
    FavoritosScreen(
        favoritos = listOf(
            Favorito(id = "forro", nombre = "Dama Forro de Colores Trenza", talla = "Talla 24", precio = 520),
            Favorito(id = "nino_suela_negra", nombre = "Niño Forro Café con Suela Negra", talla = "Talla 12", precio = 420)
        )
    )
}