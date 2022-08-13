package com.toquete.boxbox.flags.domain.repository

import com.toquete.boxbox.flags.domain.model.Flag
import kotlinx.coroutines.flow.Flow

interface FlagsRepository {

    fun getFlagByDemonym(demonym: String) : Flow<Flag>
}