package com.erkindilekci.data.repository

import com.erkindilekci.data.local.FavoritesDao
import com.erkindilekci.domain.model.FavoritesEntity
import com.erkindilekci.domain.repository.FavoritesRepository

class FavoritesRepositoryImp(private val dao: FavoritesDao) :
    FavoritesRepository {

    override suspend fun insertFavorite(favorite: FavoritesEntity) {
        dao.insertFavorite(favorite)
    }

    override suspend fun deleteFavorite(favorite: FavoritesEntity) {
        dao.deleteFavorite(favorite)
    }

    override suspend fun getFavorites(): List<FavoritesEntity> {
        return dao.getFavoritesList()
    }
}
