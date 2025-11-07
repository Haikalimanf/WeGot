package com.codelabs.wegot.model.remote.network

import com.codelabs.wegot.model.remote.response.prediksi.PrediksiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("carts")
    suspend fun getCarts(
        @Query("skip") skip: Int,
        @Query("limit") limit: Int
    ): PrediksiResponse

}