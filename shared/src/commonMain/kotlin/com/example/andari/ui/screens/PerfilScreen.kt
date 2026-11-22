package com.example.andari.ui.screens



import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


private val GradientTop = Color(0xFFF5D9A8)
private val GradientBottom = Color(0xFFFFF8EE)
private val SoftYellow = Color(0xFFF8D9A0)
private val CardBackground = Color.White
private val TextDark = Color(0xFF2C2C2C)
private val TextHint = Color(0xFF9E9E9E)


data class CompraRealizada(
    val id: Int,
    val nombre: String,
    val precio: String,
    val imagenPlaceholder: String = "",
    val favorito: Boolean = false
)

@Composable
fun PerfilScreen(
    nombreUsuario: String = "NombreUser",
    fotoPerfilUrl: String? = null,
    comprasRealizadas: List<CompraRealizada> = emptyList(),
    onBackClick: () -> Unit = {},
    onLogoClick: () -> Unit = {},
    onProductClick: (CompraRealizada) -> Unit = {},
    onFavoriteToggle: (CompraRealizada) -> Unit = {}
) {

    val compras = remember(comprasRealizadas) {
        if (comprasRealizadas.isNotEmpty()) comprasRealizadas
        else listOf(
            CompraRealizada(1, "Huarache Trenzado", "$520"),
            CompraRealizada(2, "Huarache tipo Sandalia (Vogue)", "$590")
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(GradientTop, GradientBottom)
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(horizontal = 20.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Regresar",
                        tint = TextDark,
                        modifier = Modifier.size(24.dp)
                    )
                }


                Text(
                    text = "Perfil",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextDark
                )


                IconButton(
                    onClick = onLogoClick,
                    modifier = Modifier.size(40.dp)
                ) {

                    Text(
                        text = "",
                        fontSize = 22.sp
                    )

                }
            }

            Spacer(Modifier.height(24.dp))


            Box(
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.CenterHorizontally)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.85f)),
                contentAlignment = Alignment.Center
            ) {
                if (fotoPerfilUrl != null) {

                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Foto de perfil",
                        tint = TextDark.copy(alpha = 0.5f),
                        modifier = Modifier.size(64.dp)
                    )
                } else {

                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Foto de perfil",
                        tint = TextDark.copy(alpha = 0.45f),
                        modifier = Modifier.size(64.dp)
                    )
                }
            }

            Spacer(Modifier.height(16.dp))


            Text(
                text = nombreUsuario,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextDark,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(28.dp))


            Text(
                text = "Compras Realizadas:",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = TextDark,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp, bottom = 12.dp)
            )

            if (compras.isEmpty()) {
               
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Aún no has realizado ninguna compra",
                        fontSize = 14.sp,
                        color = TextHint,
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(bottom = 16.dp)
                ) {
                    items(compras, key = { it.id }) { compra ->
                        CompraCard(
                            compra = compra,
                            onClick = { onProductClick(compra) },
                            onFavoriteClick = { onFavoriteToggle(compra) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun CompraCard(
    compra: CompraRealizada,
    onClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {
    var isFavorite by remember { mutableStateOf(compra.favorito) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(16.dp))
                .background(CardBackground)
        ) {

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "", fontSize = 40.sp)
            }

           
            IconButton(
                onClick = {
                    isFavorite = !isFavorite
                    onFavoriteClick()
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(4.dp)
                    .size(32.dp)
            ) {
                Icon(
                    imageVector = if (isFavorite) Icons.Default.Favorite
                    else Icons.Default.FavoriteBorder,
                    contentDescription = if (isFavorite) "Quitar de favoritos" else "Agregar a favoritos",
                    tint = if (isFavorite) Color(0xFFE53935) else TextDark,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        Spacer(Modifier.height(8.dp))

        Text(
            text = compra.nombre,
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            color = TextDark,
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(4.dp))

        Text(
            text = compra.precio,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = TextDark
        )
    }
}