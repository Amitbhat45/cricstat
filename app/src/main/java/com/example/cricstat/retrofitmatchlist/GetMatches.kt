package com.example.cricstat.retrofitmatchlist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState


    @Composable
    fun Iplmatch(viewModel:ViewModelmatchList) {
        val recentMatches by viewModel.recentMatches.observeAsState(initial = null)

    }

