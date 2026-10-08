package com.example.oviolite

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.oviolite.ui.OvioApp
import com.example.oviolite.ui.theme.OvioTheme

/*
 * Start here. 👋
 *
 * How the project is organised (the same layers as the real OVIO app):
 *
 *   data/        Venue.kt, VenueRepository.kt     ← WHAT the data is, WHERE it comes from
 *   ui/          VenueViewModel.kt                ← the state + the rules for changing it
 *   ui/screens/  HomeScreen, DetailScreen...      ← what the user sees
 *   ui/components/ ScoreCircle, MoodChips...      ← small reusable pieces
 *   ui/theme/    colours, fonts, the glass look   ← ported from the iOS app
 *
 * Your exercises are marked "TODO 1" to "TODO 5".
 * In Android Studio: View → Tool Windows → TODO lists them all.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // draw behind the status bar, like the iOS wallpaper does
        setContent {
            OvioTheme {
                OvioApp()
            }
        }
    }
}
