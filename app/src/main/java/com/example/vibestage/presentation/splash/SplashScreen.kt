package com.example.vibestage.presentation.splash

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vibestage.R
import com.example.vibestage.ui.theme.*
import kotlinx.coroutines.delay

/**
 * Pantalla de splash de VibeStage con animaciones
 */
@Composable
fun SplashScreen(
    onNavigateToOnboarding: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    var startAnimation by remember { mutableStateOf(false) }

    // Animaciones
    val alphaAnimation by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 1000,
            easing = FastOutSlowInEasing
        ),
        label = "alpha_animation"
    )

    val scaleAnimation by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0.8f,
        animationSpec = tween(
            durationMillis = 1000,
            easing = FastOutSlowInEasing
        ),
        label = "scale_animation"
    )

    val textAlphaAnimation by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 1200,
            delayMillis = 500,
            easing = FastOutSlowInEasing
        ),
        label = "text_alpha_animation"
    )

    LaunchedEffect(Unit) {
        startAnimation = true
        delay(2500) // Duración del splash

        // Aquí verificarías si es primera vez o si hay sesión activa
        // Por ahora navegamos al onboarding
        onNavigateToOnboarding()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        VibeStageBlack,
                        VibeStageGrayDark,
                        VibeStageBlack
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Logo de VibeStage (por ahora usamos texto estilizado)
            Text(
                text = "VibeStage",
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = RobotoFontFamily,
                color = VibeStageGolden,
                modifier = Modifier
                    .alpha(alphaAnimation)
                    .scale(scaleAnimation)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Conectando música y espacios",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = MontserratFontFamily,
                color = VibeStageWhite,
                modifier = Modifier.alpha(textAlphaAnimation)
            )
        }

        // Indicador de carga en la parte inferior
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 60.dp)
                .alpha(textAlphaAnimation),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Cargando...",
                fontSize = 14.sp,
                fontFamily = MontserratFontFamily,
                color = VibeStageGrayLight
            )
        }
    }
}
