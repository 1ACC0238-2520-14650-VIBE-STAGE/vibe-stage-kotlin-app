package com.example.vibestage.presentation.artist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.vibestage.presentation.common.components.FloatingBottomNavBar
import com.example.vibestage.presentation.common.components.artistNavItems
import com.example.vibestage.presentation.artist.home.ArtistHomeScreen
import com.example.vibestage.presentation.artist.opportunities.ArtistOpportunitiesScreen
import com.example.vibestage.presentation.artist.events.ArtistEventsScreen
import com.example.vibestage.presentation.artist.profile.ArtistProfileScreen
import com.example.vibestage.ui.theme.*

/**
 * Dashboard principal para artistas con navbar flotante
 */
@Composable
fun ArtistDashboardScreen() {
    var selectedNavIndex by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = VibeStageBlack)
    ) {
        // Contenido principal usando pantallas separadas
        when (selectedNavIndex) {
            0 -> ArtistHomeScreen()
            1 -> ArtistOpportunitiesScreen()
            2 -> ArtistEventsScreen()
            3 -> ArtistProfileScreen()
        }

        // Navbar flotante
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
        ) {
            FloatingBottomNavBar(
                items = artistNavItems,
                selectedIndex = selectedNavIndex,
                onItemClick = { selectedNavIndex = it }
            )
        }
    }
}
