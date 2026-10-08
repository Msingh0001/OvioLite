package com.example.oviolite.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

/*
 * OVIO's colour system, ported 1:1 from the iOS app (LovaPalette.swift + OvioGold.swift).
 *
 * The whole app uses ONE warm colour family: espresso, parchment, champagne, bronze.
 * There's no green or red anywhere. Hierarchy comes from brightness, not from new hues.
 *
 * SwiftUI → Compose:  Color(hex: 0x14100A)  →  Color(0xFF14100A)
 *                     (the "FF" in front is the alpha channel: fully opaque)
 */
object OvioColors {

    // ── Grounds (backgrounds) ───────────────────────────────
    val Background = Color(0xFF14100A)
    val Ground = Color(0xFF090806)        // sheets and full screens
    val TabBar = Color(0xFF0E0C0A)        // the floating tab bar capsule

    // ── Text ladder (brightest → faintest) ──────────────────
    val TextPrimary = Color(0xFFF5E8D0)   // headlines, venue names
    val TextBody = Color(0xFFE4D6BE)      // prose the user reads
    val TextSecondary = Color(0xFFBCAA8E) // supporting copy
    val TextMuted = Color(0xFFB3A087)     // labels, units
    val TextFaint = Color(0xFF93836B)     // incidental

    // ── The gold ramp (pick by what the colour is DOING) ────
    val Highlight = Color(0xFFFACC8C)     // glints only, never text
    val Champagne = Color(0xFFCCB38F)     // THE gold: selected state, accents
    val WarmGold = Color(0xFFD1944D)      // glows and shadows
    val Bronze = Color(0xFF917A5C)        // borders, dividers
    val BronzeDeep = Color(0xFF704521)    // darkest gradient stop
    val WarmIvory = Color(0xFFE8E0D1)     // labels beside a gold icon
    val Accent = Color(0xFFD1A24A)        // primary action tint

    /** The "OVIO" wordmark gradient on the home screen. */
    val Wordmark = Brush.verticalGradient(
        listOf(WarmIvory.copy(alpha = 0.96f), Champagne.copy(alpha = 0.90f), BronzeDeep.copy(alpha = 0.80f))
    )

    /**
     * Calm score → colour. Calm is PALE, busy is SATURATED.
     * Same hue family the whole way, so it still reads for colour-blind users.
     */
    fun scoreColor(score: Int): Color = when (score.coerceIn(0, 100)) {
        in 90..100 -> Color(0xFFE9D9AE) // pale champagne: calmest
        in 80..89 -> Color(0xFFDFC894)
        in 70..79 -> Color(0xFFD5B778)
        in 60..69 -> Color(0xFFCBA65F)
        in 50..59 -> Color(0xFFC59450)
        in 40..49 -> Color(0xFFC08245)
        in 30..39 -> Color(0xFFBC6F3D)
        in 20..29 -> Color(0xFFB85C36)
        else -> Color(0xFFB44A30)       // deep terracotta: busiest
    }
}
