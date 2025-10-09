package com.example.vibestage.presentation.artist.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vibestage.ui.theme.*

/**
 * Pantalla de inicio para artistas
 */
@Composable
fun ArtistHomeScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = VibeStageBlack)
            .padding(top = 80.dp), // Aumentado el padding superior
        verticalArrangement = Arrangement.spacedBy(32.dp) // Más espacio entre elementos
    ) {
        item {
            // Header principal
            ArtistHomeHeader()
        }

        item {
            // Próximo evento destacado
            FeaturedUpcomingEvent()
        }

        item {
            // Resumen de oportunidades
            OpportunitiesSummary()
        }

        item {
            // Nueva sección: Géneros musicales favoritos
            FavoriteGenresSection()
        }

        item {
            // Nueva sección: Inspiración del día
            DailyInspirationSection()
        }

        item {
            Spacer(modifier = Modifier.height(100.dp)) // Espacio para el navbar flotante
        }
    }
}

/**
 * Header principal del home
 */
@Composable
private fun ArtistHomeHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "¡Hola, Artista!",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = RobotoFontFamily,
                    color = VibeStageWhite
                )
                Text(
                    text = "Encuentra tu próximo show",
                    fontSize = 16.sp,
                    fontFamily = MontserratFontFamily,
                    color = VibeStageGrayLight,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            // Avatar y notificaciones
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Notificaciones
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(
                            VibeStageGrayDark.copy(alpha = 0.6f),
                            RoundedCornerShape(24.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "Notificaciones",
                        tint = VibeStageGolden,
                        modifier = Modifier.size(20.dp)
                    )
                }

                // Avatar
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(
                            VibeStageGolden.copy(alpha = 0.2f),
                            RoundedCornerShape(24.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        tint = VibeStageGolden,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}

/**
 * Evento próximo destacado
 */
@Composable
private fun FeaturedUpcomingEvent() {
    Column(
        modifier = Modifier.padding(horizontal = 20.dp)
    ) {
        Text(
            text = "Próximo Show",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = RobotoFontFamily,
            color = VibeStageWhite,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = VibeStageGrayDark.copy(alpha = 0.4f),
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Jazz Club Lima",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = RobotoFontFamily,
                        color = VibeStageWhite
                    )
                    Text(
                        text = "15 Oct 2025 • 21:00",
                        fontSize = 14.sp,
                        fontFamily = MontserratFontFamily,
                        color = VibeStageGrayLight,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                    Text(
                        text = "S/ 800",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = RobotoFontFamily,
                        color = VibeStageGolden,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                Icon(
                    imageVector = Icons.Default.MusicNote,
                    contentDescription = null,
                    tint = VibeStageGolden,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
}

/**
 * Resumen de oportunidades
 */
@Composable
private fun OpportunitiesSummary() {
    Column(
        modifier = Modifier.padding(horizontal = 20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Nuevas Oportunidades",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = RobotoFontFamily,
                color = VibeStageWhite
            )
            Text(
                text = "Ver todas",
                fontSize = 14.sp,
                fontFamily = MontserratFontFamily,
                color = VibeStageGolden
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Oportunidad destacada
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    VibeStageGrayDark.copy(alpha = 0.4f),
                    RoundedCornerShape(16.dp)
                )
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "URGENTE",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = MontserratFontFamily,
                            color = VibeStageError,
                            modifier = Modifier
                                .background(
                                    VibeStageError.copy(alpha = 0.2f),
                                    RoundedCornerShape(4.dp)
                                )
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }

                    Text(
                        text = "La Noche Bar",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = RobotoFontFamily,
                        color = VibeStageWhite,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Text(
                        text = "Rock • Miraflores",
                        fontSize = 14.sp,
                        fontFamily = MontserratFontFamily,
                        color = VibeStageGrayLight,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                    Text(
                        text = "S/ 600",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = RobotoFontFamily,
                        color = VibeStageGolden,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .background(
                            VibeStageGolden,
                            RoundedCornerShape(8.dp)
                        )
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = "Ver",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = MontserratFontFamily,
                        color = VibeStageBlack
                    )
                }
            }
        }
    }
}

/**
 * Nueva sección: Géneros musicales favoritos
 */
@Composable
private fun FavoriteGenresSection() {
    Column(
        modifier = Modifier.padding(horizontal = 20.dp)
    ) {
        Text(
            text = "Géneros Musicales Favoritos",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = RobotoFontFamily,
            color = VibeStageWhite,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Lista horizontal de géneros
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 4.dp)
        ) {
            item {
                GenreChip(genre = "Rock")
            }
            item {
                GenreChip(genre = "Jazz")
            }
            item {
                GenreChip(genre = "Pop")
            }
            item {
                GenreChip(genre = "Clásica")
            }
            item {
                GenreChip(genre = "Electrónica")
            }
        }
    }
}

/**
 * Chip de género musical
 */
@Composable
private fun GenreChip(genre: String) {
    Box(
        modifier = Modifier
            .background(
                VibeStageGrayDark.copy(alpha = 0.4f),
                RoundedCornerShape(16.dp)
            )
            .padding(vertical = 8.dp, horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = genre,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = MontserratFontFamily,
            color = VibeStageGolden
        )
    }
}

/**
 * Nueva sección: Inspiración del día
 */
@Composable
private fun DailyInspirationSection() {
    Column(
        modifier = Modifier.padding(horizontal = 20.dp)
    ) {
        Text(
            text = "Inspiración del Día",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = RobotoFontFamily,
            color = VibeStageWhite,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Tarjeta de inspiración
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    VibeStageGrayDark.copy(alpha = 0.4f),
                    RoundedCornerShape(16.dp)
                )
                .padding(16.dp)
        ) {
            Text(
                text = "La música es el lenguaje del alma. Deja que te guíe.",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = MontserratFontFamily,
                color = VibeStageWhite
            )
        }
    }
}
