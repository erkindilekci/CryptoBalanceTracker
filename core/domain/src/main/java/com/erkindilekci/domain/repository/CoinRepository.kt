package com.erkindilekci.domain.repository

import com.erkindilekci.domain.model.coin.CoinResponse

interface CoinRepository {

    suspend fun getCoinList(): CoinResponse
}
