package com.toquete.boxbox

import android.app.Application
import com.toquete.boxbox.core.database.di.databaseModule
import com.toquete.boxbox.core.network.di.networkModule
import com.toquete.boxbox.core.notification.di.notificationModule
import com.toquete.boxbox.core.preferences.di.preferencesModule
import com.toquete.boxbox.data.circuitimages.di.circuitImageModule
import com.toquete.boxbox.data.constructorcolors.di.constructorColorModule
import com.toquete.boxbox.data.constructorimages.di.constructorImageModule
import com.toquete.boxbox.data.constructorstandings.di.constructorStandingsModule
import com.toquete.boxbox.data.countries.di.countriesModule
import com.toquete.boxbox.data.driverimages.di.driverImagesModule
import com.toquete.boxbox.data.driverstandings.di.driverStandingsModule
import com.toquete.boxbox.data.raceresults.di.raceResultsModule
import com.toquete.boxbox.data.races.di.racesModule
import com.toquete.boxbox.data.sprintresults.di.sprintResultsModule
import com.toquete.boxbox.di.appModule
import com.toquete.boxbox.di.buildVariantModule
import com.toquete.boxbox.di.testModule
import com.toquete.boxbox.domain.di.domainModule
import com.toquete.boxbox.feature.home.di.homeModule
import com.toquete.boxbox.feature.raceresults.di.raceResultModule
import com.toquete.boxbox.feature.races.di.racesFeatureModule
import com.toquete.boxbox.feature.settings.di.settingsModule
import com.toquete.boxbox.feature.standings.di.standingsModule
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.context.startKoin

class TestApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TestApplication)
            modules(
                appModule,
                buildVariantModule,
                homeModule,
                raceResultModule,
                racesFeatureModule,
                settingsModule,
                standingsModule,
                domainModule,
                circuitImageModule,
                constructorColorModule,
                constructorImageModule,
                constructorStandingsModule,
                countriesModule,
                driverImagesModule,
                driverStandingsModule,
                raceResultsModule,
                racesModule,
                sprintResultsModule,
                databaseModule,
                networkModule,
                notificationModule,
                preferencesModule,
                testModule
            )
            workManagerFactory()
        }
    }
}
