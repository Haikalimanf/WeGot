package com.codelabs.wegot.model.remote.response

import com.codelabs.wegot.model.remote.network.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val apiService: ApiService,
)
{
    suspend fun getCartsPaging(
        skip: Int,
        limit: Int
    ) = apiService.getCarts(
        skip = skip,
        limit = limit
    )

}