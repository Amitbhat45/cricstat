package com.example.cricstat.retrofitmatchlist.dataclass

data class matchList(
    val appIndex: AppIndex,
    val filters: Filters,
    val responseLastUpdated: String,
    val typeMatches: List<TypeMatche>
)