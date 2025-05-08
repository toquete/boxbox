package com.toquete.boxbox

import android.app.Application
import com.toquete.boxbox.core.database.di.databaseModule
import com.toquete.boxbox.core.network.di.networkModule
import com.toquete.boxbox.core.notification.di.notificationModule
import com.toquete.boxbox.core.preferences.di.preferencesModule
import com.toquete.boxbox.data.circuitimages.di.circuitImageDataModule
import com.toquete.boxbox.data.constructorcolors.di.constructorColorDataModule
import com.toquete.boxbox.data.constructorimages.di.constructorImageDataModule
import com.toquete.boxbox.data.constructorstandings.di.constructorStandingsDataModule
import com.toquete.boxbox.data.countries.di.countriesDataModule
import com.toquete.boxbox.data.driverimages.di.driverImagesDataModule
import com.toquete.boxbox.data.driverstandings.di.driverStandingsDataModule
import com.toquete.boxbox.data.raceresults.di.raceResultsDataModule
import com.toquete.boxbox.data.races.di.racesDataModule
import com.toquete.boxbox.data.sprintresults.di.sprintResultsDataModule
import com.toquete.boxbox.di.appModule
import com.toquete.boxbox.di.buildVariantModule
import com.toquete.boxbox.di.testModule
import com.toquete.boxbox.domain.di.domainModule
import com.toquete.boxbox.feature.home.di.homeFeatureModule
import com.toquete.boxbox.feature.raceresults.di.raceResultsFeatureModule
import com.toquete.boxbox.feature.races.di.racesFeatureModule
import com.toquete.boxbox.feature.settings.di.settingsFeatureModule
import com.toquete.boxbox.feature.standings.di.standingsFeatureModule
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
                homeFeatureModule,
                raceResultsFeatureModule,
                racesFeatureModule,
                settingsFeatureModule,
                standingsFeatureModule,
                domainModule,
                circuitImageDataModule,
                constructorColorDataModule,
                constructorImageDataModule,
                constructorStandingsDataModule,
                countriesDataModule,
                driverImagesDataModule,
                driverStandingsDataModule,
                raceResultsDataModule,
                racesDataModule,
                sprintResultsDataModule,
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
