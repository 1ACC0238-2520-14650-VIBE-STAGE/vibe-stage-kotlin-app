package com.example.vibestage.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// Esquema de colores oscuro para VibeStage
private val VibeStageColorSchemeDark = darkColorScheme(
    primary = VibeStageGolden,
    onPrimary = VibeStageBlack,
    primaryContainer = VibeStageGoldenDark,
    onPrimaryContainer = VibeStageWhite,

    secondary = VibeStageGrayMedium,
    onSecondary = VibeStageWhite,
    secondaryContainer = VibeStageGrayDark,
    onSecondaryContainer = VibeStageWhite,

    tertiary = VibeStageGoldenLight,
    onTertiary = VibeStageBlack,

    background = VibeStageBlack,
    onBackground = VibeStageWhite,

    surface = VibeStageGrayDark,
    onSurface = VibeStageWhite,
    surfaceVariant = VibeStageGrayMedium,
    onSurfaceVariant = VibeStageWhite,

    error = VibeStageError,
    onError = VibeStageWhite,

    outline = VibeStageGrayLight,
    outlineVariant = VibeStageGrayMedium
)

// Esquema de colores claro para VibeStage
private val VibeStageColorSchemeLight = lightColorScheme(
    primary = VibeStageGolden,
    onPrimary = VibeStageBlack,
    primaryContainer = VibeStageGoldenLight,
    onPrimaryContainer = VibeStageBlack,

    secondary = VibeStageGrayMedium,
    onSecondary = VibeStageWhite,
    secondaryContainer = VibeStageGrayLight,
    onSecondaryContainer = VibeStageWhite,

    tertiary = VibeStageGoldenDark,
    onTertiary = VibeStageWhite,

    background = VibeStageWhite,
    onBackground = VibeStageBlack,

    surface = VibeStageWhite,
    onSurface = VibeStageBlack,
    surfaceVariant = Color(0xFFF5F5F5),
    onSurfaceVariant = VibeStageGrayDark,

    error = VibeStageError,
    onError = VibeStageWhite,

    outline = VibeStageGrayMedium,
    outlineVariant = VibeStageGrayLight
)

// Mantener compatibilidad con esquemas anteriores
private val DarkColorScheme = VibeStageColorSchemeDark
private val LightColorScheme = VibeStageColorSchemeLight

@Composable
fun VibeStageTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color deshabilitado para mantener identidad visual de VibeStage
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> VibeStageColorSchemeDark
        else -> VibeStageColorSchemeLight
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = VibeStageTypography,
        content = content
    )
}