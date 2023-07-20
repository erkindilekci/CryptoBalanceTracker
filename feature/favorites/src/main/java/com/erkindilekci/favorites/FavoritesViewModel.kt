package com.erkindilekci.favorites

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erkindilekci.domain.model.FavoritesEntity
import com.erkindilekci.domain.model.coin.CoinItem
import com.erkindilekci.domain.usecase.coin.GetCoinUseCase
import com.erkindilekci.domain.usecase.favorites.DeleteFavoriteUseCase
import com.erkindilekci.domain.usecase.favorites.GetFavoritesUseCase
import com.erkindilekci.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
    private val getCoinUseCase: GetCoinUseCase
) : ViewModel() {

    private var _coinList = mutableStateOf<List<CoinItem>>(listOf())
    var coinList = _coinList

    private var _favoriteList = mutableStateOf<List<FavoritesEntity>>(listOf())
    var favoriteList = _favoriteList

    private var _loadingState = mutableStateOf(false)
    var loadingState = _loadingState

    init {
        refreshData()
    }

    private fun refreshData() {
        loadFavorites()
        loadCoins()
    }

    /**
     * Loads favorites from RoomDB
     */
    private fun loadFavorites() {
        viewModelScope.launch(Dispatchers.IO) {
            getFavoritesUseCase.invoke().collect {
                withContext(Dispatchers.Main) {
                    when (it) {
                        is Response.Success -> {
                            _favoriteList.value = it.data
                        }

                        else -> {}
                    }
                }
            }
        }
    }

    private fun loadCoins() {
        val selectedList = ArrayList<CoinItem>()
        viewModelScope.launch(Dispatchers.IO) {
            getCoinUseCase.invoke().collect {
                withContext(Dispatchers.Main) {
                    when (it) {
                        is Response.Success -> {
                            it.data.data.forEach { coin ->
                                favoriteList.value.forEach { favorite ->
                                    if (coin.symbol == favorite.symbol) {
                                        selectedList.add(coin)
                                    }
                                }
                            }
                            _coinList.value = selectedList
                            loadingState.value = false
                        }

                        is Response.Loading -> {
                            loadingState.value = true
                        }

                        is Response.Error -> {
                            loadingState.value = false
                        }
                    }
                }
            }
        }
    }

    fun deleteFavorite(favorite: FavoritesEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteFavoriteUseCase.invoke(favorite)
            refreshData()
        }
    }
}
