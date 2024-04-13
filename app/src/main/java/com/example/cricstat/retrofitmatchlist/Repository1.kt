package com.example.cricstat.retrofitmatchlist

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response

class Repository1(private val matchList:  matchList) {
    suspend fun getLiveMatches(): Response<com.example.cricstat.retrofitmatchlist.dataclass.matchList>? {
        return matchList.getLiveMatches()
    }
    suspend fun getUpcomingMatches():Response<com.example.cricstat.retrofitmatchlist.dataclass.matchList>? {
        return matchList.getUpcomingMatches()
    }
    suspend fun getRecentMatches(): Response<com.example.cricstat.retrofitmatchlist.dataclass.matchList>? {
        return matchList.getRecentMatches()
    }

    suspend fun getImage(imageId: String):Response<ResponseBody>?{
        return matchList.getImage(imageId)
    }
}


