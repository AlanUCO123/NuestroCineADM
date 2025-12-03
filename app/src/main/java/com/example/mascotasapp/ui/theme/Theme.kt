// ui.theme/Theme.kt

package com.example.mascotasapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// Define el esquema de color claro con tus nuevos colores
private val LightColorScheme = lightColorScheme(
    primary = PrimaryBrown, // Marrón oscuro para elementos principales
    onPrimary = OnPrimaryWhite, // Texto blanco sobre el marrón
    secondary = SecondaryBeige, // Beige para acentos
    onSecondary = OnSecondaryBrown, // Marrón oscuro sobre beige
    tertiary = SecondaryBeige, // Puedes usar el mismo secondary o definir otro
    onTertiary = OnSecondaryBrown,

    background = BackgroundLight, // Fondo general claro
    onBackground = OnSurfaceDark, // Texto oscuro sobre el fondo

    surface = SurfaceCream, // Superficies de Cards, un poco más cálido
    onSurface = OnSurfaceDark, // Texto oscuro sobre superficies

    error = ErrorRed, // Color para errores
    onError = OnPrimaryWhite // Texto blanco sobre error
)

// Puedes definir un DarkColorScheme si tu app soporta modo oscuro,
// o simplemente dejarlo como está o adaptarlo también.
private val DarkColorScheme = darkColorScheme(
    primary = PrimaryBrown, // Podrías querer un marrón más oscuro o un beige para el modo oscuro
    onPrimary = OnPrimaryWhite,
    secondary = SecondaryBeige,
    onSecondary = OnSecondaryBrown,
    tertiary = SecondaryBeige,
    onTertiary = OnSecondaryBrown,
    background = Color(0xFF1C1B1F), // Color de fondo oscuro por defecto
    onBackground = Color(0xFFE6E1E5),
    surface = Color(0xFF1C1B1F),
    onSurface = Color(0xFFE6E1E5),
    error = ErrorRed,
    onError = OnPrimaryWhite
)


@Composable
fun MascotasAppTheme( // Asegúrate de que este sea el nombre correcto de tu tema
    darkTheme: Boolean = false,
    // Dynamic color is available only on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb() // Puedes cambiar esto para que combine mejor con tu fondo
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography, // Asegúrate de que tu tipografía también esté definida
        content = content
    )
}