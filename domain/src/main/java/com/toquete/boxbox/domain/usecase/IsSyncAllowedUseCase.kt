package com.toquete.boxbox.domain.usecase

import com.toquete.boxbox.domain.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.datetime.DayOfWeek
import javax.inject.Inject

class IsSyncAllowedUseCase @Inject constructor(
    private val getTodayLocalDateUseCase: GetTodayLocalDateUseCase,
    private val userPreferencesRepository: UserPreferencesRepository
) {

    suspend operator fun invoke(): Boolean {
        val dayOfWeek = getTodayLocalDateUseCase().dayOfWeek
        val lastUpdatedDate = userPreferencesRepository.userPreferences.firstOrNull()?.lastUpdatedDateInMillis
        return isDayOfWeekAllowed(dayOfWeek) || lastUpdatedDate == null
    }

    private fun isDayOfWeekAllowed(dayOfWeek: DayOfWeek): Boolean {
        return dayOfWeek in setOf(DayOfWeek.SUNDAY, DayOfWeek.MONDAY)
    }
}
