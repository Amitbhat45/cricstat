package com.example.cricstat.screens.stats

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.cricstat.stat.PlayerHeader
import androidx.compose.ui.unit.dp


@Composable
fun singleplayerscreen(){
    Box(modifier = Modifier
        .background(Color(0xFF1b1a25))
        .fillMaxSize())
    Column (modifier = Modifier.fillMaxSize()){
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            Spacer(modifier = Modifier.width(20.dp))
            PlayerHeader()
            Spacer(modifier = Modifier.width(85.dp))
            PlayerHeader()
        }
        
    }
}

@Composable
@Preview
fun pre(){
    singleplayerscreen()
}