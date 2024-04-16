package com.example.cricstat.retrofitmatchlist

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object retrofitInstance {
    private const val BASE_URL = "https://cricbuzz-cricket.p.rapidapi.com/"

    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .addHeader("X-RapidAPI-Key", "10dd23b96fmsh75f0a558f1fe512p186edejsn39b188571013")
                .addHeader("X-RapidAPI-Host", "cricbuzz-cricket.p.rapidapi.com")
            val request = requestBuilder.build()
            chain.proceed(request)
        }

        .build()


    fun ProvideApi(builder:Retrofit.Builder):matchList{
        return builder.build()
            .create(matchList::class.java)
    }

    fun provideRetrofit():Retrofit.Builder{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)

    }


}

