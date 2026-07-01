package com.toquete.boxbox

import android.app.Application
import com.toquete.boxbox.di.testModule
import com.toquete.boxbox.domain.di.domainModule
import com.toquete.boxbox.feature.raceresults.di.raceResultsFeatureModule
import com.toquete.boxbox.feature.races.di.racesFeatureModule
import com.toquete.boxbox.feature.settings.di.settingsModule
import com.toquete.boxbox.feature.standings.di.standingsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KoinTestApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@KoinTestApplication)
            modules(
                testModule,
                domainModule,
                standingsModule,
                racesFeatureModule,
                raceResultsFeatureModule,
                settingsModule
            )
        }
    }
}