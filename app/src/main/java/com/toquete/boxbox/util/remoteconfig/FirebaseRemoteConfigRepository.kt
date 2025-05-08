package com.toquete.boxbox.util.remoteconfig

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.get
import com.toquete.boxbox.core.common.annotation.Generated
import com.toquete.boxbox.core.model.RemoteConfigs
import com.toquete.boxbox.domain.repository.RemoteConfigRepository
import com.toquete.boxbox.util.remoteconfig.RemoteConfigKeys.IS_AD_BANNER_VISIBLE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

@Generated
class FirebaseRemoteConfigRepository(
    private val firebaseRemoteConfig: FirebaseRemoteConfig,
    private val dispatcher: CoroutineContext = Dispatchers.IO
) : RemoteConfigRepository {

    override val remoteConfigs: Flow<RemoteConfigs> = flow {
        emit(
            RemoteConfigs(isAdBannerVisible = firebaseRemoteConfig[IS_AD_BANNER_VISIBLE].asBoolean())
        )
    }

    override fun fetchAndActivate(): Flow<Boolean> = callbackFlow {
        firebaseRemoteConfig.fetchAndActivate()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    trySend(true)
                } else {
                    Timber.e(task.exception, "Failed to fetch and activate remote config")
                    trySend(false)
                }
                close(task.exception)
            }
        awaitClose()
    }.flowOn(dispatcher)
}
