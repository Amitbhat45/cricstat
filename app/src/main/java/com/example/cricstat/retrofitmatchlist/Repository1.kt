package com.example.cricstat.retrofitmatchlist

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

}


