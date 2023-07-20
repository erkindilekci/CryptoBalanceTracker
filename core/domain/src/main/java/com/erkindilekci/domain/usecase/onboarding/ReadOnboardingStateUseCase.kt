package com.erkindilekci.domain.usecase.onboarding

import com.erkindilekci.domain.repository.DataStoreRepository
import javax.inject.Inject

class ReadOnboardingStateUseCase @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) {
    suspend operator fun invoke() = dataStoreRepository.readOnboardingState()
}
