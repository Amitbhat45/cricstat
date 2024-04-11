package com.example.cricstat.retrofitmatchlist

import com.example.cricstat.retrofitmatchlist.dataclass.matchList
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface matchList {
    @GET("matches/v1/live")
   suspend fun getLiveMatches(): Response<matchList>?

    @GET("matches/v1/recent")
     suspend fun getRecentMatches(): Response<matchList>?

    @GET("matches/v1/upcoming")
    suspend fun getUpcomingMatches(): Response<matchList>?
}