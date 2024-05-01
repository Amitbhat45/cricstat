package com.example.cricstat.stat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
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

            Column(modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            ) {
                DisplayBoxWithLabel(dataByYear?.get(1), "Matches")
                DisplayBoxWithLabel(dataByYear?.get(2), "Runs")
                DisplayBoxWithLabel(dataByYear?.get(3), "Balls")
                DisplayBoxWithLabel(dataByYear?.get(4), "Outs")
                DisplayBoxWithLabel(dataByYear?.get(5), "Avg")
                DisplayBoxWithLabel(dataByYear?.get(6), "SR")
                DisplayBoxWithLabel(dataByYear?.get(7), "HS")
                DisplayBoxWithLabel(dataByYear?.get(8), "50s")
                DisplayBoxWithLabel(dataByYear?.get(9), "100s")
                DisplayBoxWithLabel(dataByYear?.get(10), "4s")
                DisplayBoxWithLabel(dataByYear?.get(11), "6s")
                DisplayBoxWithLabel(dataByYear?.get(12), "Dot%")


            }

        }
















@Composable
fun DisplayBoxWithLabel(data: String?, label: String) {
    Box(modifier = Modifier.fillMaxWidth()) {
        // Your divider
       /* Divider(color = Color(0xFF22323e),
            thickness = 1.dp, modifier = Modifier
                .size(width = 150.dp, height = 1.dp))
        Spacer(modifier = Modifier.height(3.5.dp))*/
        // Your text and box above the divider
        Column(
            modifier = Modifier
                .padding(start = 68.dp,top=3.5.dp) // Adjust padding as needed
        ) {
            data?.let {
                box(data = it)
                Spacer(modifier = Modifier.height(1.5.dp))
                Text(text = label, color = Color.White)
                Spacer(modifier = Modifier.height(3.5.dp))
            }
        }
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
                .align(Alignment.CenterHorizontally)
                .wrapContentSize(),
            //textAlign = TextAlign.Center,
            //style = TextStyle(fontSize = 25.sp),
            fontWeight = FontWeight.W500,)
        Spacer(modifier = Modifier.height(3.dp))
        Text(text = "${name?.get(1)}",
            color = Color(0xFFe3b05f),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .wrapContentSize(),
            //textAlign = TextAlign.Center,
            //style = TextStyle(fontSize = 30.sp),
            fontWeight = FontWeight.W500,)
    }
}}}
@Composable
fun box(data:String){
    Card (modifier = Modifier
        .height(28.dp)
        .width(28.dp),
        shape = RoundedCornerShape(3.dp)){
        Box(modifier = Modifier
            .background(Color(0xFFFF7575))
            .fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "$data", modifier = Modifier.wrapContentSize(), color = Color.White, maxLines = 1)

        }

    }
}
@Preview
@Composable
fun pp(){
    Column (modifier = Modifier.padding(start=50.dp)){
        DisplayPlayerStats()
    }

}