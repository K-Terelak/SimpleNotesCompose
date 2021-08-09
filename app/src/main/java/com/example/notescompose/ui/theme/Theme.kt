package com.example.notescompose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = black,
    primaryVariant = black,
    secondary = darkGray2,
    background = black,
    error = lightRed,
    onPrimary = lightGray2,
    onSecondary = darkGray,
    secondaryVariant = darkSecondaryVariant,
)

private val LightColorPalette = lightColors(
    primary = white,
    primaryVariant = black,
    secondary = white,
    background = lightGray,
    onPrimary = darkGray2,
    onSecondary = darkGray2,
    secondaryVariant = lightSecondaryVariant


)

@Composable
fun NotesComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}