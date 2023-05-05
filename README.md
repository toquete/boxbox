# BoxBox :racing_car:

BoxBox is an app for the Formula 1 fan, where you can find informations about the current season, the drivers' and constructor's standings and more!

It uses [Material Design 3](https://m3.material.io/) and follows the Android's [guide to app architecture](https://developer.android.com/topic/architecture) and [guide to modularization](https://developer.android.com/topic/modularization).

## Jetpack libraries

* Compose
* Hilt
* ViewModel
* Room
* WorkManager
* DataStore

## Architecture

You can check the modules' dependencies on each module README file.

## Data

The app is [offline-first](https://developer.android.com/topic/architecture/data-layer/offline-first). All data comes from the Ergast API and saved in the local database every time the app is opened.
