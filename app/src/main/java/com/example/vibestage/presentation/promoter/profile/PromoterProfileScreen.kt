package com.example.vibestage.presentation.promoter.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vibestage.ui.theme.*

/**
 * Pantalla de perfil para promotores
 */
@Composable
fun PromoterProfileScreen() {
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
            Text(
                text = "Mi Perfil",
                color = VibeStageWhite,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        item {
            // Información del perfil
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = VibeStageGrayDark)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Avatar
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .background(VibeStageGolden.copy(alpha = 0.2f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Avatar",
                            tint = VibeStageGolden,
                            modifier = Modifier.size(50.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Nombre centrado
                    Text(
                        text = "Nombre del Promotor",
                        color = VibeStageWhite,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    // Correo centrado
                    Text(
                        text = "promotor@example.com",
                        color = VibeStageGrayLight,
                        fontSize = 14.sp
                    )
                }
            }
        }

        item {
            // Estadísticas rápidas
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = VibeStageGrayDark)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Estadísticas",
                        color = VibeStageWhite,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        StatItem(
                            title = "Eventos",
                            value = "12",
                            icon = Icons.Default.Event
                        )
                        StatItem(
                            title = "Artistas",
                            value = "45",
                            icon = Icons.Default.People
                        )
                        StatItem(
                            title = "Rating",
                            value = "4.8",
                            icon = Icons.Default.Star
                        )
                    }
                }
            }
        }

        item {
            // Opciones del perfil
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ProfileOption(
                    icon = Icons.Default.Edit,
                    title = "Editar Perfil",
                    onClick = { /* Funcionalidad pendiente */ }
                )
                ProfileOption(
                    icon = Icons.Default.Settings,
                    title = "Configuración",
                    onClick = { /* Funcionalidad pendiente */ }
                )
                ProfileOption(
                    icon = Icons.AutoMirrored.Filled.Help,
                    title = "Ayuda y Soporte",
                    onClick = { /* Funcionalidad pendiente */ }
                )
                ProfileOption(
                    icon = Icons.AutoMirrored.Filled.ExitToApp,
                    title = "Cerrar Sesión",
                    onClick = { /* Funcionalidad pendiente */ }
                )
            }
        }
    }
}

@Composable
private fun StatItem(
    title: String,
    value: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = VibeStageGolden,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = value,
            color = VibeStageWhite,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = title,
            color = VibeStageGrayLight,
            fontSize = 12.sp
        )
    }
}

@Composable
private fun ProfileOption(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = VibeStageGrayDark),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = VibeStageGolden,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = title,
                color = VibeStageWhite,
                fontSize = 16.sp,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "Ir",
                tint = VibeStageGrayLight,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}
