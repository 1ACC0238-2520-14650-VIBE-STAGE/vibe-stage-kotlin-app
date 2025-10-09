package com.example.vibestage.presentation.artist.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.Help
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
 * Pantalla de perfil del artista
 */
@Composable
fun ArtistProfileScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = VibeStageBlack)
            .padding(top = 80.dp), // Aumentado el padding superior
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        item {
            // Header del perfil
            ProfileHeader()
        }

        item {
            // Información del artista
            ArtistInfoSection()
        }

        item {
            // Galería de fotos
            PhotoGallerySection()
        }

        item {
            // Configuraciones
            SettingsSection()
        }
    }
}

/**
 * Header del perfil
 */
@Composable
private fun ProfileHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Mi Perfil",
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
                imageVector = Icons.Default.Edit,
                contentDescription = "Editar perfil",
                tint = VibeStageGolden,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

/**
 * Información principal del artista
 */
@Composable
private fun ArtistInfoSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .background(
                VibeStageGrayDark.copy(alpha = 0.4f),
                RoundedCornerShape(20.dp)
            )
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Avatar principal
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(
                        VibeStageGrayMedium.copy(alpha = 0.3f),
                        RoundedCornerShape(40.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                // Placeholder para foto de perfil
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = VibeStageGolden,
                    modifier = Modifier.size(40.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Los Rockeros",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = RobotoFontFamily,
                    color = VibeStageWhite
                )

                Text(
                    text = "Rock • Pop Rock",
                    fontSize = 14.sp,
                    fontFamily = MontserratFontFamily,
                    color = VibeStageGolden,
                    modifier = Modifier.padding(top = 4.dp)
                )

                Text(
                    text = "Lima, Perú",
                    fontSize = 14.sp,
                    fontFamily = MontserratFontFamily,
                    color = VibeStageGrayLight,
                    modifier = Modifier.padding(top = 2.dp)
                )

                Text(
                    text = "3 años de experiencia",
                    fontSize = 12.sp,
                    fontFamily = MontserratFontFamily,
                    color = VibeStageGrayLight,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            // Rating
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = VibeStageGolden,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "4.8",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = MontserratFontFamily,
                        color = VibeStageWhite,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
                Text(
                    text = "12 reseñas",
                    fontSize = 10.sp,
                    fontFamily = MontserratFontFamily,
                    color = VibeStageGrayLight,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }
        }
    }
}

/**
 * Galería de fotos del artista
 */
@Composable
private fun PhotoGallerySection() {
    Column(
        modifier = Modifier.padding(horizontal = 20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Galería",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = RobotoFontFamily,
                color = VibeStageWhite
            )

            Text(
                text = "Agregar fotos",
                fontSize = 14.sp,
                fontFamily = MontserratFontFamily,
                color = VibeStageGolden
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(4) { index ->
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .background(
                            VibeStageGrayMedium.copy(alpha = 0.3f),
                            RoundedCornerShape(16.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    if (index == 0) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Agregar foto",
                            tint = VibeStageGolden,
                            modifier = Modifier.size(32.dp)
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.MusicNote,
                            contentDescription = null,
                            tint = VibeStageGrayLight,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        }
    }
}

/**
 * Configuraciones del perfil
 */
@Composable
private fun SettingsSection() {
    Column(
        modifier = Modifier.padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Configuración",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = RobotoFontFamily,
            color = VibeStageWhite,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        SettingItem(
            title = "Notificaciones",
            subtitle = "Gestiona alertas",
            icon = Icons.Default.Notifications
        )

        SettingItem(
            title = "Privacidad",
            subtitle = "Configuración de cuenta",
            icon = Icons.Default.Security
        )

        SettingItem(
            title = "Ayuda",
            subtitle = "Centro de soporte",
            icon = Icons.AutoMirrored.Filled.Help
        )

        SettingItem(
            title = "Cerrar Sesión",
            subtitle = "Salir de la cuenta",
            icon = Icons.AutoMirrored.Filled.ExitToApp
        )
    }
}

/**
 * Item de configuración
 */
@Composable
private fun SettingItem(
    title: String,
    subtitle: String,
    icon: ImageVector
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(
                    VibeStageGrayDark.copy(alpha = 0.4f),
                    RoundedCornerShape(20.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = VibeStageGolden,
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = MontserratFontFamily,
                color = VibeStageWhite
            )
            Text(
                text = subtitle,
                fontSize = 14.sp,
                fontFamily = MontserratFontFamily,
                color = VibeStageGrayLight,
                modifier = Modifier.padding(top = 2.dp)
            )
        }

        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = null,
            tint = VibeStageGrayLight,
            modifier = Modifier.size(16.dp)
        )
    }
}
