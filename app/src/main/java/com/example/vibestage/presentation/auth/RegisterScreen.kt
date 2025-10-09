package com.example.vibestage.presentation.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vibestage.presentation.common.components.VibeStageButton
import com.example.vibestage.presentation.common.components.VibeStageTextField
import com.example.vibestage.ui.theme.*

/**
 * Pantalla de registro de VibeStage
 */
@Composable
fun RegisterScreen(
    userType: String,
    onNavigateBack: () -> Unit,
    onRegisterSuccess: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var showError by remember { mutableStateOf("") }

    // Campos específicos según el tipo de usuario
    var artistName by remember { mutableStateOf("") } // Para artistas
    var genreMusicale by remember { mutableStateOf("") } // Para artistas
    var localName by remember { mutableStateOf("") } // Para promotores
    var localAddress by remember { mutableStateOf("") } // Para promotores

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        VibeStageBlack,
                        VibeStageGrayDark
                    )
                )
            )
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        // Header con botón de regreso
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onNavigateBack) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Volver",
                    tint = VibeStageWhite
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "Registro ${if (userType == "artist") "Artista" else "Promotor"}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = RobotoFontFamily,
                color = VibeStageGolden
            )

            Spacer(modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.width(48.dp)) // Para balancear el IconButton
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Campos comunes
        VibeStageTextField(
            value = name,
            onValueChange = {
                name = it
                showError = ""
            },
            label = "Nombre completo",
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = VibeStageGrayLight
                )
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        VibeStageTextField(
            value = email,
            onValueChange = {
                email = it
                showError = ""
            },
            label = "Correo electrónico",
            placeholder = "ejemplo@email.com",
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = null,
                    tint = VibeStageGrayLight
                )
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        VibeStageTextField(
            value = phone,
            onValueChange = {
                phone = it
                showError = ""
            },
            label = "Teléfono",
            placeholder = "+51 999 999 999",
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Phone,
                    contentDescription = null,
                    tint = VibeStageGrayLight
                )
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campos específicos según el tipo de usuario
        if (userType == "artist") {
            ArtistSpecificFields(
                artistName = artistName,
                onArtistNameChange = { artistName = it },
                genreMusicale = genreMusicale,
                onGenreChange = { genreMusicale = it }
            )
        } else {
            PromoterSpecificFields(
                localName = localName,
                onLocalNameChange = { localName = it },
                localAddress = localAddress,
                onAddressChange = { localAddress = it }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        VibeStageTextField(
            value = password,
            onValueChange = {
                password = it
                showError = ""
            },
            label = "Contraseña",
            isPassword = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null,
                    tint = VibeStageGrayLight
                )
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        VibeStageTextField(
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it
                showError = ""
            },
            label = "Confirmar contraseña",
            isPassword = true,
            isError = password.isNotEmpty() && confirmPassword.isNotEmpty() && password != confirmPassword,
            errorMessage = if (password != confirmPassword) "Las contraseñas no coinciden" else "",
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null,
                    tint = VibeStageGrayLight
                )
            }
        )

        if (showError.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = showError,
                color = VibeStageError,
                fontSize = 14.sp,
                fontFamily = MontserratFontFamily,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Botón de registro
        VibeStageButton(
            text = "Crear Cuenta",
            onClick = {
                when {
                    name.isBlank() -> showError = "El nombre es requerido"
                    email.isBlank() -> showError = "El correo es requerido"
                    phone.isBlank() -> showError = "El teléfono es requerido"
                    password.isBlank() -> showError = "La contraseña es requerida"
                    password != confirmPassword -> showError = "Las contraseñas no coinciden"
                    userType == "artist" && artistName.isBlank() -> showError = "El nombre artístico es requerido"
                    userType == "promoter" && localName.isBlank() -> showError = "El nombre del local es requerido"
                    else -> {
                        isLoading = true
                        // Simular registro
                        onRegisterSuccess()
                    }
                }
            },
            isLoading = isLoading,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Información sobre términos y condiciones
        Text(
            text = "Al registrarte aceptas nuestros Términos y Condiciones y Política de Privacidad",
            color = VibeStageGrayLight,
            fontSize = 12.sp,
            fontFamily = MontserratFontFamily,
            textAlign = TextAlign.Center,
            lineHeight = 16.sp
        )
    }
}

/**
 * Campos específicos para artistas
 */
@Composable
private fun ArtistSpecificFields(
    artistName: String,
    onArtistNameChange: (String) -> Unit,
    genreMusicale: String,
    onGenreChange: (String) -> Unit
) {
    VibeStageTextField(
        value = artistName,
        onValueChange = onArtistNameChange,
        label = "Nombre artístico",
        placeholder = "Tu nombre en el escenario",
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.MusicNote,
                contentDescription = null,
                tint = VibeStageGrayLight
            )
        }
    )

    Spacer(modifier = Modifier.height(16.dp))

    VibeStageTextField(
        value = genreMusicale,
        onValueChange = onGenreChange,
        label = "Género musical principal",
        placeholder = "Rock, Pop, Jazz, etc.",
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Album,
                contentDescription = null,
                tint = VibeStageGrayLight
            )
        }
    )
}

/**
 * Campos específicos para promotores
 */
@Composable
private fun PromoterSpecificFields(
    localName: String,
    onLocalNameChange: (String) -> Unit,
    localAddress: String,
    onAddressChange: (String) -> Unit
) {
    VibeStageTextField(
        value = localName,
        onValueChange = onLocalNameChange,
        label = "Nombre del local",
        placeholder = "Bar, discoteca, centro cultural...",
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Business,
                contentDescription = null,
                tint = VibeStageGrayLight
            )
        }
    )

    Spacer(modifier = Modifier.height(16.dp))

    VibeStageTextField(
        value = localAddress,
        onValueChange = onAddressChange,
        label = "Dirección del local",
        placeholder = "Dirección completa",
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = null,
                tint = VibeStageGrayLight
            )
        }
    )
}
