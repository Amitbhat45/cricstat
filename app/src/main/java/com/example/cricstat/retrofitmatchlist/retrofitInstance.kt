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
        //.addInterceptor(RateLimitInterceptor(200L)) // 200ms delay between requests
        .build()

    /*private val BASE_DELAY_MILLIS = 200L
    private val rateLimiter = CoroutineRateLimiter(5, 1, TimeUnit.SECONDS)*/
    fun ProvideApi(builder:Retrofit.Builder):matchList{
        return builder.build()
            .create(matchList::class.java)
    }

    fun provideRetrofit():Retrofit.Builder{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            //.addCallAdapterFactory(RetrofitRetry.create())
    }
/*
    val instance: matchList by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(matchList::class.java)

    }*/

}
/*
class RateLimitInterceptor(private val delayMillis: Long) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        Thread.sleep(delayMillis) // Introduce a delay before the request
        return chain.proceed(chain.request())
    }
}*/
