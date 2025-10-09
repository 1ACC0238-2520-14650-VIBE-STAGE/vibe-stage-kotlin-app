package com.example.vibestage.presentation.promoter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.vibestage.presentation.common.components.FloatingBottomNavBar
import com.example.vibestage.presentation.common.components.promoterNavItems
import com.example.vibestage.presentation.promoter.applications.PromoterApplicationsScreen
import com.example.vibestage.presentation.promoter.events.PromoterEventsScreen
import com.example.vibestage.presentation.promoter.home.PromoterHomeScreen
import com.example.vibestage.presentation.promoter.profile.PromoterProfileScreen
import com.example.vibestage.ui.theme.*

/**
 * Dashboard principal para promotores con navbar flotante
 */
@Composable
fun PromoterDashboardScreen() {
    var selectedNavIndex by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = VibeStageBlack)
    ) {
        // Contenido principal usando pantallas separadas
        when (selectedNavIndex) {
            0 -> PromoterHomeScreen()
            1 -> PromoterApplicationsScreen()
            2 -> PromoterEventsScreen()
            3 -> PromoterProfileScreen()
        }

        // Navbar flotante
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
        ) {
            FloatingBottomNavBar(
                items = promoterNavItems,
                selectedIndex = selectedNavIndex,
                onItemClick = { selectedNavIndex = it }
            )
        }
    }
}
