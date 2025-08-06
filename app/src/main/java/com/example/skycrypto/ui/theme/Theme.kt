package com.example.skycrypto.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/* 1️⃣  Dark-theme colors matching screenshots */
private val DarkColorScheme = darkColorScheme(
    primary       = Color(0xFF0A84FF),
    onPrimary     = Color.White,
    secondary     = Color(0xFF2B2B2B),
    onSecondary   = Color.White,
    background    = Color(0xFF08080A),
    onBackground  = Color.White,
    surface       = Color(0xFF151517),
    onSurface     = Color.White
)

/* 2️⃣  Always-dark theme */
@Composable
fun SkyCryptoTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme  = DarkColorScheme,
        typography   = Typography,
        content      = content
    )
}