package com.codelabs.wegot.ui.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codelabs.wegot.model.remote.network.ApiResponse
import com.codelabs.wegot.model.remote.response.auth.login.LoginResponse
import com.codelabs.wegot.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel() {

    private val _loginResult = MutableLiveData<ApiResponse<LoginResponse>>()
    val loginResult: LiveData<ApiResponse<LoginResponse>> = _loginResult

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun login(username: String, password: String) {
        viewModelScope.launch {
            loginRepository.login(username, password).collect { result ->
                when (result) {
                    is ApiResponse.Empty -> _isLoading.value = true
                    is ApiResponse.Success -> {
                        _isLoading.value = false
                        _loginResult.value = result
                    }
                    is ApiResponse.Error -> {
                        _isLoading.value = false
                        _loginResult.value = result
                    }
                }
            }
        }
    }

}