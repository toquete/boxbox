package com.toquete.boxbox.core.common.util

interface Syncable {

    suspend fun sync(): Boolean
}
