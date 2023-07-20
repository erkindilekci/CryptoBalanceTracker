package com.erkindilekci.domain.repository

import com.erkindilekci.domain.model.FavoritesEntity

interface FavoritesRepository {

    suspend fun insertFavorite(favorite: FavoritesEntity)

    suspend fun deleteFavorite(favorite: FavoritesEntity)

    suspend fun getFavorites(): List<FavoritesEntity>
}
