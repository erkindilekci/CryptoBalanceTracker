package com.erkindilekci.onboarding

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erkindilekci.domain.usecase.auth.IsLoggedInUseCase
import com.erkindilekci.domain.usecase.onboarding.ReadOnboardingStateUseCase
import com.erkindilekci.domain.usecase.onboarding.SaveOnboardingStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val isLoggedInUseCase: IsLoggedInUseCase,
    private val saveOnboardingStateUseCase: SaveOnboardingStateUseCase,
    private val readOnboardingStateUseCase: ReadOnboardingStateUseCase
) : ViewModel() {

    private var _isLoggedInState = mutableStateOf(false)
    val isLoggedInState = _isLoggedInState

    var isOnBoardingCompleted = mutableStateOf(false)
        private set

    init {
        readOnBoardingState()
        isLoggedIn()

    }

    private fun isLoggedIn() {
        viewModelScope.launch {
            isLoggedInUseCase.invoke().collect {
                _isLoggedInState.value = it
            }
        }
    }

    fun saveOnBoardingState(completed: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            saveOnboardingStateUseCase(completed)
        }
    }

    private fun readOnBoardingState() {
        viewModelScope.launch(Dispatchers.Main) {
            isOnBoardingCompleted.value = readOnboardingStateUseCase()
        }
    }
}
