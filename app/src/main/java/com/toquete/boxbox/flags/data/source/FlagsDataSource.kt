package com.toquete.boxbox.flags.data.source

import com.toquete.boxbox.flags.data.source.remote.model.FlagsDataResponse

interface FlagsDataSource {

    suspend fun getFlagInfoByName(name: String): List<FlagsDataResponse>
}