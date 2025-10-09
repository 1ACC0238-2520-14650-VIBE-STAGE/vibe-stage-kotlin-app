package com.example.vibestage.presentation.auth

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vibestage.R
import com.example.vibestage.presentation.common.components.VibeStageButton
import com.example.vibestage.presentation.common.components.VibeStageTextField
import com.example.vibestage.ui.theme.*

/**
 * Pantalla de login mejorada de VibeStage con Google Sign-In
 */
@Composable
fun LoginScreen(
    onNavigateToRegister: (String) -> Unit,
    onLoginSuccess: (String) -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var selectedUserType by remember { mutableStateOf("artist") }
    var passwordVisible by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }
    var showError by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(
                        VibeStageGrayDark,
                        VibeStageBlack,
                        VibeStageBlack
                    ),
                    radius = 1000f
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(60.dp))

            // Logo y título mejorado
            Card(
                modifier = Modifier.size(120.dp),
                colors = CardDefaults.cardColors(
                    containerColor = VibeStageGolden.copy(alpha = 0.1f)
                ),
                shape = RoundedCornerShape(30.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "VS",
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = RobotoFontFamily,
                        color = VibeStageGolden
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Bienvenido a VibeStage",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = RobotoFontFamily,
                color = VibeStageWhite,
                textAlign = TextAlign.Center
            )

            Text(
                text = "Conecta música y espacios",
                fontSize = 16.sp,
                fontFamily = MontserratFontFamily,
                color = VibeStageGrayLight,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 8.dp)
            )

            Spacer(modifier = Modifier.height(40.dp))

            // Selector de tipo de usuario mejorado
            EnhancedUserTypeSelector(
                selectedType = selectedUserType,
                onTypeSelected = { selectedUserType = it },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Botón de Google Sign-In
            GoogleSignInButton(
                onClick = {
                    try {
                        // Agregar manejo de errores y validación
                        isLoading = true
                        // Simular un pequeño delay para mostrar que está procesando
                        // En una implementación real, aquí iría la lógica de Google Sign-In

                        // Validar que selectedUserType no esté vacío
                        if (selectedUserType.isNotBlank()) {
                            onLoginSuccess(selectedUserType)
                        } else {
                            showError = true
                            isLoading = false
                        }
                    } catch (e: Exception) {
                        // Manejar cualquier error que pueda ocurrir
                        showError = true
                        isLoading = false
                    }
                },
                isLoading = isLoading,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Divisor
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Divider(
                    modifier = Modifier.weight(1f),
                    color = VibeStageGrayMedium,
                    thickness = 1.dp
                )
                Text(
                    text = "  O continúa con email  ",
                    fontSize = 14.sp,
                    fontFamily = MontserratFontFamily,
                    color = VibeStageGrayLight
                )
                Divider(
                    modifier = Modifier.weight(1f),
                    color = VibeStageGrayMedium,
                    thickness = 1.dp
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Campos de login mejorados
            VibeStageTextField(
                value = email,
                onValueChange = {
                    email = it
                    showError = false
                },
                label = "Correo electrónico",
                placeholder = "tu@email.com",
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = null,
                        tint = VibeStageGolden
                    )
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            VibeStageTextField(
                value = password,
                onValueChange = {
                    password = it
                    showError = false
                },
                label = "Contraseña",
                isPassword = !passwordVisible,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = null,
                        tint = VibeStageGolden
                    )
                },
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = if (passwordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                            contentDescription = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña",
                            tint = VibeStageGrayLight
                        )
                    }
                }
            )

            if (showError) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Credenciales incorrectas. Intenta nuevamente.",
                    color = VibeStageError,
                    fontSize = 14.sp,
                    fontFamily = MontserratFontFamily,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Botón de login
            VibeStageButton(
                text = "Iniciar Sesión",
                onClick = {
                    if (email.isNotBlank() && password.isNotBlank()) {
                        isLoading = true
                        onLoginSuccess(selectedUserType)
                    } else {
                        showError = true
                    }
                },
                isLoading = isLoading,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Enlace a registro mejorado
            TextButton(
                onClick = { onNavigateToRegister(selectedUserType) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "¿No tienes cuenta? Regístrate",
                    color = VibeStageGolden,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = MontserratFontFamily
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

/**
 * Selector mejorado para tipo de usuario
 */
@Composable
private fun EnhancedUserTypeSelector(
    selectedType: String,
    onTypeSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        EnhancedUserTypeCard(
            title = "Soy Artista",
            subtitle = "Músico o banda",
            icon = Icons.Default.Person,
            isSelected = selectedType == "artist",
            onClick = { onTypeSelected("artist") },
            modifier = Modifier.weight(1f)
        )

        EnhancedUserTypeCard(
            title = "Soy Promotor",
            subtitle = "Local o evento",
            icon = Icons.Default.Store,
            isSelected = selectedType == "promoter",
            onClick = { onTypeSelected("promoter") },
            modifier = Modifier.weight(1f)
        )
    }
}

/**
 * Tarjeta mejorada para seleccionar tipo de usuario
 */
@Composable
private fun EnhancedUserTypeCard(
    title: String,
    subtitle: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        animationSpec = tween(150),
        label = "card_scale"
    )

    val backgroundColor by animateColorAsState(
        targetValue = if (isSelected) VibeStageGolden.copy(alpha = 0.15f) else VibeStageGrayMedium.copy(alpha = 0.1f),
        animationSpec = tween(300),
        label = "background_color"
    )

    val borderColor by animateColorAsState(
        targetValue = if (isSelected) VibeStageGolden else Color.Transparent,
        animationSpec = tween(300),
        label = "border_color"
    )

    Card(
        modifier = modifier
            .scale(scale)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) { onClick() },
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (isSelected) 8.dp else 2.dp
        ),
        border = androidx.compose.foundation.BorderStroke(
            width = if (isSelected) 2.dp else 0.dp,
            color = borderColor
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(
                        if (isSelected) VibeStageGolden.copy(alpha = 0.2f) else VibeStageGrayMedium.copy(alpha = 0.2f),
                        RoundedCornerShape(25.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = if (isSelected) VibeStageGolden else VibeStageGrayLight,
                    modifier = Modifier.size(28.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = title,
                color = if (isSelected) VibeStageWhite else VibeStageGrayLight,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = RobotoFontFamily,
                textAlign = TextAlign.Center
            )

            Text(
                text = subtitle,
                color = VibeStageGrayLight,
                fontSize = 12.sp,
                fontFamily = MontserratFontFamily,
                textAlign = TextAlign.Center
            )
        }
    }
}

/**
 * Botón de Google Sign-In personalizado
 */
@Composable
private fun GoogleSignInButton(
    onClick: () -> Unit,
    isLoading: Boolean = false,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.98f else 1f,
        animationSpec = tween(150),
        label = "google_button_scale"
    )

    Card(
        modifier = modifier
            .scale(scale)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = !isLoading
            ) { onClick() },
        colors = CardDefaults.cardColors(containerColor = VibeStageWhite),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    color = VibeStageGolden,
                    strokeWidth = 2.dp
                )
            } else {
                // Aquí iría el icono de Google (placeholder por ahora)
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    Color(0xFF4285F4),
                                    Color(0xFFDB4437),
                                    Color(0xFFF4B400),
                                    Color(0xFF0F9D58)
                                )
                            ),
                            shape = RoundedCornerShape(12.dp)
                        )
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = if (isLoading) "Autenticando..." else "Continuar con Google",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = MontserratFontFamily,
                color = VibeStageBlack
            )
        }
    }
}
