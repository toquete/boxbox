package com.toquete.boxbox.flags.data.source

import com.toquete.boxbox.flags.data.source.remote.model.FlagsDataResponse
import kotlinx.coroutines.flow.Flow

interface FlagsDataSource {

    fun getFlagInfoByName(name: String): Flow<List<FlagsDataResponse>>
}