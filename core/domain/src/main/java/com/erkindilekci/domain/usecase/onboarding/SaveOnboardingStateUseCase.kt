package com.erkindilekci.domain.usecase.onboarding

import com.erkindilekci.domain.repository.DataStoreRepository
import javax.inject.Inject

class SaveOnboardingStateUseCase @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) {
    suspend operator fun invoke(isCompleted: Boolean) = dataStoreRepository.saveOnboardingState(isCompleted)
}
