package com.erkindilekci.signin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erkindilekci.domain.usecase.auth.SignInUseCase
import com.erkindilekci.util.Response
import com.google.firebase.auth.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase
) : ViewModel() {

    private val _signInFlow = MutableSharedFlow<Response<AuthResult>>()
    val signInFlow = _signInFlow

    var emailState by  mutableStateOf("")
        private set
    var passwordState by  mutableStateOf("")
        private set

    fun signIn() = viewModelScope.launch {
        signInUseCase.invoke(emailState, passwordState).collect { response ->
            _signInFlow.emit(response)
        }
    }

    fun updateEmailState(str: String) {
        emailState = str
    }

    fun updatePasswordState(str: String) {
        passwordState = str
    }
}
