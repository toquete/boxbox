package com.toquete.boxbox.flags.data.source.remote.service

import com.toquete.boxbox.flags.data.source.remote.model.FlagsDataResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface FlagsService {

    @GET("demonym/{name}?=fields=demonyms,flags")
    suspend fun getFlagInfoByName(@Path("name") name: String): List<FlagsDataResponse>
}