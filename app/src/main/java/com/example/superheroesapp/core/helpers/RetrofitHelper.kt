package com.example.superheroesapp.core.helpers

import com.example.superheroesapp.util.Constants.Companion.BASE_URL
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private fun buildClient(): OkHttpClient {
        val builder: OkHttpClient.Builder = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val req = chain.request()
                val url = req.url()
                val requestBuilder = req.newBuilder()
                    .url(url)
                    .addHeader("Accept", "application/json")
                val request = requestBuilder.build()
                chain.proceed(request)
            }
        return builder.build()
    }

    fun getRetrofit(): Retrofit {
        val authInterceptor = buildClient()
        val gson = GsonBuilder().setPrettyPrinting().setLenient().create()

        return Retrofit.Builder()
            .client(authInterceptor)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}