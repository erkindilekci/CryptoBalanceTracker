package com.erkindilekci.data.repository

import com.erkindilekci.data.remote.ApiService
import com.erkindilekci.domain.model.coin.CoinResponse
import com.erkindilekci.domain.repository.CoinRepository

class CoinRepositoryImp(
    private val apiService: ApiService
) : CoinRepository {

    override suspend fun getCoinList(): CoinResponse {
        return apiService.getCoinList()
    }
}
