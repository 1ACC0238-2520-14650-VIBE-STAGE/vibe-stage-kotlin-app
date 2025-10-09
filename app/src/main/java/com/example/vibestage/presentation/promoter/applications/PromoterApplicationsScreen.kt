package com.example.vibestage.presentation.promoter.applications

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.vibestage.ui.theme.*

/**
 * Datos para postulaciones de artistas
 */
data class ArtistApplication(
    val id: String,
    val artistName: String,
    val musicalGenre: String,
    val eventDate: String,
    val rating: Float,
    val priceRequested: String,
    val experience: String,
    val artistImage: String = "",
    val description: String = "",
    val socialMedia: String = ""
)

/**
 * Pantalla de postulaciones para promotores
 */
@Composable
fun PromoterApplicationsScreen() {
    var filterStatus by remember { mutableStateOf("Pendientes") }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = VibeStageBlack)
            .padding(top = 80.dp), // Aumentado el padding superior
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            // Header
            ApplicationsHeader()
        }

        item {
            // Filtros
            ApplicationFilters(
                selectedFilter = filterStatus,
                onFilterSelected = { filterStatus = it }
            )
        }

        item {
            // Resumen de postulaciones
            ApplicationsSummary()
        }

        items(getApplications()) { application ->
            ArtistApplicationCard(
                application = application,
                onAccept = { /* Aceptar */ },
                onReject = { /* Rechazar */ },
                onViewProfile = { /* Ver perfil */ },
                modifier = Modifier.padding(horizontal = 20.dp)
            )
        }

        item {
            Spacer(modifier = Modifier.height(100.dp)) // Espacio para el navbar flotante
        }
    }
}

/**
 * Header de postulaciones
 */
@Composable
private fun ApplicationsHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Postulaciones",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = RobotoFontFamily,
            color = VibeStageWhite
        )

        Box(
            modifier = Modifier
                .background(
                    VibeStageGrayDark.copy(alpha = 0.6f),
                    RoundedCornerShape(20.dp)
                )
                .padding(10.dp)
        ) {
            Icon(
                imageVector = Icons.Default.FilterList,
                contentDescription = "Filtros",
                tint = VibeStageGolden,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

/**
 * Filtros de postulaciones
 */
@Composable
private fun ApplicationFilters(
    selectedFilter: String,
    onFilterSelected: (String) -> Unit
) {
    val filters = listOf("Pendientes", "Aceptadas", "Rechazadas", "Todas")

    LazyRow(
        modifier = Modifier.padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(filters) { filter ->
            FilterChip(
                text = filter,
                isSelected = selectedFilter == filter,
                onClick = { onFilterSelected(filter) }
            )
        }
    }
}

/**
 * Chip de filtro
 */
@Composable
private fun FilterChip(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .background(
                if (isSelected) VibeStageGolden.copy(alpha = 0.2f) else VibeStageGrayDark.copy(alpha = 0.4f),
                RoundedCornerShape(20.dp)
            )
            .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium,
            fontFamily = MontserratFontFamily,
            color = if (isSelected) VibeStageGolden else VibeStageGrayLight
        )
    }
}

/**
 * Resumen de postulaciones
 */
@Composable
private fun ApplicationsSummary() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        SummaryCard(
            title = "Pendientes",
            value = "5",
            color = VibeStageWarning,
            modifier = Modifier.weight(1f)
        )

        SummaryCard(
            title = "Esta Semana",
            value = "12",
            color = VibeStageInfo,
            modifier = Modifier.weight(1f)
        )

        SummaryCard(
            title = "Aceptadas",
            value = "8",
            color = VibeStageSuccess,
            modifier = Modifier.weight(1f)
        )
    }
}

/**
 * Card de resumen
 */
@Composable
private fun SummaryCard(
    title: String,
    value: String,
    color: androidx.compose.ui.graphics.Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(80.dp)
            .background(
                VibeStageGrayDark.copy(alpha = 0.4f),
                RoundedCornerShape(16.dp)
            )
            .padding(12.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = value,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = RobotoFontFamily,
                color = color
            )
            Text(
                text = title,
                fontSize = 11.sp,
                fontFamily = MontserratFontFamily,
                color = VibeStageGrayLight,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

/**
 * Card de postulación de artista
 */
@Composable
private fun ArtistApplicationCard(
    application: ArtistApplication,
    onAccept: () -> Unit,
    onReject: () -> Unit,
    onViewProfile: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                VibeStageGrayDark.copy(alpha = 0.4f),
                RoundedCornerShape(20.dp)
            )
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Información del artista
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Avatar del artista
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(
                            VibeStageGolden.copy(alpha = 0.2f),
                            RoundedCornerShape(30.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        tint = VibeStageGolden,
                        modifier = Modifier.size(30.dp)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text(
                        text = application.artistName,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = RobotoFontFamily,
                        color = VibeStageWhite
                    )

                    Text(
                        text = application.musicalGenre,
                        fontSize = 14.sp,
                        fontFamily = MontserratFontFamily,
                        color = VibeStageGolden,
                        modifier = Modifier.padding(top = 2.dp)
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(top = 4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = VibeStageGolden,
                            modifier = Modifier.size(14.dp)
                        )
                        Text(
                            text = application.rating.toString(),
                            fontSize = 12.sp,
                            fontFamily = MontserratFontFamily,
                            color = VibeStageGrayLight,
                            modifier = Modifier.padding(start = 4.dp)
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        Text(
                            text = application.priceRequested,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = RobotoFontFamily,
                            color = VibeStageGolden
                        )
                    }
                }
            }

            // Acciones
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Botón rechazar
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .background(
                                VibeStageError.copy(alpha = 0.2f),
                                RoundedCornerShape(18.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Rechazar",
                            tint = VibeStageError,
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    // Botón aceptar
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .background(
                                VibeStageSuccess.copy(alpha = 0.2f),
                                RoundedCornerShape(18.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Aceptar",
                            tint = VibeStageSuccess,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }

                // Botón ver perfil
                Box(
                    modifier = Modifier
                        .background(
                            VibeStageGolden,
                            RoundedCornerShape(8.dp)
                        )
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = "Ver Perfil",
                        fontSize = 12.sp,
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
 * Función para obtener las postulaciones (datos de ejemplo)
 */
private fun getApplications(): List<ArtistApplication> {
    return listOf(
        ArtistApplication(
            id = "1",
            artistName = "Los Rockeros",
            musicalGenre = "Rock",
            eventDate = "15 Oct 2025",
            rating = 4.8f,
            priceRequested = "S/ 800",
            experience = "3 años"
        ),
        ArtistApplication(
            id = "2",
            artistName = "Jazz Collective",
            musicalGenre = "Jazz",
            eventDate = "20 Oct 2025",
            rating = 4.9f,
            priceRequested = "S/ 900",
            experience = "5 años"
        ),
        ArtistApplication(
            id = "3",
            artistName = "Indie Stars",
            musicalGenre = "Indie",
            eventDate = "25 Oct 2025",
            rating = 4.6f,
            priceRequested = "S/ 650",
            experience = "2 años"
        )
    )
}
