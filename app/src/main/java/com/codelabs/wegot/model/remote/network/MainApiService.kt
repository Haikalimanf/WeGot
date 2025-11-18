package com.codelabs.wegot.model.remote.network

import com.codelabs.wegot.model.remote.body.AddFasePembesaranRequest
import com.codelabs.wegot.model.remote.body.AddFaseRequest
import com.codelabs.wegot.model.remote.body.AddSiklusRequest
import com.codelabs.wegot.model.remote.body.ChatBody
import com.codelabs.wegot.model.remote.body.LoginRequest
import com.codelabs.wegot.model.remote.body.RegisterRequest
import com.codelabs.wegot.model.remote.response.auth.LogoutResponse
import com.codelabs.wegot.model.remote.response.auth.login.LoginResponse
import com.codelabs.wegot.model.remote.response.auth.register.RegisterResponse
import com.codelabs.wegot.model.remote.response.chatbot.ChatbotResponse
import com.codelabs.wegot.model.remote.response.prediksi.AddFaseResponse
import com.codelabs.wegot.model.remote.response.prediksi.AddSiklusResponse
import com.codelabs.wegot.model.remote.response.prediksi.GetFaseResponse
import com.codelabs.wegot.model.remote.response.prediksi.GetSiklusResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface MainApiService {

    @POST("api/auth/register")
    suspend fun register(
        @Body registerRequest: RegisterRequest
    ): RegisterResponse

    @POST("api/auth/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): LoginResponse

    @POST ("api/siklus")
    suspend fun addSiklus(
        @Body siklusRequest: AddSiklusRequest
    ): AddSiklusResponse

    @POST ("api/siklus/{siklusId}/fase")
    suspend fun addFase(
        @Path("siklusId") siklusId: Int,
        @Body faseRequest: AddFaseRequest
    ): AddFaseResponse

    @POST ("api/siklus/{siklusId}/fase")
    suspend fun addFasePembesaran(
        @Path("siklusId") siklusId: Int,
        @Body faseRequest: AddFasePembesaranRequest
    ): AddFaseResponse

    @POST ("api/siklus/{siklusId}/fase")
    suspend fun addFasePanen(
        @Path("siklusId") siklusId: Int,
        @Body faseRequest: AddFasePembesaranRequest
    ): AddFaseResponse

    @GET ("api/siklus/{siklusId}/fase")
    suspend fun getFase(
        @Path("siklusId") siklusId: Int,
    ): GetFaseResponse

    @GET("api/siklus")
    suspend fun getSiklus(): GetSiklusResponse

    @POST("api/auth/logout")
    suspend fun logout(): LogoutResponse

}