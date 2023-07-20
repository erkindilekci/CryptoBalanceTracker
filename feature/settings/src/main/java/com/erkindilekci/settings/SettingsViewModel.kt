package com.erkindilekci.settings

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erkindilekci.domain.model.user.UserModel
import com.erkindilekci.domain.usecase.auth.GetUserInfoUseCase
import com.erkindilekci.domain.usecase.auth.SignOutUseCase
import com.erkindilekci.domain.usecase.notification.ReadNotificationPreferenceUseCase
import com.erkindilekci.domain.usecase.notification.SaveNotificationPreferenceUseCase
import com.erkindilekci.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val signOutUseCase: SignOutUseCase,
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val saveNotificationPreferenceUseCase: SaveNotificationPreferenceUseCase,
    private val readNotificationPreferenceUseCase: ReadNotificationPreferenceUseCase
) : ViewModel() {

    private var _userInfoState = mutableStateOf(UserModel("", ""))
    val userInfoState = _userInfoState

    private var _notificationState = mutableStateOf(false)
    var notificationState = _notificationState

    init {
        loadUserInfo()
        readNotificationPreference()
    }

    fun signOut() {
        viewModelScope.launch {
            signOutUseCase.invoke()
        }
    }

    private fun loadUserInfo() {
        viewModelScope.launch {
            getUserInfoUseCase.invoke().collect {
                var userModel = UserModel("", "")
                when (it) {
                    is Response.Success -> {
                        it.data?.let { snapshot ->
                            for (i in snapshot) {
                                userModel =
                                    UserModel(
                                        i.get("name").toString(), i.get("email").toString()
                                    )
                            }
                        }
                        _userInfoState.value = userModel
                    }

                    is Response.Loading -> {}
                    is Response.Error -> {}
                }
            }
        }
    }

    fun saveNotificationPreference(isActive: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            saveNotificationPreferenceUseCase.invoke(isActive)
        }
    }

    private fun readNotificationPreference() {
        viewModelScope.launch {
            _notificationState.value = readNotificationPreferenceUseCase.invoke()
        }
    }
}
