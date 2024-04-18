package com.example.cricstat.screens.Mains.matches

import com.example.cricstat.R

data class Iplflags(
    val name:String,
    val img:Int
)
val flags= listOf( Iplflags(
    "Chennai Super Kings",
    R.drawable.img_4
),
    Iplflags(
        "Kolkata Knight Riders",
        R.drawable.kkr
    ),
    Iplflags(
        "Lucknow Super Giants",
        R.drawable.lsg
    ),
    Iplflags(
        "Gujarat Titans",
        R.drawable.gt
    ),
    Iplflags(
        "Mumbai Indians",
        R.drawable.mi
    ),
    Iplflags(
        "Delhi Capitals",
        R.drawable.dc
    ),
    Iplflags(
        "Royal Challengers Bengaluru",
        R.drawable.rcb
    ),
    Iplflags(
        "Rajasthan Royals",
        R.drawable.rr
    ),
    Iplflags(
        "Sunrisers Hyderabad",
        R.drawable.srh
    ),
    Iplflags(
        "Punjab Kings",
        R.drawable.pbks
    )
)

fun getIplFlag(teamName:String): Int? {
    return flags.find { it.name == teamName }?.img
}