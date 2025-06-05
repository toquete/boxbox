package com.toquete.boxbox.core.common

import com.toquete.boxbox.core.common.extension.toDayString
import com.toquete.boxbox.core.common.extension.toShortMonthString
import kotlinx.datetime.Instant
import kotlin.test.Test
import kotlin.test.assertEquals

class InstantExtTest {

    @Test
    fun `toShortMonthString should return short month from Instant`() {
        // Given
        val instant = Instant.fromEpochMilliseconds(1672531199000)

        // When
        val result = instant.toShortMonthString()

        // Then
        assertEquals("DEC", result)
    }

    @Test
    fun `toDayString should return day from Instant`() {
        // Given
        val instant = Instant.fromEpochMilliseconds(1672531199000)

        // When
        val result = instant.toDayString()

        // Then
        assertEquals("31", result)
    }
}
