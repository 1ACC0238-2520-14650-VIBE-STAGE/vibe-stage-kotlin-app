package com.example.vibestage.presentation.artist.events

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
 * Datos para eventos del artista
 */
data class ArtistEvent(
    val id: String,
    val venueName: String,
    val eventName: String,
    val date: String,
    val time: String,
    val payment: String,
    val status: EventStatus,
    val venueImage: String = "",
    val attendeesExpected: Int = 0
)

enum class EventStatus {
    CONFIRMED, PENDING, COMPLETED, CANCELLED
}

/**
 * Pantalla de eventos del artista
 */
@Composable
fun ArtistEventsScreen() {
    var selectedStatus by remember { mutableStateOf(EventStatus.CONFIRMED) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = VibeStageBlack)
            .padding(top = 80.dp), // Aumentado el padding superior
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            // Header
            EventsHeader()
        }

        item {
            // Filtros por estado
            EventStatusFilters(
                selectedStatus = selectedStatus,
                onStatusSelected = { selectedStatus = it }
            )
        }

        item {
            // Resumen de eventos
            EventsSummarySection()
        }

        items(getEventsByStatus(selectedStatus)) { event ->
            EventCard(
                event = event,
                onEventClick = { /* Ver detalles */ },
                modifier = Modifier.padding(horizontal = 20.dp)
            )
        }
    }
}

/**
 * Header de eventos
 */
@Composable
private fun EventsHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Mis Shows",
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
                imageVector = Icons.Default.CalendarToday,
                contentDescription = "Calendario",
                tint = VibeStageGolden,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

/**
 * Filtros por estado de evento
 */
@Composable
private fun EventStatusFilters(
    selectedStatus: EventStatus,
    onStatusSelected: (EventStatus) -> Unit
) {
    LazyRow(
        modifier = Modifier.padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            StatusFilterChip(
                text = "Confirmados",
                isSelected = selectedStatus == EventStatus.CONFIRMED,
                onClick = { onStatusSelected(EventStatus.CONFIRMED) },
                count = 3
            )
        }
        item {
            StatusFilterChip(
                text = "Pendientes",
                isSelected = selectedStatus == EventStatus.PENDING,
                onClick = { onStatusSelected(EventStatus.PENDING) },
                count = 2
            )
        }
        item {
            StatusFilterChip(
                text = "Completados",
                isSelected = selectedStatus == EventStatus.COMPLETED,
                onClick = { onStatusSelected(EventStatus.COMPLETED) },
                count = 12
            )
        }
    }
}

/**
 * Chip de filtro con contador
 */
@Composable
private fun StatusFilterChip(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    count: Int
) {
    Box(
        modifier = Modifier
            .background(
                if (isSelected) VibeStageGolden.copy(alpha = 0.2f) else VibeStageGrayDark.copy(alpha = 0.4f),
                RoundedCornerShape(20.dp)
            )
            .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = text,
                fontSize = 14.sp,
                fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium,
                fontFamily = MontserratFontFamily,
                color = if (isSelected) VibeStageGolden else VibeStageGrayLight
            )

            Box(
                modifier = Modifier
                    .background(
                        if (isSelected) VibeStageGolden else VibeStageGrayLight,
                        RoundedCornerShape(10.dp)
                    )
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
                Text(
                    text = count.toString(),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = MontserratFontFamily,
                    color = if (isSelected) VibeStageBlack else VibeStageBlack
                )
            }
        }
    }
}

/**
 * Resumen de eventos
 */
@Composable
private fun EventsSummarySection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        SummaryCard(
            title = "Próximo Show",
            subtitle = "Jazz Club Lima",
            date = "15 Oct",
            color = VibeStageGolden,
            modifier = Modifier.weight(1f)
        )

        SummaryCard(
            title = "Ganado Este Mes",
            subtitle = "S/ 2,500",
            date = "Oct 2025",
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
    subtitle: String,
    date: String,
    color: androidx.compose.ui.graphics.Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(100.dp)
            .background(
                VibeStageGrayDark.copy(alpha = 0.4f),
                RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                fontSize = 12.sp,
                fontFamily = MontserratFontFamily,
                color = VibeStageGrayLight
            )

            Text(
                text = subtitle,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = RobotoFontFamily,
                color = color
            )

            Text(
                text = date,
                fontSize = 10.sp,
                fontFamily = MontserratFontFamily,
                color = VibeStageGrayLight
            )
        }
    }
}

