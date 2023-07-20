package com.erkindilekci.domain.usecase.coin

import com.erkindilekci.domain.model.coin.CoinResponse
import com.erkindilekci.domain.repository.CoinRepository
import com.erkindilekci.util.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(): Flow<Response<CoinResponse>> = flow {
        try {
            emit(Response.Loading)
            emit(Response.Success(repository.getCoinList()))
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "Unexpected Error!"))
        }
    }
}
