package com.example.vibestage.presentation.promoter.events

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
 * Datos para eventos del promotor
 */
data class PromoterEvent(
    val id: String,
    val eventName: String,
    val artistName: String,
    val date: String,
    val time: String,
    val expectedAttendance: Int,
    val actualAttendance: Int = 0,
    val revenue: String,
    val status: PromoterEventStatus,
    val eventImage: String = ""
)

enum class PromoterEventStatus {
    UPCOMING, ONGOING, COMPLETED, CANCELLED
}

/**
 * Pantalla de eventos para promotores
 */
@Composable
fun PromoterEventsScreen() {
    var selectedStatus by remember { mutableStateOf(PromoterEventStatus.UPCOMING) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = VibeStageBlack)
            .padding(top = 80.dp), // Aumentado el padding superior
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
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
            PromoterEventCard(
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
            text = "Mis Eventos",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = RobotoFontFamily,
            color = VibeStageWhite
        )

        // Eliminar los íconos problemáticos temporalmente
        // o agregar funcionalidad segura más adelante
        Spacer(modifier = Modifier.width(8.dp))
    }
}

/**
 * Filtros por estado de evento
 */
@Composable
private fun EventStatusFilters(
    selectedStatus: PromoterEventStatus,
    onStatusSelected: (PromoterEventStatus) -> Unit
) {
    LazyRow(
        modifier = Modifier.padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            StatusFilterChip(
                text = "Próximos",
                isSelected = selectedStatus == PromoterEventStatus.UPCOMING,
                onClick = { onStatusSelected(PromoterEventStatus.UPCOMING) },
                count = 3
            )
        }
        item {
            StatusFilterChip(
                text = "En Curso",
                isSelected = selectedStatus == PromoterEventStatus.ONGOING,
                onClick = { onStatusSelected(PromoterEventStatus.ONGOING) },
                count = 1
            )
        }
        item {
            StatusFilterChip(
                text = "Completados",
                isSelected = selectedStatus == PromoterEventStatus.COMPLETED,
                onClick = { onStatusSelected(PromoterEventStatus.COMPLETED) },
                count = 15
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
            .clickable { onClick() }
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
                    color = VibeStageBlack
                )
            }
        }
    }
}

/**
 * Card de evento del promotor mejorada
 */
@Composable
private fun PromoterEventCard(
    event: PromoterEvent,
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
        Column {
            // Imagen del evento - Reemplazada con ícono simple
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(
                        VibeStageGolden.copy(alpha = 0.1f),
                        RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.MusicNote,
                    contentDescription = "Evento",
                    tint = VibeStageGolden,
                    modifier = Modifier.size(40.dp)
                )

                // Status badge
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(12.dp)
                        .background(
                            when(event.status) {
                                PromoterEventStatus.UPCOMING -> VibeStageWarning
                                PromoterEventStatus.ONGOING -> VibeStageSuccess
                                PromoterEventStatus.COMPLETED -> VibeStageInfo
                                PromoterEventStatus.CANCELLED -> VibeStageError
                            },
                            RoundedCornerShape(8.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = when(event.status) {
                            PromoterEventStatus.UPCOMING -> "Próximo"
                            PromoterEventStatus.ONGOING -> "En curso"
                            PromoterEventStatus.COMPLETED -> "Completado"
                            PromoterEventStatus.CANCELLED -> "Cancelado"
                        },
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = MontserratFontFamily,
                        color = VibeStageWhite
                    )
                }
            }

            // Información del evento
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
                            text = event.eventName,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = RobotoFontFamily,
                            color = VibeStageWhite
                        )

                        Text(
                            text = "Artista: ${event.artistName}",
                            fontSize = 14.sp,
                            fontFamily = MontserratFontFamily,
                            color = VibeStageGolden,
                            modifier = Modifier.padding(top = 4.dp)
                        )

                        Text(
                            text = "${event.date} • ${event.time}",
                            fontSize = 12.sp,
                            fontFamily = MontserratFontFamily,
                            color = VibeStageGrayLight,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }

                    Column(
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            text = event.revenue,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = RobotoFontFamily,
                            color = VibeStageGolden
                        )

                        Text(
                            text = "${event.expectedAttendance} asistentes",
                            fontSize = 11.sp,
                            fontFamily = MontserratFontFamily,
                            color = VibeStageGrayLight,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
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
            title = "Este Mes",
            value = "8",
            subtitle = "eventos",
            color = VibeStageInfo,
            modifier = Modifier.weight(1f)
        )

        SummaryCard(
            title = "Ingresos",
            value = "S/ 15K",
            subtitle = "octubre",
            color = VibeStageGolden,
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
    subtitle: String,
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
                text = value,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = RobotoFontFamily,
                color = color
            )

            Text(
                text = subtitle,
                fontSize = 10.sp,
                fontFamily = MontserratFontFamily,
                color = VibeStageGrayLight
            )
        }
    }
}

/**
 * Función para obtener eventos por estado
 */
private fun getEventsByStatus(status: PromoterEventStatus): List<PromoterEvent> {
    val allEvents = listOf(
        PromoterEvent(
            id = "1",
            eventName = "Noche de Rock",
            artistName = "Los Rockeros",
            date = "15 Oct 2025",
            time = "22:00",
            expectedAttendance = 120,
            actualAttendance = 115,
            revenue = "S/ 3,200",
            status = PromoterEventStatus.UPCOMING,
            eventImage = "https://images.unsplash.com/photo-1516450360452-9312f5e86fc7?w=400"
        ),
        PromoterEvent(
            id = "2",
            eventName = "Jazz Night",
            artistName = "Jazz Collective",
            date = "22 Oct 2025",
            time = "21:00",
            expectedAttendance = 80,
            revenue = "S/ 2,400",
            status = PromoterEventStatus.UPCOMING,
            eventImage = "https://images.unsplash.com/photo-1415201364774-f6f0bb35f28f?w=400"
        ),
        PromoterEvent(
            id = "3",
            eventName = "Festival Indie",
            artistName = "Indie Stars",
            date = "05 Oct 2025",
            time = "20:00",
            expectedAttendance = 200,
            actualAttendance = 180,
            revenue = "S/ 4,500",
            status = PromoterEventStatus.COMPLETED,
            eventImage = "https://images.unsplash.com/photo-1493225457124-a3eb161ffa5f?w=400"
        )
    )

    return allEvents.filter { it.status == status }
}
