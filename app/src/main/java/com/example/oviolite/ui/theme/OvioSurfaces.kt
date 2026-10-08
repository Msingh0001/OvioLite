package com.example.oviolite.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/*
 * OVIO's "glass" look, ported from the iOS view modifiers in OvioGold.swift.
 *
 *   SwiftUI                                  Compose
 *   .ovioSurface()                      →    Modifier.ovioSurface()
 *   .ovioPrimaryAction()                →    Modifier.ovioPrimaryAction()
 *   .ovioElevated(.primary)             →    Modifier.ovioElevated(Elevation.Primary)
 *
 * The trick behind "glass": a barely-visible fill plus a very thin, slightly
 * brighter border. That thin edge is what makes a dark panel read as glass
 * instead of a flat rectangle.
 *
 * THE RULE (from the iOS code): at most ONE Primary element per screen.
 * Gold only feels special when it's rare.
 */

enum class Elevation { Primary, Secondary, Quiet }

/** A content surface: list rows, info cards. */
fun Modifier.ovioSurface(corner: Dp = 16.dp, fill: Float = 0.06f, stroke: Float = 0.08f): Modifier {
    val shape = RoundedCornerShape(corner)
    return this
        .clip(shape)
        .background(Color.White.copy(alpha = fill), shape)
        .border(0.5.dp, Color.White.copy(alpha = stroke), shape)
}

/** The ONE main action on a screen, e.g. "Take me there". */
fun Modifier.ovioPrimaryAction(corner: Dp = 16.dp): Modifier {
    val shape = RoundedCornerShape(corner)
    return this
        .clip(shape)
        .background(OvioColors.Accent.copy(alpha = 0.16f), shape)
        .border(0.5.dp, OvioColors.Accent.copy(alpha = 0.45f), shape)
}

/** Anything that isn't the point of the screen: skip, cancel, share. */
fun Modifier.ovioSecondaryAction(corner: Dp = 16.dp): Modifier {
    val shape = RoundedCornerShape(corner)
    return this
        .clip(shape)
        .background(Color.White.copy(alpha = 0.06f), shape)
        .border(0.5.dp, Color.White.copy(alpha = 0.10f), shape)
}

// The dark "well" inside every elevated card: darker than the page, so the rim reads as light.
private val Well = Brush.verticalGradient(
    0.00f to Color(0xFF1B1610),
    0.42f to Color(0xFF100D0A),
    1.00f to Color(0xFF080605),
)

private fun rim(level: Elevation): Brush = when (level) {
    Elevation.Primary -> Brush.verticalGradient(
        0.00f to Color(0xFFFFE6B3).copy(alpha = 0.95f),
        0.10f to Color(0xFFDBB880).copy(alpha = 0.66f),
        0.44f to OvioColors.Bronze.copy(alpha = 0.34f),
        1.00f to Color(0xFF75542B).copy(alpha = 0.58f),
    )
    Elevation.Secondary -> Brush.verticalGradient(
        0.00f to Color(0xFFFFE6B3).copy(alpha = 0.42f),
        0.14f to Color(0xFFDBB880).copy(alpha = 0.28f),
        0.50f to OvioColors.Bronze.copy(alpha = 0.15f),
        1.00f to Color(0xFF75542B).copy(alpha = 0.22f),
    )
    Elevation.Quiet -> Brush.verticalGradient(
        0.0f to Color.White.copy(alpha = 0.10f),
        0.5f to Color.White.copy(alpha = 0.05f),
        1.0f to Color.White.copy(alpha = 0.03f),
    )
}

/** The main card treatment. Primary cards get a gold rim and a soft gold glow. */
fun Modifier.ovioElevated(level: Elevation, corner: Dp = 16.dp): Modifier {
    val shape = RoundedCornerShape(corner)
    val glow = if (level == Elevation.Primary) {
        Modifier.shadow(
            elevation = 18.dp,
            shape = shape,
            ambientColor = OvioColors.WarmGold.copy(alpha = 0.35f),
            spotColor = OvioColors.WarmGold.copy(alpha = 0.45f),
        )
    } else {
        Modifier
    }
    return this
        .then(glow)
        .clip(shape)
        .background(Well, shape)
        .border(if (level == Elevation.Quiet) 0.5.dp else 1.dp, rim(level), shape)
}
