package com.toquete.boxbox.core.common

interface Syncable {

    suspend fun sync(): Boolean
}