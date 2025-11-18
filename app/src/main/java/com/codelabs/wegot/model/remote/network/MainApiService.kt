package com.codelabs.wegot.model.remote.network

import com.codelabs.wegot.model.remote.body.ChatBody
import com.codelabs.wegot.model.remote.body.LoginRequest
import com.codelabs.wegot.model.remote.body.RegisterRequest
import com.codelabs.wegot.model.remote.response.auth.LogoutResponse
import com.codelabs.wegot.model.remote.response.auth.login.LoginResponse
import com.codelabs.wegot.model.remote.response.auth.register.RegisterResponse
import com.codelabs.wegot.model.remote.response.chatbot.ChatbotResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface MainApiService {

    @POST("api/auth/register")
    suspend fun register(
        @Body registerRequest: RegisterRequest
    ): RegisterResponse

    @POST("api/auth/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): LoginResponse

    @POST ("api/query")
    suspend fun getChatResponse(
        @Body chatBody: ChatBody
    ): ChatbotResponse

    @POST("api/auth/logout")
    suspend fun logout(): LogoutResponse

}