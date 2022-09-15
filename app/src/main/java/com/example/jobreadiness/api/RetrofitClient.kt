package com.example.jobreadiness.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object {

        private lateinit var  INSTANCE: Retrofit
        private const val  CATEGORY_URL = "https://api.mercadolibre.com/"

        private fun getRetrofitInstance(): Retrofit{
            val http = OkHttpClient.Builder()
            if (!:: INSTANCE.isInitialized) {
                INSTANCE = Retrofit.Builder()
                    .baseUrl(CATEGORY_URL)
                    .client(http.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return INSTANCE
        }
        fun createService() : RefactorService {
           return getRetrofitInstance().create(RefactorService::class.java)
        }
    }
}