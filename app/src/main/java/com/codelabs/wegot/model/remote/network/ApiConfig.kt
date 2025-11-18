package com.codelabs.wegot.model.remote.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object {
        fun getMainApiService(): MainApiService {
            val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

            val authInterceptor = Interceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .addHeader("Content-Type", "application/json")

                val request = requestBuilder.build()
                chain.proceed(request)
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(authInterceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.smartmaggot.my.id/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofit.create(MainApiService::class.java)
        }


        fun getChatbotApiService(): ChatbotApiService {
            val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

            val retrofit = Retrofit.Builder()
                .baseUrl("https://chatbot.smartmaggot.my.id/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ChatbotApiService::class.java)
        }

        fun getFlaskApiService(): FlaskApiService {
            val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

            val retrofit = Retrofit.Builder()
                .baseUrl("https://flaskapi.smartmaggot.my.id/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(FlaskApiService::class.java)
        }


    }

}