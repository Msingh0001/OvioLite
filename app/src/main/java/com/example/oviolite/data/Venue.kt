package com.example.oviolite.data

/*
 * MODEL LAYER: plain data, no UI code.
 *
 * In the real OVIO app these come from a FastAPI backend that combines
 * Google Places with live crowd forecasts. Here they're hard-coded so the
 * app runs with no internet and no API keys.
 */

/** What the user is in the mood for. Each venue is tagged with the moods it fits. */
enum class Mood(val label: String) {
    CALM("calm"),
    FOCUS("focus"),
    COZY("cozy"),

    // TODO 3: Add a new mood called RESET with the label "reset".
    //  Then open VenueRepository.kt and tag the park and the garden with it.
    //  The new chip should appear on the Home screen with no other changes. Why?
}

data class Venue(
    val id: String,
    val name: String,
    val category: String,         // "Tea house", "Library"...
    val neighborhood: String,
    val distance: String,         // already formatted, e.g. "0.8 mi"
    /**
     * How calm the place is right now, 0–100 (100 = calmest).
     * It's NULLABLE on purpose: sometimes we simply don't have crowd data
     * for a place. See TODO 4.
     */
    val calmScore: Int?,
    val quietWindow: String,      // when it's usually calmest
    val whyItFits: String,        // the one-line reason OVIO recommends it
    val moods: Set<Mood>,
)
