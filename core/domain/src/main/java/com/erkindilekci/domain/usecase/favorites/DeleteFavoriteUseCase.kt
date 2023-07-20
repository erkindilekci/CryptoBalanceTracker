package com.erkindilekci.domain.usecase.favorites

import com.erkindilekci.domain.model.FavoritesEntity
import com.erkindilekci.domain.repository.FavoritesRepository
import javax.inject.Inject

class DeleteFavoriteUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) {
    suspend operator fun invoke(favorite: FavoritesEntity) {
        favoritesRepository.deleteFavorite(favorite)
    }
}
