package com.erkindilekci.domain.usecase.notification

import com.erkindilekci.domain.repository.DataStoreRepository
import javax.inject.Inject

class ReadNotificationPreferenceUseCase @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) {
    suspend operator fun invoke() = dataStoreRepository.readNotificationPreference()
}
