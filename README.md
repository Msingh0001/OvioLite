# OVIO Lite: Android starter project

A small Kotlin + Jetpack Compose app that looks like **OVIO**, a calm-places discovery app.
You saw the full iOS version in class. This is a simplified version that **you** get to finish.

- ✅ Runs offline: no internet, no API keys, no accounts
- ✅ Same architecture as the real app: **Model → Repository → ViewModel → Compose UI**
- ✅ Same look: OVIO's warm gold palette, glass cards, the breathing score circle

---

## 1. Run it (5 minutes)

1. Install a recent **Android Studio** (2025 release or newer).
2. Unzip this folder, then in Android Studio choose **File → Open** and select the `OvioLite` folder.
3. Wait for **Gradle sync** to finish (the first time can take a few minutes; it downloads libraries).
4. Pick an emulator (e.g. *Pixel 8, API 34+*) or plug in your phone, then press the green **▶ Run** button.

You should see the OVIO home screen with a mountain wallpaper, mood chips, and a list of calm places.

> **Sync failed?** Android Studio will usually offer a one-click fix ("Upgrade Gradle plugin" or "Install missing SDK"). Accept it.
> If it says the Android Gradle Plugin is too new, update Android Studio.

---

## 2. Find your way around

```
app/src/main/java/com/example/oviolite/
├── MainActivity.kt            ← entry point, start reading here
├── data/
│   ├── Venue.kt               ← the data model + Mood enum
│   └── VenueRepository.kt     ← fake data (in the real app, a network call)
└── ui/
    ├── VenueViewModel.kt      ← state (UiState) + the functions that change it
    ├── OvioApp.kt             ← tabs + which screen is showing
    ├── screens/               ← Home, Detail, Sanctuaries, Coming-soon
    ├── components/            ← ScoreCircle, MoodChips, VenueCard, OvioTabBar
    └── theme/                 ← colours, fonts, the "glass" look (ported from iOS)
```

**The one idea to understand:** the UI never changes data directly.

```
User taps ♡  →  viewModel.toggleSave(id)  →  new UiState  →  Compose redraws by itself
```

---

## 3. Your exercises

Open **View → Tool Windows → TODO** in Android Studio to jump straight to each one.

| # | File | What to do | Difficulty |
|---|------|------------|------------|
| 1 | `VenueViewModel.kt` | Make the ♡ save button work. Then check the Sanctuaries tab. | ⭐ |
| 2 | `VenueViewModel.kt` | Sort places so the calmest comes first. | ⭐ |
| 3 | `Venue.kt` + `VenueRepository.kt` | Add a new mood, **reset**, and tag some places with it. | ⭐⭐ |
| 4 | `ScoreCircle.kt` | **Honest scores:** show `?` instead of `0` when there's no data. | ⭐⭐ |
| 5 | `DetailScreen.kt` | *(Stretch)* "Take me there" opens Google Maps. | ⭐⭐⭐ |

**Bonus ideas:** build the Discover tab as a grid, add your own fake venues from your city,
or make a Profile screen where users choose their favourite moods.

---

## 4. Why TODO 4 matters

Look at *Second Chapter Books*. We have **no crowd data** for it, so `calmScore` is `null`.
Right now the app shows **0**, which tells the user the place is packed. That's a lie.

OVIO's core product rule: **showing "?" is better than a made-up number.**
Users only trust a score if it's honest when it doesn't know.
Good apps are as much about honesty as about code.

---

## 5. SwiftUI ↔ Compose cheat sheet

If you're curious how the iOS version maps to this one:

| SwiftUI (iOS) | Jetpack Compose (Android) |
|---|---|
| `VStack` / `HStack` / `ZStack` | `Column` / `Row` / `Box` |
| `@State var x` | `var x by remember { mutableStateOf(...) }` |
| `ObservableObject` + `@Published` | `ViewModel` + `StateFlow` |
| `@StateObject var vm` | `viewModel()` + `collectAsStateWithLifecycle()` |
| `List` / `ForEach` | `LazyColumn` / `items(...)` |
| `.task { }` | `LaunchedEffect(Unit) { }` |
| `Color(hex: 0x14100A)` | `Color(0xFF14100A)` |
| `.overlay(RoundedRectangle().strokeBorder(...))` | `Modifier.border(width, color, shape)` |
| `Path` + `addQuadCurve` | `Path` + `quadraticBezierTo` |

Have fun, and find your calm. 🌲
