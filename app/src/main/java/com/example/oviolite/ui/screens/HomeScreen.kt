package com.example.oviolite.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.oviolite.R
import com.example.oviolite.data.Mood
import com.example.oviolite.ui.UiState
import com.example.oviolite.ui.components.HeroVenueCard
import com.example.oviolite.ui.components.MoodChips
import com.example.oviolite.ui.components.SectionLabel
import com.example.oviolite.ui.components.VenueRow
import com.example.oviolite.ui.theme.OvioColors

/**
 * Home: wallpaper, the OVIO wordmark, mood chips, one hero pick, then the rest.
 *
 * Notice this screen takes plain data (state) and lambdas (onSelectMood...).
 * It doesn't know the ViewModel exists. That makes it easy to preview and test.
 */
@Composable
fun HomeScreen(
    state: UiState,
    onSelectMood: (Mood) -> Unit,
    onToggleSave: (String) -> Unit,
    onOpenVenue: (String) -> Unit,
) {
    val venues = state.visibleVenues
    val hero = venues.firstOrNull()
    val rest = venues.drop(1)

    Box(Modifier.fillMaxSize()) {
        // 1. The wallpaper (same image as the iOS app), with a dark scrim so text stays readable.
        Image(
            painter = painterResource(R.drawable.wallpaper_forest),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )
        Box(
            Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        0.00f to Color.Black.copy(alpha = 0.10f),
                        0.35f to OvioColors.Ground.copy(alpha = 0.45f),
                        0.65f to OvioColors.Ground.copy(alpha = 0.85f),
                        1.00f to OvioColors.Ground,
                    )
                )
        )

        // 2. The content. LazyColumn only builds the rows that are on screen,
        //    like a RecyclerView (or a SwiftUI List).
        LazyColumn(
            modifier = Modifier.fillMaxSize().statusBarsPadding(),
            contentPadding = PaddingValues(start = 20.dp, end = 20.dp, top = 28.dp, bottom = 120.dp),
        ) {
            item {
                Text(
                    "OVIO",
                    style = MaterialTheme.typography.displayLarge.copy(
                        brush = OvioColors.Wordmark,
                        shadow = Shadow(Color.Black.copy(alpha = 0.5f), blurRadius = 22f),
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    "Find your calm.",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        shadow = Shadow(Color.Black.copy(alpha = 0.55f), blurRadius = 26f),
                    ),
                    color = OvioColors.WarmIvory,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                )
                Spacer(Modifier.height(140.dp)) // let the mountains breathe
                MoodChips(selected = state.selectedMood, onSelect = onSelectMood)
                Spacer(Modifier.height(22.dp))
            }

            if (hero == null) {
                item {
                    Text(
                        "Nothing matches that mood yet.",
                        style = MaterialTheme.typography.bodyLarge,
                        color = OvioColors.TextSecondary,
                    )
                }
            } else {
                item {
                    SectionLabel(if (state.selectedMood == null) "Tonight's pick" else "Best for ${state.selectedMood.label}")
                    Spacer(Modifier.height(12.dp))
                    HeroVenueCard(
                        venue = hero,
                        isSaved = hero.id in state.savedIds,
                        onToggleSave = { onToggleSave(hero.id) },
                        onOpen = { onOpenVenue(hero.id) },
                    )
                    Spacer(Modifier.height(26.dp))
                    if (rest.isNotEmpty()) {
                        SectionLabel("More calm spots")
                        Spacer(Modifier.height(12.dp))
                    }
                }
                // `key` helps Compose keep each row's identity when the list re-sorts.
                items(rest, key = { it.id }) { venue ->
                    VenueRow(
                        venue = venue,
                        isSaved = venue.id in state.savedIds,
                        onToggleSave = { onToggleSave(venue.id) },
                        onOpen = { onOpenVenue(venue.id) },
                        modifier = Modifier.padding(bottom = 10.dp),
                    )
                }
            }
        }
    }
}
