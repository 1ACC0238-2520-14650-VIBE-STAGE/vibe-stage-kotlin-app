package com.example.vibestage.presentation.onboarding

import androidx.compose.animation.core.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vibestage.presentation.common.components.VibeStageButton
import com.example.vibestage.ui.theme.*
import kotlinx.coroutines.launch
import androidx.compose.ui.graphics.graphicsLayer

/**
 * Datos para las páginas del onboarding
 */
data class OnboardingPage(
    val title: String,
    val description: String,
    val icon: ImageVector,
    val backgroundColor: Color
)

/**
 * Pantalla de onboarding de VibeStage
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    onNavigateToLogin: () -> Unit
) {
    val pages = listOf(
        OnboardingPage(
            title = "Bienvenido a VibeStage",
            description = "La plataforma que conecta músicos emergentes con bares, discotecas, centros culturales y promotores de eventos.",
            icon = Icons.Default.MusicNote,
            backgroundColor = VibeStageGolden
        ),
        OnboardingPage(
            title = "Para Artistas",
            description = "Encuentra oportunidades perfectas para tu propuesta artística. Postula fácilmente, revisa restricciones y agenda presentaciones sin intermediarios.",
            icon = Icons.Default.Person,
            backgroundColor = VibeStageInfo
        ),
        OnboardingPage(
            title = "Para Promotores",
            description = "Publica tu disponibilidad, encuentra talentos que se ajusten a tu local y gestiona todo el proceso de contratación de forma profesional.",
            icon = Icons.Default.Business,
            backgroundColor = VibeStageSuccess
        ),
        OnboardingPage(
            title = "Gestión Completa",
            description = "Contratos digitales, pagos seguros con escrow, agenda colaborativa y promoción automática para cada evento.",
            icon = Icons.Default.Assignment,
            backgroundColor = VibeStageWarning
        )
    )

    val pagerState = rememberPagerState(pageCount = { pages.size })
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(VibeStageBlack)
    ) {
        // Pager con las páginas del onboarding
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            OnboardingPageContent(
                page = pages[page],
                modifier = Modifier.fillMaxSize()
            )
        }

        // Indicadores de página y botones de navegación
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Indicadores de página
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(bottom = 32.dp)
            ) {
                repeat(pages.size) { index ->
                    val isSelected = pagerState.currentPage == index
                    Box(
                        modifier = Modifier
                            .size(if (isSelected) 12.dp else 8.dp)
                            .clip(CircleShape)
                            .background(
                                if (isSelected) VibeStageGolden else VibeStageGrayMedium
                            )
                    )
                }
            }

            // Botones de navegación
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = if (pagerState.currentPage == pages.size - 1) {
                    Arrangement.Center
                } else {
                    Arrangement.SpaceBetween
                }
            ) {
                if (pagerState.currentPage < pages.size - 1) {
                    // Botón "Omitir"
                    VibeStageButton(
                        text = "Omitir",
                        onClick = onNavigateToLogin,
                        isSecondary = true,
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    // Botón "Siguiente"
                    VibeStageButton(
                        text = "Siguiente",
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        },
                        modifier = Modifier.weight(1f)
                    )
                } else {
                    // Botón "Comenzar"
                    VibeStageButton(
                        text = "Comenzar",
                        onClick = onNavigateToLogin,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

/**
 * Contenido de cada página del onboarding
 */
@Composable
private fun OnboardingPageContent(
    page: OnboardingPage,
    modifier: Modifier = Modifier
) {
    var isVisible by remember { mutableStateOf(false) }

    val animationDelay = 300
    val iconScale by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0.8f,
        animationSpec = tween(600, animationDelay),
        label = "icon_scale"
    )

    val contentAlpha by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = tween(800, animationDelay + 200),
        label = "content_alpha"
    )

    LaunchedEffect(Unit) {
        isVisible = true
    }

    Column(
        modifier = modifier.padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Icono animado
        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(page.backgroundColor.copy(alpha = 0.2f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = page.icon,
                contentDescription = null,
                tint = page.backgroundColor,
                modifier = Modifier
                    .size(60.dp)
                    .graphicsLayer {
                        scaleX = iconScale
                        scaleY = iconScale
                    }
            )
        }

        Spacer(modifier = Modifier.height(48.dp))

        // Título
        Text(
            text = page.title,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = RobotoFontFamily,
            color = VibeStageWhite,
            textAlign = TextAlign.Center,
            modifier = Modifier.graphicsLayer { alpha = contentAlpha }
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Descripción
        Text(
            text = page.description,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = MontserratFontFamily,
            color = VibeStageGrayLight,
            textAlign = TextAlign.Center,
            lineHeight = 24.sp,
            modifier = Modifier.graphicsLayer { alpha = contentAlpha }
        )
    }
}
