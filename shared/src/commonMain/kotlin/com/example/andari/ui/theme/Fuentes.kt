package com.example.andari.ui.theme

import andari.shared.generated.resources.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.resources.Font


object Fuentes {

    @Composable
    fun raleway() = FontFamily(
        Font(Res.font.raleway_semibold, FontWeight.SemiBold),
        Font(Res.font.raleway_bold, FontWeight.Bold),
        Font(Res.font.raleway_extrabold, FontWeight.ExtraBold)
    )

    @Composable
    fun inter() = FontFamily(
        Font(Res.font.inter_regular, FontWeight.Normal),
        Font(Res.font.inter_bold, FontWeight.Bold),
        Font(Res.font.inter_extrabold_italic, FontWeight.ExtraBold, FontStyle.Italic)
    )

    @Composable
    fun nunito() = FontFamily(
        Font(Res.font.nunito_bold, FontWeight.Bold),
        Font(Res.font.nunito_black, FontWeight.Black)
    )

    @Composable
    fun poppins() = FontFamily(
        Font(Res.font.poppins_regular, FontWeight.Normal),
        Font(Res.font.poppins_medium, FontWeight.Medium)
    )


    @Composable
    fun montserratSubrayada() = FontFamily(
        Font(Res.font.montserrat_subrayada_bold, FontWeight.Bold)
    )

    @Composable
    fun fontAwesome() = FontFamily(
        Font(Res.font.fa_solid_900, FontWeight.Normal)
    )
}
