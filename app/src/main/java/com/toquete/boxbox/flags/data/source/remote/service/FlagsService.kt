package com.toquete.boxbox.flags.data.source.remote.service

import com.toquete.boxbox.flags.data.source.remote.model.FlagsDataResponse
import retrofit2.http.Field
import retrofit2.http.GET

interface FlagsService {

    @GET("name/{name}?=fields=demonym,flags")
    suspend fun getFlagInfoByName(@Field("name") name: String): List<FlagsDataResponse>
}