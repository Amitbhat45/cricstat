package com.example.cricstat.retrofitmatchlist

import com.example.cricstat.retrofitmatchlist.dataclass.matchList
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface matchList {
    @GET("matches/v1/live")
   suspend fun getLiveMatches(): Response<matchList>?

    @GET("matches/v1/recent")
     suspend fun getRecentMatches(): Response<matchList>?

    @GET("matches/v1/upcoming")
    suspend fun getUpcomingMatches(): Response<matchList>?

   /* @GET("img/v1/i1/c{imageId}/i.jpg")
    suspend fun getImage(@Path("imageId") imageId: String): Response<ResponseBody>*/

}