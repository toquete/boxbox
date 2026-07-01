package com.toquete.boxbox.sync.di

import com.toquete.boxbox.core.common.util.SyncMonitor
import com.toquete.boxbox.domain.repository.SyncRepository
import com.toquete.boxbox.sync.monitor.WorkManagerSyncMonitor
import com.toquete.boxbox.sync.repository.DefaultSyncRepository
import com.toquete.boxbox.sync.repository.ImagesRepository
import com.toquete.boxbox.sync.repository.StandingsRepository
import com.toquete.boxbox.sync.worker.SyncWorker
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.workmanager.dsl.workerOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val syncModule = module {
    workerOf(::SyncWorker)
    single<SyncMonitor> { WorkManagerSyncMonitor(androidContext()) }
    singleOf(::StandingsRepository)
    singleOf(::ImagesRepository)
    singleOf(::DefaultSyncRepository) bind SyncRepository::class
}
