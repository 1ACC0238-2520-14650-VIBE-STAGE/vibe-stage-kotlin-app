package com.example.vibestage.presentation.promoter.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vibestage.ui.theme.*

/**
 * Pantalla principal para promotores - Versión simplificada
 */
@Composable
fun PromoterHomeScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = VibeStageBlack),
        contentPadding = PaddingValues(
            start = 16.dp,
            end = 16.dp,
            top = 80.dp,
            bottom = 100.dp
        ),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        item {
            // Header del promotor
            PromoterHomeHeader()
        }

        item {
            // Próximo evento destacado con imagen
            FeaturedEventCard()
        }

        item {
            // Eventos populares
            PopularEventsSection()
        }

        item {
            // Postulaciones pendientes
            PendingApplicationsCard()
        }

        item {
            // Resumen visual simple
            SimpleOverviewCard()
        }
    }
}

/**
 * Header principal del promotor
 */
@Composable
private fun PromoterHomeHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = "¡Hola, Promotor!",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = VibeStageWhite
            )
            Text(
                text = "Gestiona tu local y eventos",
                fontSize = 16.sp,
                color = VibeStageGrayLight,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        // Avatar del promotor
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(VibeStageGolden.copy(alpha = 0.2f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Business,
                contentDescription = "Perfil",
                tint = VibeStageGolden,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

/**
 * Card del evento destacado con imagen
 */
@Composable
private fun FeaturedEventCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = VibeStageGrayDark)
    ) {
        Column {
            // Imagen del evento desde drawable
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            ) {
                // Intentar cargar la imagen, si falla mostrar ícono
                val imageResource = try {
                    com.example.vibestage.R.drawable.nocherock
                } catch (_: Exception) {
                    null
                }

                if (imageResource != null) {
                    Image(
                        painter = painterResource(id = imageResource),
                        contentDescription = "Noche de Rock",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    // Fallback si no se puede cargar la imagen
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(VibeStageGolden.copy(alpha = 0.1f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.MusicNote,
                            contentDescription = "Evento",
                            tint = VibeStageGolden,
                            modifier = Modifier.size(40.dp)
                        )
                    }
                }
            }

            // Información del evento
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    text = "Próximo Evento",
                    color = VibeStageGrayLight,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )

                Text(
                    text = "Noche de Rock",
                    color = VibeStageWhite,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 4.dp)
                )

                Text(
                    text = "Los Rockeros • 15 Oct 2025",
                    color = VibeStageGolden,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.People,
                            contentDescription = "Asistentes",
                            tint = VibeStageGrayLight,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = "120 asistentes",
                            color = VibeStageGrayLight,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(start = 4.dp)
                        )
                    }

                    Text(
                        text = "22:00 hrs",
                        color = VibeStageWhite,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

/**
 * Sección de eventos populares
 */
@Composable
private fun PopularEventsSection() {
    Column {
        Text(
            text = "Eventos Populares",
            color = VibeStageWhite,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            PopularEventItem(
                title = "Jazz Night",
                date = "16 Oct",
                modifier = Modifier.weight(1f)
            )
            PopularEventItem(
                title = "DJ Session",
                date = "18 Oct",
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun PopularEventItem(
    title: String,
    date: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = VibeStageGrayDark)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.Event,
                contentDescription = title,
                tint = VibeStageGolden,
                modifier = Modifier.size(32.dp)
            )

            Text(
                text = title,
                color = VibeStageWhite,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(top = 8.dp)
            )

            Text(
                text = date,
                color = VibeStageGrayLight,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

/**
 * Card de postulaciones pendientes
 */
@Composable
private fun PendingApplicationsCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = VibeStageGrayDark)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(VibeStageGolden.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.People,
                    contentDescription = "Postulaciones",
                    tint = VibeStageGolden,
                    modifier = Modifier.size(24.dp)
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Nuevas Postulaciones",
                    color = VibeStageWhite,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "5 artistas esperando respuesta",
                    color = VibeStageGrayLight,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }

            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "Ver",
                tint = VibeStageGolden,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

/**
 * Resumen visual simple
 */
@Composable
private fun SimpleOverviewCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = VibeStageGrayDark)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = "Resumen Rápido",
                color = VibeStageWhite,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                SimpleStatItem(
                    icon = Icons.Default.Event,
                    value = "8",
                    label = "Eventos activos"
                )
                SimpleStatItem(
                    icon = Icons.Default.People,
                    value = "5",
                    label = "Postulaciones"
                )
                SimpleStatItem(
                    icon = Icons.Default.Star,
                    value = "4.8",
                    label = "Rating"
                )
            }
        }
    }
}

@Composable
private fun SimpleStatItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    value: String,
    label: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = VibeStageGolden,
            modifier = Modifier.size(24.dp)
        )

        Text(
            text = value,
            color = VibeStageWhite,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 8.dp)
        )

        Text(
            text = label,
            color = VibeStageGrayLight,
            fontSize = 12.sp,
            modifier = Modifier.padding(top = 2.dp)
        )
    }
}
