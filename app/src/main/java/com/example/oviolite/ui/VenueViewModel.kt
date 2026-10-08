package com.example.oviolite.ui

import androidx.lifecycle.ViewModel
import com.example.oviolite.data.Mood
import com.example.oviolite.data.Venue
import com.example.oviolite.data.VenueRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/*
 * VIEWMODEL: holds the screen's state and the rules for changing it.
 *
 * The flow, every time:
 *   1. The UI calls a function here       (selectMood, toggleSave)
 *   2. The function makes a NEW UiState   (_state.update { it.copy(...) })
 *   3. Compose sees the new state and redraws by itself
 *
 * The UI never changes state directly. It only asks.
 *
 * On iOS, the same idea is an ObservableObject with @Published properties.
 */

/** Everything the screens need to draw themselves, in one immutable object. */
data class UiState(
    val allVenues: List<Venue> = emptyList(),
    val selectedMood: Mood? = null,           // null = show everything
    val savedIds: Set<String> = emptySet(),
) {
    /** The venues to show for the current mood. Computed from the state, never stored. */
    val visibleVenues: List<Venue>
        get() {
            val filtered = if (selectedMood == null) allVenues
                           else allVenues.filter { selectedMood in it.moods }

            // TODO 2: Sort the list so the CALMEST place comes first.
            //  Hint: look up sortedByDescending { ... } in the Kotlin docs.
            //  Careful: calmScore can be null. Where should unknown places go?
            return filtered
        }

    val savedVenues: List<Venue>
        get() = allVenues.filter { it.id in savedIds }
}

class VenueViewModel(
    private val repository: VenueRepository = VenueRepository(),
) : ViewModel() {

    private val _state = MutableStateFlow(UiState(allVenues = repository.getVenues()))
    val state: StateFlow<UiState> = _state.asStateFlow()

    /** Tapping the selected chip again clears the filter. */
    fun selectMood(mood: Mood) {
        _state.update { current ->
            current.copy(selectedMood = if (current.selectedMood == mood) null else mood)
        }
    }

    fun toggleSave(venueId: String) {
        // TODO 1: Make the heart button work.
        //  If venueId is already in savedIds, remove it. Otherwise, add it.
        //  Use the same pattern as selectMood() above: _state.update { it.copy(...) }
        //  Kotlin hint: for a Set, `set + item` and `set - item` both return a NEW set.
        //  Once it works, check the "Sanctuaries" tab. It fills in on its own. Why?
    }

    fun venue(id: String): Venue? = _state.value.allVenues.find { it.id == id }
}
