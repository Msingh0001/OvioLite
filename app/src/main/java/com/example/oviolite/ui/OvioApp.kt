package com.example.oviolite.ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.oviolite.ui.components.OvioTab
import com.example.oviolite.ui.components.OvioTabBar
import com.example.oviolite.ui.screens.ComingSoonScreen
import com.example.oviolite.ui.screens.DetailScreen
import com.example.oviolite.ui.screens.HomeScreen
import com.example.oviolite.ui.screens.SanctuariesScreen
import com.example.oviolite.ui.theme.OvioColors

/**
 * The app's "shell": which tab is showing, whether a venue is open, and the tab bar.
 *
 * We keep navigation as simple state (no navigation library) so you can see
 * exactly how it works: change a variable → Compose shows a different screen.
 */
@Composable
fun OvioApp(viewModel: VenueViewModel = viewModel()) {
    // Collect the ViewModel's StateFlow. Every new UiState triggers a redraw.
    val state by viewModel.state.collectAsStateWithLifecycle()

    // rememberSaveable survives screen rotation (plain remember would not).
    var tab by rememberSaveable { mutableStateOf(OvioTab.Home) }
    var openVenueId by rememberSaveable { mutableStateOf<String?>(null) }

    // The system back gesture closes the detail screen instead of quitting the app.
    BackHandler(enabled = openVenueId != null) { openVenueId = null }

    Box(
        Modifier
            .fillMaxSize()
            .background(OvioColors.Background)
    ) {
        // AnimatedContent cross-fades whenever the (venue, tab) pair changes.
        AnimatedContent(
            targetState = openVenueId to tab,
            transitionSpec = { fadeIn() togetherWith fadeOut() },
            label = "screen",
        ) { (venueId, shownTab) ->
            val openVenue = venueId?.let { viewModel.venue(it) }
            if (openVenue != null) {
                DetailScreen(
                    venue = openVenue,
                    isSaved = openVenue.id in state.savedIds,
                    onToggleSave = { viewModel.toggleSave(openVenue.id) },
                    onBack = { openVenueId = null },
                )
            } else {
                when (shownTab) {
                    OvioTab.Home -> HomeScreen(
                        state = state,
                        onSelectMood = viewModel::selectMood,
                        onToggleSave = viewModel::toggleSave,
                        onOpenVenue = { openVenueId = it },
                    )
                    OvioTab.Sanctuaries -> SanctuariesScreen(
                        state = state,
                        onToggleSave = viewModel::toggleSave,
                        onOpenVenue = { openVenueId = it },
                    )
                    OvioTab.Discover -> ComingSoonScreen(
                        "Discover",
                        "Browse every place on a map or in a grid. Try a LazyVerticalGrid!",
                    )
                    OvioTab.Concierge -> ComingSoonScreen(
                        "OVIO Concierge",
                        "In the real app, you type what you need and an AI plans a calm outing for you.",
                    )
                    OvioTab.Profile -> ComingSoonScreen(
                        "Profile",
                        "Let users pick their favourite moods, and show those first on Home.",
                    )
                }
            }
        }

        OvioTabBar(
            selected = tab,
            onSelect = {
                tab = it
                openVenueId = null
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
                .padding(horizontal = 16.dp, vertical = 12.dp),
        )
    }
}
