# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Overview

BoxBox is an offline-first Android Formula 1 companion app that fetches data from the jolpica API (successor to Ergast) and stores it locally in Room. It uses Jetpack Compose, Hilt, ViewModel, WorkManager, and DataStore.

## Build Commands

```bash
# Build debug variant
./gradlew assembleProdDebug

# Build demo flavor
./gradlew assembleDemoDebug

# Run all unit tests
./gradlew testProdDebugUnitTest

# Run unit tests for a specific module
./gradlew :feature:standings:testProdDebugUnitTest

# Run a single test class
./gradlew :feature:standings:testProdDebugUnitTest --tests "com.toquete.boxbox.feature.standings.drivers.DriverStandingsViewModelTest"

# Lint
./gradlew :app:lintProdDebug

# Static analysis (detekt)
./gradlew detekt

# Code coverage report
./gradlew koverHtmlReportProdDebug

# Clean
./gradlew clean
```

## Build Flavors & Variants

Two product flavors:
- `prod` (default) — production flavor, full app
- `demo` — adds `.demo` application ID suffix and `-DEMO` version suffix

Three build types:
- `debug` — debuggable, no minification, adds `.debug` suffix
- `minified` — like debug but with R8/ProGuard enabled, adds `.minified` suffix
- `release` — signed release build with full minification

Common active variants: `prodDebug`, `demoDebug`, `prodRelease`.

## Module Architecture

The project is fully modularized. Module groups:

- **`:app`** — application entry point, `MainActivity`, `MainViewModel`, `BoxBoxNavHost`, and the `feature/home` scaffolding (bottom nav + top app bar) that was recently moved here from a deleted `:feature:home` module.
- **`:core:*`** — foundational libraries with no feature logic: `model` (domain models), `database` (Room), `network` (Retrofit/API client), `preferences` (DataStore), `ui` (theme, shared composables), `common`, `navigation` (`BoxBoxRoute` sealed interface), `testing` (shared fakes, `MainDispatcherRule`, test data), `alarm`, `notification`.
- **`:data:*`** — repository implementations that bridge `:core:network` ↔ `:core:database`. Each data module owns its network model, entity mappers, and a `DefaultXxxRepository`. Modules: `driverstandings`, `constructorstandings`, `driverimages`, `constructorimages`, `constructorcolors`, `circuitimages`, `races`, `raceresults`, `sprintresults`, `countries`.
- **`:domain`** — repository interfaces (e.g. `DriverStandingsRepository`) and use cases. Feature modules depend only on `:domain`, never directly on `:data:*`.
- **`:feature:*`** — UI modules: `standings`, `races`, `raceresults`, `settings`. Each exposes a `navigation/XxxNavigation.kt` with extension functions for `NavGraphBuilder` and `NavController`.
- **`:sync`** — `SyncWorker` (WorkManager) that orchestrates calling `sync()` on all data repositories via the domain layer.
- **`:plugins`** — convention Gradle plugins (`boxbox.android.application`, `boxbox.android.library`, `boxbox.android.hilt`, `boxbox.android.feature`, etc.) applied in module `build.gradle.kts` files.
- **`:baselineprofile`** — Baseline profile generation module.

## Navigation

Routes are defined as a `@Serializable sealed interface BoxBoxRoute` in `:core:navigation`. The root `BoxBoxNavHost` in `:app` registers all top-level feature screens. The home scaffold (bottom nav between Standings / Races) lives in `app/src/main/java/.../feature/home/`.

## Data Flow (offline-first)

1. On app start, `SyncWorker` is enqueued via WorkManager.
2. `SyncWorker` calls `sync()` on each repository (defined in `:domain`), which fetches from the jolpica API and upserts into Room.
3. Feature ViewModels observe `Flow`s from repository interfaces (`:domain`) which read from Room DAOs.
4. ViewModels use `stateIn(SharingStarted.WhileSubscribed(5_000))` pattern uniformly.

## Convention Plugins

Instead of per-module Gradle boilerplate, convention plugins in `:plugins` configure:
- All modules get `prod`/`demo` flavors automatically.
- `boxbox.android.feature` — applies `boxbox.android.library` + Hilt + adds `core-ktx` and Coil.
- `boxbox.android.hilt` — applies Hilt plugin + KSP.
- `boxbox.android.library` — applies AGP library, Kotlin Android, Kover, flavors, Gradle managed devices.

## Testing Conventions

- Unit tests use **MockK** for mocking and `runTest` for coroutines.
- `MainDispatcherRule` (in `:core:testing`) must be applied as a `@get:Rule` in any ViewModel test.
- Shared test data objects live in `:core:testing` (e.g. `driverStandings`, `constructorStandings`).
- All modules use Robolectric for unit tests that require Android resources (`isIncludeAndroidResources = true`).
- Coverage is measured via **Kover** on the `prodDebug` variant, excluding Hilt/DI, Composable, Navigation, and generated classes.
- `FixedClock` (in `domain/src/test/.../domain/util/`) is available for use case tests that depend on `Clock`/date — no need to create a new fake.

## Static Analysis

**Detekt** runs on all subprojects (ignores `release` and `minified` build types). Config is at `detekt/config.yml`. Auto-correct is enabled. Merged report outputs to `build/reports/detekt-results.xml`.
- Max line length enforced by detekt is **120 characters**.

## Network / API Layer

- Base URL: `https://api.jolpi.ca/ergast/f1/` (defined in `core/network/.../di/NetworkModule.kt`).
- Endpoint path reference: https://github.com/jolpica/jolpica-f1/tree/main/docs/endpoints/
- All endpoints follow `{season}/<resource>.json` pattern (e.g., `{season}/driverStandings.json`).
- The old Ergast shorthand `current.json` (races) is not valid in jolpica; use `{season}/races.json`.
- When adding `@Path` params to existing no-arg service functions, use default values (`= "current"`) to avoid breaking callers.
- Each `:data:*` remote data source has both `prod/` and `demo/` source sets — check both when modifying service method signatures.
- When inserting a new `@Path` param before existing `@Query` params, positional callers break; use named arguments at call sites (e.g., `service.getFoo(offset = offset)`).

## Key Property Files

- `keystore.properties` — signing config for release builds (not committed).
- `build.properties` — contains `APP_CHECK_DEBUG_TOKEN` for Firebase App Check in debug.