/**
 * Card de evento mejorada con imagen
 */
@Composable
private fun EventCard(
    event: ArtistEvent,
    onEventClick: () -> Unit,
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
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            // Imagen del venue
            Box(
                modifier = Modifier
                    .width(80.dp)
                    .height(100.dp)
                    .background(
                        VibeStageGrayMedium.copy(alpha = 0.3f),
                        RoundedCornerShape(topStart = 20.dp, bottomStart = 20.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.MusicNote,
                    contentDescription = null,
                    tint = VibeStageGolden,
                    modifier = Modifier.size(24.dp)
                )
            }

            // Información del evento
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = event.venueName,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = RobotoFontFamily,
                            color = VibeStageWhite
                        )

                        Text(
                            text = event.eventName,
                            fontSize = 14.sp,
                            fontFamily = MontserratFontFamily,
                            color = VibeStageGrayLight,
                            modifier = Modifier.padding(top = 2.dp)
                        )

                        Text(
                            text = "${event.date} • ${event.time}",
                            fontSize = 12.sp,
                            fontFamily = MontserratFontFamily,
                            color = VibeStageGrayLight,
                            modifier = Modifier.padding(top = 4.dp)
                        )

                        Text(
                            text = event.payment,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = RobotoFontFamily,
                            color = VibeStageGolden,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }

                    // Status badge
                    Box(
                        modifier = Modifier
                            .background(
                                when(event.status) {
                                    EventStatus.CONFIRMED -> VibeStageSuccess.copy(alpha = 0.2f)
                                    EventStatus.PENDING -> VibeStageWarning.copy(alpha = 0.2f)
                                    EventStatus.COMPLETED -> VibeStageInfo.copy(alpha = 0.2f)
                                    EventStatus.CANCELLED -> VibeStageError.copy(alpha = 0.2f)
                                },
                                RoundedCornerShape(8.dp)
                            )
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = when(event.status) {
                                EventStatus.CONFIRMED -> "Confirmado"
                                EventStatus.PENDING -> "Pendiente"
                                EventStatus.COMPLETED -> "Completado"
                                EventStatus.CANCELLED -> "Cancelado"
                            },
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = MontserratFontFamily,
                            color = when(event.status) {
                                EventStatus.CONFIRMED -> VibeStageSuccess
                                EventStatus.PENDING -> VibeStageWarning
                                EventStatus.COMPLETED -> VibeStageInfo
                                EventStatus.CANCELLED -> VibeStageError
                            }
                        )
                    }
                }
            }
        }
    }
}

/**
 * Función para obtener eventos por estado
 */
private fun getEventsByStatus(status: EventStatus): List<ArtistEvent> {
    val allEvents = listOf(
        ArtistEvent(
            id = "1",
            venueName = "Jazz Club Lima",
            eventName = "Noche de Jazz",
            date = "15 Oct 2025",
            time = "21:00",
            payment = "S/ 800",
            status = EventStatus.CONFIRMED,
            venueImage = "https://images.unsplash.com/photo-1415201364774-f6f0bb35f28f?w=300",
            attendeesExpected = 120
        ),
        ArtistEvent(
            id = "2",
            venueName = "La Noche Bar",
            eventName = "Rock Night",
            date = "22 Oct 2025",
            time = "22:30",
            payment = "S/ 600",
            status = EventStatus.PENDING,
            venueImage = "https://images.unsplash.com/photo-1516450360452-9312f5e86fc7?w=300",
            attendeesExpected = 80
        ),
        ArtistEvent(
            id = "3",
            venueName = "Centro Cultural",
            eventName = "Festival Indie",
            date = "05 Oct 2025",
            time = "20:00",
            payment = "S/ 450",
            status = EventStatus.COMPLETED,
            venueImage = "https://images.unsplash.com/photo-1493225457124-a3eb161ffa5f?w=300",
            attendeesExpected = 200
        )
    )

    return allEvents.filter { it.status == status }
}
