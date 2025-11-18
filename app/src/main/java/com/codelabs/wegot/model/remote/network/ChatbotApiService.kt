package com.codelabs.wegot.model.remote.network

import com.codelabs.wegot.model.remote.body.ChatBody
import com.codelabs.wegot.model.remote.response.auth.login.LoginResponse
import com.codelabs.wegot.model.remote.response.auth.register.RegisterResponse
import com.codelabs.wegot.model.remote.response.chatbot.ChatbotResponse
import com.codelabs.wegot.model.remote.response.prediksi.PrediksiResponse
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Query

interface ChatbotApiService {

    @POST ("api/query")
    suspend fun getChatResponse(
        @Body chatBody: ChatBody
    ): ChatbotResponse

}