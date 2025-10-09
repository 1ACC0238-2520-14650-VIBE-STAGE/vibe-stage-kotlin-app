package com.example.vibestage.presentation.artist.opportunities

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vibestage.ui.theme.*

/**
 * Datos para oportunidades
 */
data class EventOpportunity(
    val id: String,
    val venueName: String,
    val eventDate: String,
    val eventType: String,
    val location: String,
    val payment: String,
    val genresWanted: List<String>,
    val isUrgent: Boolean = false,
    val venueImage: String = ""
)

/**
 * Pantalla de búsqueda de oportunidades para artistas
 */
@Composable
fun ArtistOpportunitiesScreen() {
    var selectedFilter by remember { mutableStateOf("Todos") }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = VibeStageBlack)
            .padding(top = 80.dp), // Aumentado el padding superior
        contentPadding = PaddingValues(bottom = 100.dp), // Padding inferior para evitar que la navbar tape el contenido
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            // Header de búsqueda
            OpportunitiesHeader()
        }

        item {
            // Filtros de género musical
            GenreFiltersSection(
                selectedFilter = selectedFilter,
                onFilterSelected = { selectedFilter = it }
            )
        }

        items(getFilteredOpportunities(selectedFilter)) { opportunity ->
            OpportunityCard(
                opportunity = opportunity,
                onApply = { /* Lógica de postulación */ },
                modifier = Modifier.padding(horizontal = 20.dp)
            )
        }
    }
}

/**
 * Header de la pantalla de oportunidades
 */
@Composable
private fun OpportunitiesHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Buscar Shows",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = RobotoFontFamily,
            color = VibeStageWhite
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(
                        VibeStageGrayDark.copy(alpha = 0.6f),
                        RoundedCornerShape(20.dp)
                    )
                    .padding(10.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Buscar",
                    tint = VibeStageGolden,
                    modifier = Modifier.size(20.dp)
                )
            }

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
}

/**
 * Filtros por género musical
 */
@Composable
private fun GenreFiltersSection(
    selectedFilter: String,
    onFilterSelected: (String) -> Unit
) {
    val filters = listOf("Todos", "Rock", "Jazz", "Pop", "Indie", "Acústico", "Urgente")

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
 * Chip de filtro mejorado
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
            .clickable { onClick() }
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
 * Card de oportunidad con imagen
 */
@Composable
private fun OpportunityCard(
    opportunity: EventOpportunity,
    onApply: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                VibeStageGrayDark.copy(alpha = 0.4f),
                RoundedCornerShape(20.dp)
            )
    ) {
        Column {
            // Imagen del venue (placeholder por ahora)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(
                        VibeStageGrayMedium.copy(alpha = 0.3f),
                        RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.MusicNote,
                    contentDescription = null,
                    tint = VibeStageGolden,
                    modifier = Modifier.size(40.dp)
                )

                // Badge urgente si aplica
                if (opportunity.isUrgent) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(12.dp)
                            .background(
                                VibeStageError,
                                RoundedCornerShape(8.dp)
                            )
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "URGENTE",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = MontserratFontFamily,
                            color = VibeStageWhite
                        )
                    }
                }
            }

            // Contenido de la oportunidad
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = opportunity.venueName,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = RobotoFontFamily,
                            color = VibeStageWhite
                        )

                        Text(
                            text = opportunity.eventType,
                            fontSize = 14.sp,
                            fontFamily = MontserratFontFamily,
                            color = VibeStageGrayLight,
                            modifier = Modifier.padding(top = 4.dp)
                        )

                        Text(
                            text = "${opportunity.eventDate} • ${opportunity.location}",
                            fontSize = 12.sp,
                            fontFamily = MontserratFontFamily,
                            color = VibeStageGrayLight,
                            modifier = Modifier.padding(top = 8.dp)
                        )

                        Text(
                            text = opportunity.genresWanted.joinToString(", "),
                            fontSize = 12.sp,
                            fontFamily = MontserratFontFamily,
                            color = VibeStageGolden,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }

                    Column(
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            text = opportunity.payment,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = RobotoFontFamily,
                            color = VibeStageGolden
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Box(
                            modifier = Modifier
                                .background(
                                    VibeStageGolden,
                                    RoundedCornerShape(8.dp)
                                )
                                .clickable { onApply() }
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                        ) {
                            Text(
                                text = "Postular",
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
    }
}

/**
 * Función para obtener oportunidades filtradas
 */
private fun getFilteredOpportunities(filter: String): List<EventOpportunity> {
    val allOpportunities = listOf(
        EventOpportunity(
            id = "1",
            venueName = "La Noche Bar",
            eventDate = "20 Oct 2025",
            eventType = "Noche de Rock",
            location = "Miraflores",
            payment = "S/ 600",
            genresWanted = listOf("Rock", "Pop Rock"),
            isUrgent = true,
            venueImage = "https://images.unsplash.com/photo-1516450360452-9312f5e86fc7?w=300"
        ),
        EventOpportunity(
            id = "2",
            venueName = "Centro Cultural",
            eventDate = "25 Oct 2025",
            eventType = "Festival Indie",
            location = "Barranco",
            payment = "S/ 450",
            genresWanted = listOf("Indie", "Alternativo"),
            venueImage = "https://images.unsplash.com/photo-1493225457124-a3eb161ffa5f?w=300"
        ),
        EventOpportunity(
            id = "3",
            venueName = "Jazz Club Lima",
            eventDate = "28 Oct 2025",
            eventType = "Noche de Jazz",
            location = "San Isidro",
            payment = "S/ 750",
            genresWanted = listOf("Jazz", "Blues"),
            venueImage = "https://images.unsplash.com/photo-1415201364774-f6f0bb35f28f?w=300"
        ),
        EventOpportunity(
            id = "4",
            venueName = "El Dragón Club",
            eventDate = "30 Oct 2025",
            eventType = "Noche Acústica",
            location = "San Isidro",
            payment = "S/ 350",
            genresWanted = listOf("Acústico", "Folk"),
            venueImage = "https://images.unsplash.com/photo-1501386761578-eac5c94b800a?w=300"
        )
    )

    return when (filter) {
        "Rock" -> allOpportunities.filter { it.genresWanted.any { genre -> genre.contains("Rock", ignoreCase = true) } }
        "Jazz" -> allOpportunities.filter { it.genresWanted.any { genre -> genre.contains("Jazz", ignoreCase = true) } }
        "Indie" -> allOpportunities.filter { it.genresWanted.any { genre -> genre.contains("Indie", ignoreCase = true) } }
        "Acústico" -> allOpportunities.filter { it.genresWanted.any { genre -> genre.contains("Acústico", ignoreCase = true) } }
        "Urgente" -> allOpportunities.filter { it.isUrgent }
        else -> allOpportunities
    }
}
