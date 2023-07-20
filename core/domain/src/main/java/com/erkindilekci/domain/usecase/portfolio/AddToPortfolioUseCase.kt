package com.erkindilekci.domain.usecase.portfolio

import com.erkindilekci.domain.model.portfolio.PortfolioModel
import com.erkindilekci.domain.repository.AuthRepository
import com.erkindilekci.domain.repository.PortfolioRepository
import javax.inject.Inject

class AddToPortfolioUseCase @Inject constructor(
    private val portfolioRepository: PortfolioRepository,
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(
        portfolioModel: PortfolioModel
    ) {
        try {
            portfolioRepository.addToPortfolio(authRepository.userUid(), portfolioModel)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
