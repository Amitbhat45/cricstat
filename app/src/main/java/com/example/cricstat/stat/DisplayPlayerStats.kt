package com.example.cricstat.stat

import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

@Composable
fun DisplayPlayerStats() {
    var dataByYear by remember { mutableStateOf<List<String>?>(null) }
    val deferredData = scrapingData(text = "Virat Kohli")
    LaunchedEffect(Unit) {
        dataByYear = deferredData.await()
    }

    Column(modifier = Modifier.fillMaxSize()) {
        /*dataByYear?.let { stats ->
            stats.forEach { row ->
                Text(text = row.joinToString(separator = ", "))
            }
        } ?: Text(text = "Loading...")*/
        Text(text = "$dataByYear")
    }
}



@Composable
fun PlayerHeader(){
    OutlinedCard (colors = CardDefaults.cardColors(
        containerColor = Color(0xFF22212f),
    ),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .width(130.dp)
            .height(150.dp)
            ,
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)){

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center

        ){
            Column(
                modifier = Modifier.wrapContentSize(), // Wrap the content size of the Column
                horizontalAlignment = Alignment.CenterHorizontally // Center the content horizontally
            ) {


        val playerName = remember { mutableStateOf<String?>(null) }
        val info = remember { mutableStateOf<List<String>?>(null) }
        val playerinfo= getinfo()
        LaunchedEffect(Unit) {
            val (fetchedPlayerName, fetchedInfo) = playerinfo.await()
           playerName.value=fetchedPlayerName
            info.value=fetchedInfo
        }
        val name=playerName.value?.split(" ")

        Text(text = "${name?.get(0)}",
            color = Color(0xFFe3b05f),
            modifier = Modifier
            .align(Alignment.CenterHorizontally).
            wrapContentSize(),
            //textAlign = TextAlign.Center,
            //style = TextStyle(fontSize = 25.sp),
            fontWeight = FontWeight.W500,)
        Spacer(modifier = Modifier.height(3.dp))
        Text(text = "${name?.get(1)}",
            color = Color(0xFFe3b05f),
            modifier = Modifier.
            align(Alignment.CenterHorizontally).
            wrapContentSize(),
            //textAlign = TextAlign.Center,
            //style = TextStyle(fontSize = 30.sp),
            fontWeight = FontWeight.W500,)
    }
}}}
@Preview
@Composable
fun pp(){

    PlayerHeader()
}