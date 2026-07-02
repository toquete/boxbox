package com.toquete.boxbox

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.work.WorkerParameters
import com.toquete.boxbox.core.common.di.commonModule
import com.toquete.boxbox.core.database.di.databaseModule
import com.toquete.boxbox.core.network.di.networkModule
import com.toquete.boxbox.core.preferences.di.preferencesModule
import com.toquete.boxbox.data.circuitimages.di.circuitImagesModule
import com.toquete.boxbox.data.constructorcolors.di.constructorColorsModule
import com.toquete.boxbox.data.constructorimages.di.constructorImagesModule
import com.toquete.boxbox.data.constructorstandings.di.constructorStandingsModule
import com.toquete.boxbox.data.countries.di.countriesModule
import com.toquete.boxbox.data.driverimages.di.driverImagesModule
import com.toquete.boxbox.data.driverstandings.di.driverStandingsModule
import com.toquete.boxbox.data.raceresults.di.raceResultsModule
import com.toquete.boxbox.data.races.di.racesModule
import com.toquete.boxbox.data.sprintresults.di.sprintResultsModule
import com.toquete.boxbox.di.appCheckModule
import com.toquete.boxbox.di.treeModule
import com.toquete.boxbox.domain.di.domainModule
import com.toquete.boxbox.feature.raceresults.di.raceResultsFeatureModule
import com.toquete.boxbox.feature.races.di.racesFeatureModule
import com.toquete.boxbox.feature.settings.di.settingsModule
import com.toquete.boxbox.feature.standings.di.standingsModule
import com.toquete.boxbox.sync.di.syncModule
import org.junit.Test
import org.koin.dsl.module
import org.koin.test.verify.verify

class KoinModuleTest {

    // Firebase singletons (FirebaseAnalytics, FirebaseCrashlytics, FirebaseRemoteConfig) are
    // provided via lambda factories in appModule. Koin verify() walks their concrete constructors,
    // which contain internal GMS types that cannot be resolved statically. appModule is therefore
    // excluded from verify() — its wiring is exercised by instrumentation tests instead.
    private val verifiableModules = module {
        includes(
            commonModule,
            networkModule,
            databaseModule,
            preferencesModule,
            domainModule,
            driverStandingsModule,
            constructorStandingsModule,
            driverImagesModule,
            constructorImagesModule,
            constructorColorsModule,
            circuitImagesModule,
            racesModule,
            raceResultsModule,
            sprintResultsModule,
            countriesModule,
            syncModule,
            standingsModule,
            racesFeatureModule,
            raceResultsFeatureModule,
            settingsModule,
            appCheckModule,
            treeModule
        )
    }

    @Test
    fun verifyAllModules() {
        verifiableModules.verify(
            extraTypes = listOf(
                Context::class,
                WorkerParameters::class,
                SavedStateHandle::class
            )
        )
    }
}
