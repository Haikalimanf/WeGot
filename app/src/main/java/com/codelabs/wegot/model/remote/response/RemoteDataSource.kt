package com.codelabs.wegot.model.remote.response

import android.util.Log
import com.codelabs.wegot.model.remote.body.LoginRequest
import com.codelabs.wegot.model.remote.body.RegisterRequest
import com.codelabs.wegot.model.remote.network.MainApiService
import com.codelabs.wegot.model.remote.response.auth.login.LoginResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val mainApiService: MainApiService,
) {
    suspend fun loginAccount(username: String, password: String): LoginResponse =
        mainApiService.login(LoginRequest(username, password))

    suspend fun registerAccount(
        username: String,
        password: String,
        rw: String
    ) = mainApiService.register(
        RegisterRequest(
            username = username,
            rw = rw,
            password = password
        )
    )

//    suspend fun getChatbotResponse(
//        chatbody: ChatBody
//    ) = apiService.getChatResponse(
//        chatbody = chatbody
//    )



}