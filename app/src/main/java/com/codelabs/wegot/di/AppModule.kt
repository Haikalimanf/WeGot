package com.codelabs.wegot.di

import com.codelabs.wegot.model.remote.network.ApiConfig
import com.codelabs.wegot.model.remote.network.MainApiService
import com.codelabs.wegot.model.remote.network.ChatbotApiService
import com.codelabs.wegot.model.remote.network.FlaskApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideMainApiService(): MainApiService {
        return ApiConfig.getMainApiService()
    }

    @Provides
    fun provideChatbotApiService(): ChatbotApiService {
        return ApiConfig.getChatbotApiService()
    }

    @Provides
    fun provideFlaskApiService(): FlaskApiService {
        return ApiConfig.getFlaskApiService()
    }

}