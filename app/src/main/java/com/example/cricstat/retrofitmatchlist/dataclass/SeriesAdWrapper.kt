package com.example.cricstat.retrofitmatchlist.dataclass

data class SeriesAdWrapper(
    val matches: List<Matche>,
    val seriesId: Int,
    val seriesName: String
)