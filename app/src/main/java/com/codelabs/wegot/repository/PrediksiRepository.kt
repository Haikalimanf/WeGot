package com.codelabs.wegot.repository

import com.codelabs.wegot.model.local.LocalDataSource
import com.codelabs.wegot.model.remote.response.RemoteDataSource
import com.codelabs.wegot.model.remote.response.prediksi.PrediksiResponse
import javax.inject.Inject

class PrediksiRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
) {
//    suspend fun getCarts(skip: Int, limit: Int): PrediksiResponse {
//        return remoteDataSource.getCartsPaging(skip, limit)
//    }
}
