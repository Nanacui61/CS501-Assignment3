# Assignment 3: Jetpack Compose UI Components

## Overview
This project contains solutions to five Jetpack Compose UI tasks.  
Each question is implemented in `MainActivity.kt` with separate composable functions.

---

## Q1: Row & Column Weight Split Layout
- A Row where one section takes 25% width and the other 75%.
- Inside the larger section, a Column with three weighted children (2:3:5).
- Colors and text labels are added to visualize space usage.

---

## Q2: Box Overlay with Badge
- A profile picture displayed using a Box.
- A badge is aligned to the bottom-end corner with `Modifier.align()`.
- A toggle button allows showing and hiding the badge.

---

## Q3: LazyColumn with Sticky Headers
- A contact list grouped alphabetically.
- `stickyHeader` keeps the current letter visible while scrolling.
- At least 50 sample contacts are generated.
- A Floating Action Button (FAB) appears when the user scrolls past item 10, and clicking it scrolls to the top using `animateScrollToItem()`.

---

## Q4: Scaffold with TopAppBar, BottomBar, and FAB
- A `Scaffold` layout is used.
- Top bar shows the app title.
- Bottom navigation bar with three items: Home, Settings, and Profile.
- A FAB triggers a Snackbar message when clicked.
- Proper inner padding is applied to avoid overlap with UI elements.

---

## Q5: Themed Form with TextFields and Submit Button
- A login form with username and password fields.
- Fields styled using Material 3 theme colors and typography.
- Validation ensures fields are not empty.
- If fields are empty, an error message is displayed.

