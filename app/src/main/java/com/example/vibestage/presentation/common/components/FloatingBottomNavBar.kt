package com.example.vibestage.presentation.common.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vibestage.ui.theme.*

/**
 * Datos para elementos del navbar
 */
data class NavItem(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val label: String,
    val route: String
)

/**
 * Navbar flotante personalizado para VibeStage
 */
@Composable
fun FloatingBottomNavBar(
    items: List<NavItem>,
    selectedIndex: Int,
    onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 12.dp)
            .shadow(
                elevation = 12.dp,
                shape = RoundedCornerShape(32.dp),
                ambientColor = VibeStageGolden.copy(alpha = 0.2f),
                spotColor = VibeStageGolden.copy(alpha = 0.2f)
            ),
        colors = CardDefaults.cardColors(
            containerColor = VibeStageGrayDark
        ),
        shape = RoundedCornerShape(32.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = VibeStageGrayDark,
                    shape = RoundedCornerShape(32.dp)
                )
                .padding(horizontal = 24.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEachIndexed { index, item ->
                FloatingNavItem(
                    item = item,
                    isSelected = selectedIndex == index,
                    onClick = { onItemClick(index) },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

/**
 * Item individual del navbar flotante
 */
@Composable
private fun FloatingNavItem(
    item: NavItem,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.85f else if (isSelected) 1.2f else 1f,
        animationSpec = tween(200),
        label = "nav_item_scale"
    )

    val backgroundColor by animateColorAsState(
        targetValue = if (isSelected) VibeStageGolden.copy(alpha = 0.2f) else Color.Transparent,
        animationSpec = tween(300),
        label = "nav_item_background"
    )

    val iconColor by animateColorAsState(
        targetValue = if (isSelected) VibeStageGolden else VibeStageGrayLight,
        animationSpec = tween(300),
        label = "nav_item_icon_color"
    )

    Box(
        modifier = modifier
            .scale(scale)
            .clip(RoundedCornerShape(24.dp))
            .background(backgroundColor)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) { onClick() }
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
            contentDescription = item.label,
            tint = iconColor,
            modifier = Modifier.size(26.dp)
        )
    }
}

/**
 * Items del navbar para artistas
 */
val artistNavItems = listOf(
    NavItem(
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        label = "Inicio",
        route = "artist_home"
    ),
    NavItem(
        selectedIcon = Icons.Filled.Search,
        unselectedIcon = Icons.Outlined.Search,
        label = "Buscar",
        route = "artist_opportunities"
    ),
    NavItem(
        selectedIcon = Icons.Filled.Event,
        unselectedIcon = Icons.Outlined.Event,
        label = "Mis Shows",
        route = "artist_events"
    ),
    NavItem(
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person,
        label = "Perfil",
        route = "artist_profile"
    )
)

/**
 * Items del navbar para promotores
 */
val promoterNavItems = listOf(
    NavItem(
        selectedIcon = Icons.Filled.Dashboard,
        unselectedIcon = Icons.Outlined.Dashboard,
        label = "Dashboard",
        route = "promoter_home"
    ),
    NavItem(
        selectedIcon = Icons.Filled.People,
        unselectedIcon = Icons.Outlined.People,
        label = "Artistas",
        route = "promoter_applications"
    ),
    NavItem(
        selectedIcon = Icons.Filled.Event,
        unselectedIcon = Icons.Outlined.Event,
        label = "Eventos",
        route = "promoter_events"
    ),
    NavItem(
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person,
        label = "Perfil",
        route = "promoter_profile"
    )
)
