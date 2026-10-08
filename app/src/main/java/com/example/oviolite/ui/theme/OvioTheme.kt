package com.example.oviolite.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/*
 * On iOS, OVIO sets `.fontDesign(.serif)` once at the root and every Text inherits it.
 * In Compose we do the same thing through MaterialTheme's Typography.
 * FontFamily.Serif is the phone's built-in serif font, so there's nothing to download.
 */
private val Serif = FontFamily.Serif

private val OvioTypography = Typography(
    displayLarge = TextStyle(fontFamily = Serif, fontWeight = FontWeight.Normal, fontSize = 44.sp, letterSpacing = 12.sp),
    headlineMedium = TextStyle(fontFamily = Serif, fontWeight = FontWeight.Normal, fontSize = 26.sp),
    titleLarge = TextStyle(fontFamily = Serif, fontWeight = FontWeight.Normal, fontSize = 22.sp),
    titleMedium = TextStyle(fontFamily = Serif, fontWeight = FontWeight.SemiBold, fontSize = 17.sp),
    bodyLarge = TextStyle(fontFamily = Serif, fontSize = 15.sp, lineHeight = 21.sp),
    bodyMedium = TextStyle(fontFamily = Serif, fontSize = 13.sp, lineHeight = 18.sp),
    labelLarge = TextStyle(fontFamily = Serif, fontWeight = FontWeight.Medium, fontSize = 13.sp),
    labelSmall = TextStyle(fontFamily = Serif, fontWeight = FontWeight.Bold, fontSize = 10.sp, letterSpacing = 1.5.sp),
)

private val OvioDarkScheme = darkColorScheme(
    primary = OvioColors.Champagne,
    onPrimary = OvioColors.Ground,
    background = OvioColors.Background,
    onBackground = OvioColors.TextPrimary,
    surface = OvioColors.Ground,
    onSurface = OvioColors.TextPrimary,
    onSurfaceVariant = OvioColors.TextSecondary,
)

/** OVIO is dark-first, like the iOS app. We ignore the system light mode on purpose. */
@Composable
fun OvioTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = OvioDarkScheme,
        typography = OvioTypography,
        content = content,
    )
}
