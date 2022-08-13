package com.toquete.boxbox.flags.domain.repository

import com.toquete.boxbox.flags.domain.model.Flag

interface FlagsRepository {

    suspend fun getFlagByDemonym(demonym: String) : Flag
}