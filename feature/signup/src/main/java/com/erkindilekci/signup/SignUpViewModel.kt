package com.erkindilekci.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erkindilekci.domain.usecase.auth.SignUpUseCase
import com.erkindilekci.util.Response
import com.google.firebase.auth.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
) : ViewModel() {

    private var _signUpFlow = MutableSharedFlow<Response<AuthResult>>()
    val signUpFlow = _signUpFlow

    var nameState by  mutableStateOf("")
        private set
    var emailState by  mutableStateOf("")
        private set
    var passwordState by  mutableStateOf("")
        private set

    fun signUp() {
        viewModelScope.launch {
            signUpUseCase.invoke(nameState, emailState, passwordState).collect {
                _signUpFlow.emit(it)
            }
        }
    }

    fun updateNameState(str: String) {
        nameState = str
    }

    fun updateEmailState(str: String) {
        emailState = str
    }

    fun updatePasswordState(str: String) {
        passwordState = str
    }
}
