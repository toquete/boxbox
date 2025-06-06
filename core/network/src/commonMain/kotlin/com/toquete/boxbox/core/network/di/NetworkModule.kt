package com.toquete.boxbox.core.network.di

import com.toquete.boxbox.core.network.BoxBoxRemoteDatabase
import com.toquete.boxbox.core.network.BoxBoxService
import com.toquete.boxbox.core.network.KtorService
import com.toquete.boxbox.core.network.firebase.FirebaseDatabase
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.firestore.firestore
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal val commonModule = module {
    single<FirebaseFirestore> { Firebase.firestore }
    singleOf(::KtorService).bind<BoxBoxService>()
    singleOf(::FirebaseDatabase).bind<BoxBoxRemoteDatabase>()
}

val networkModule = module {
    includes(commonModule, platformModule)
}
