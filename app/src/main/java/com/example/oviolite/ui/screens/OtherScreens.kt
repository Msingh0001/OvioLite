package com.example.oviolite.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.oviolite.ui.UiState
import com.example.oviolite.ui.components.VenueRow
import com.example.oviolite.ui.theme.Elevation
import com.example.oviolite.ui.theme.OvioColors
import com.example.oviolite.ui.theme.ovioElevated

/** Saved places. Reads state.savedVenues, so it fills in automatically once TODO 1 works. */
@Composable
fun SanctuariesScreen(
    state: UiState,
    onToggleSave: (String) -> Unit,
    onOpenVenue: (String) -> Unit,
) {
    val saved = state.savedVenues
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(OvioColors.Background)
            .statusBarsPadding(),
        contentPadding = PaddingValues(start = 20.dp, end = 20.dp, top = 28.dp, bottom = 120.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        item {
            Text("Sanctuaries", style = MaterialTheme.typography.headlineMedium, color = OvioColors.TextPrimary)
            Text(
                "Places you've kept for later.",
                style = MaterialTheme.typography.bodyMedium,
                color = OvioColors.TextMuted,
            )
            Spacer(Modifier.height(16.dp))
        }
        if (saved.isEmpty()) {
            item {
                Text(
                    "Nothing saved yet. Tap the ♡ on any place.\n(If tapping does nothing, that's TODO 1!)",
                    style = MaterialTheme.typography.bodyLarge,
                    color = OvioColors.TextSecondary,
                )
            }
        }
        items(saved, key = { it.id }) { venue ->
            VenueRow(
                venue = venue,
                isSaved = true,
                onToggleSave = { onToggleSave(venue.id) },
                onOpen = { onOpenVenue(venue.id) },
            )
        }
    }
}

/** Used for the tabs that aren't built yet: Discover, OVIO (concierge) and Profile. */
@Composable
fun ComingSoonScreen(title: String, idea: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(OvioColors.Background)
            .padding(24.dp),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .ovioElevated(Elevation.Secondary, corner = 20.dp)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(title, style = MaterialTheme.typography.titleLarge, color = OvioColors.TextPrimary)
            Spacer(Modifier.height(10.dp))
            Text(
                idea,
                style = MaterialTheme.typography.bodyLarge,
                color = OvioColors.TextSecondary,
                textAlign = TextAlign.Center,
            )
            Spacer(Modifier.height(14.dp))
            Text("YOUR TURN", style = MaterialTheme.typography.labelSmall, color = OvioColors.Champagne)
        }
    }
}
