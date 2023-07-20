package com.erkindilekci.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erkindilekci.domain.model.FavoritesEntity
import com.erkindilekci.domain.model.portfolio.PortfolioModel
import com.erkindilekci.domain.usecase.favorites.AddFavoriteUseCase
import com.erkindilekci.domain.usecase.portfolio.AddToPortfolioUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val addToPortfolioUseCase: AddToPortfolioUseCase
) : ViewModel() {

    fun addToFavorite(favorite: FavoritesEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            addFavoriteUseCase.invoke(favorite)
        }
    }

    fun addToPortfolio(
        portfolioModel: PortfolioModel
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            addToPortfolioUseCase.invoke(portfolioModel)
        }
    }
}
