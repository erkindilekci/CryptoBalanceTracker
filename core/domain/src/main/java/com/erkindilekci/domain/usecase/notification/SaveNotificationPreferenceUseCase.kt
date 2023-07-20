package com.erkindilekci.domain.usecase.notification

import com.erkindilekci.domain.repository.DataStoreRepository
import javax.inject.Inject

class SaveNotificationPreferenceUseCase @Inject constructor(
    private val notificationRepository: DataStoreRepository
) {
    suspend operator fun invoke(isActive: Boolean) =
        notificationRepository.saveNotificationPreference(isActive)
}
