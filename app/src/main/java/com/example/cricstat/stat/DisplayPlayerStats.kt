package com.example.cricstat.stat

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import com.example.cricstat.stat.Player1.batting.TestData1
import com.example.cricstat.stat.Player1.getinfo

import com.example.cricstat.stat.Player1.batting.scrapingData1

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DisplayPlayerStats() {
   /* var dataByYear by remember { mutableStateOf<List<String>?>(null) }
    val deferredData = scrapingData1(text = "Virat Kohli")
    LaunchedEffect(Unit) {
        dataByYear = deferredData.await()
    }*/
    val dataByYear = remember {mutableStateOf<List<String>?>(null) }
    val allYearData = remember { mutableStateOf<List<String>?>(null) }
    val deferredData = scrapingData1(text = "Virat Kohli")
    LaunchedEffect(Unit) {
        val (fetchedPlayerYearbyData, fetchedAlltimeData) = deferredData.await()
        dataByYear.value=fetchedPlayerYearbyData
        allYearData.value=fetchedAlltimeData
    }

    val dataByYear2 = remember {mutableStateOf<List<String>?>(null) }
    val allYearData2 = remember { mutableStateOf<List<String>?>(null) }
    val deferredData2= TestData1(text = "Virat Kohli")
    LaunchedEffect(Unit) {
        val (fetchedPlayerYearbyData, fetchedAlltimeData) = deferredData2.await()
        dataByYear2.value=fetchedPlayerYearbyData
        allYearData2.value=fetchedAlltimeData
    }



    Card ( colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.surface
    ),
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .width(365.dp)
            .fillMaxHeight()
            .padding(start = 18.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)){
        Box (modifier = Modifier
            .fillMaxWidth()
            .height(45.dp)
            ){
            BattingAndBowlingButtons()

        }
        Box (modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(Color(0xFF22212f))){
            Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically,) {
                (allYearData.value?.get(1) ?: null)?.let { Text(text = it, color = Color.White
                ,style = TextStyle(fontSize = 17.sp), modifier = Modifier.padding(start=15.dp)
                ) }
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "Innings", color = Color.White
                    ,style = TextStyle(fontSize = 17.sp))
                Spacer(modifier = Modifier.weight(1f))
                //Spacer(modifier = Modifier.width(102.dp))
                (dataByYear2.value?.get(1) ?: null)?.let { Text(text = it, color = Color.White
                    ,style = TextStyle(fontSize = 17.sp), modifier = Modifier.padding(end=15.dp)
                ) }

            }
        }

        Box (modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(Color(0xFF383743))){
            Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
                (allYearData.value?.get(2) ?: null)?.let { Text(text = it, color = Color.White
                    ,style = TextStyle(fontSize = 17.sp), modifier = Modifier.padding(start=15.dp)
                ) }
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "Runs", color = Color.White
                    ,style = TextStyle(fontSize = 17.sp))
                Spacer(modifier = Modifier.weight(1f))
                (dataByYear2.value?.get(2) ?: null)?.let { Text(text = it, color = Color.White
                    ,style = TextStyle(fontSize = 17.sp), modifier = Modifier.padding(end=15.dp)
                ) }

            }
        }

        Box (modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(Color(0xFF22212f))){
            Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
                (dataByYear.value?.get(3) ?: null)?.let { Text(text = it, color = Color.White
                    ,style = TextStyle(fontSize = 17.sp), modifier = Modifier.padding(start=15.dp)
                ) }
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "Balls", color = Color.White
                    ,style = TextStyle(fontSize = 17.sp))
                Spacer(modifier = Modifier.weight(1f))
                (dataByYear2.value?.get(3) ?: null)?.let { Text(text = it, color = Color.White
                    ,style = TextStyle(fontSize = 17.sp), modifier = Modifier.padding(end=15.dp)
                ) }

            }
        }

        Box (modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(Color(0xFF383743))){
            Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
                (dataByYear.value?.get(4) ?: null)?.let { Text(text = it, color = Color.White
                    ,style = TextStyle(fontSize = 17.sp), modifier = Modifier.padding(start=15.dp)
                ) }
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "Outs", color = Color.White
                    ,style = TextStyle(fontSize = 17.sp))
                Spacer(modifier = Modifier.weight(1f))
                (dataByYear2.value?.get(4) ?: null)?.let { Text(text = it, color = Color.White
                    ,style = TextStyle(fontSize = 17.sp), modifier = Modifier.padding(end=15.dp)
                ) }

            }
        }

        Box (modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(Color(0xFF22212f))){
            Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
                (dataByYear.value?.get(5) ?: null)?.let { Text(text = it, color = Color.White
                    ,style = TextStyle(fontSize = 17.sp), modifier = Modifier.padding(start=15.dp)
                ) }
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "Average", color = Color.White
                    ,style = TextStyle(fontSize = 17.sp))
                Spacer(modifier = Modifier.weight(1f))
                (dataByYear2.value?.get(5) ?: null)?.let { Text(text = it, color = Color.White
                    ,style = TextStyle(fontSize = 17.sp), modifier = Modifier.padding(end=15.dp)
                ) }

            }
        }

        Box (modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(Color(0xFF383743))){
            Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
                (dataByYear.value?.get(6) ?: null)?.let { Text(text = it, color = Color.White
                    ,style = TextStyle(fontSize = 17.sp), modifier = Modifier.padding(start=15.dp)
                ) }
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "StrikeRate", color = Color.White
                    ,style = TextStyle(fontSize = 17.sp))
                Spacer(modifier = Modifier.weight(1f))
                (dataByYear2.value?.get(6) ?: null)?.let { Text(text = it, color = Color.White
                    ,style = TextStyle(fontSize = 17.sp), modifier = Modifier.padding(end=15.dp)
                ) }

            }
        }

        Box (modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(Color(0xFF22212f))){
            Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
                (dataByYear.value?.get(7) ?: null)?.let { Text(text = it, color = Color.White
                    ,style = TextStyle(fontSize = 17.sp), modifier = Modifier.padding(start=15.dp)
                ) }
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "HighScore", color = Color.White
                    ,style = TextStyle(fontSize = 17.sp))
                Spacer(modifier = Modifier.weight(1f))
                (dataByYear2.value?.get(7) ?: null)?.let { Text(text = it, color = Color.White
                    ,style = TextStyle(fontSize = 17.sp), modifier = Modifier.padding(end=15.dp)
                ) }

            }
        }

        Box (modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(Color(0xFF383743))){
            Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
                (dataByYear.value?.get(8) ?: null)?.let { Text(text = it, color = Color.White
                    ,style = TextStyle(fontSize = 17.sp), modifier = Modifier.padding(start=15.dp)
                ) }
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "50s", color = Color.White
                    ,style = TextStyle(fontSize = 17.sp))
                Spacer(modifier = Modifier.weight(1f))
                (dataByYear2.value?.get(8) ?: null)?.let { Text(text = it, color = Color.White
                    ,style = TextStyle(fontSize = 17.sp), modifier = Modifier.padding(end=15.dp)
                ) }

            }
        }

        Box (modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(Color(0xFF22212f))){
            Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
                (dataByYear.value?.get(9) ?: null)?.let { Text(text = it, color = Color.White
                    ,style = TextStyle(fontSize = 17.sp), modifier = Modifier.padding(start=15.dp)
                ) }
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "100s", color = Color.White
                    ,style = TextStyle(fontSize = 17.sp))
                Spacer(modifier = Modifier.weight(1f))
                (dataByYear2.value?.get(9) ?: null)?.let { Text(text = it, color = Color.White
                    ,style = TextStyle(fontSize = 17.sp), modifier = Modifier.padding(end=15.dp)
                ) }

            }
        }

        Box (modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(Color(0xFF383743))){
            Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
                (dataByYear.value?.get(10) ?: null)?.let { Text(text = it, color = Color.White
                    ,style = TextStyle(fontSize = 17.sp), modifier = Modifier.padding(start=15.dp)
                ) }
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "Fours", color = Color.White
                    ,style = TextStyle(fontSize = 17.sp))
                Spacer(modifier = Modifier.weight(1f))
                (dataByYear2.value?.get(10) ?: null)?.let { Text(text = it, color = Color.White
                    ,style = TextStyle(fontSize = 17.sp), modifier = Modifier.padding(end=15.dp)
                ) }

            }
        }

        Box (modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(Color(0xFF22212f))){
            Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
                (dataByYear.value?.get(11) ?: null)?.let { Text(text = it, color = Color.White
                    ,style = TextStyle(fontSize = 17.sp), modifier = Modifier.padding(start=15.dp)
                ) }
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "Sixes", color = Color.White
                    ,style = TextStyle(fontSize = 17.sp))
                Spacer(modifier = Modifier.weight(1f))
                (dataByYear2.value?.get(11) ?: null)?.let { Text(text = it, color = Color.White
                    ,style = TextStyle(fontSize = 17.sp), modifier = Modifier.padding(end=15.dp)
                ) }

            }
        }

        Box (modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(Color(0xFF383743))){
            Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
                (dataByYear.value?.get(12) ?: null)?.let { Text(text = it, color = Color.White
                    ,style = TextStyle(fontSize = 17.sp), modifier = Modifier.padding(start=15.dp)
                ) }
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "Dot%", color = Color.White
                    ,style = TextStyle(fontSize = 17.sp))
                Spacer(modifier = Modifier.weight(1f))
                (dataByYear2.value?.get(12) ?: null)?.let { Text(text = it, color = Color.White
                    ,style = TextStyle(fontSize = 17.sp), modifier = Modifier.padding(end=15.dp)
                ) }

            }
        }



    }

        }
















/*@Composable
fun DisplayBoxWithLabel(data: String?, label: String) {
    Box(modifier = Modifier.fillMaxWidth()) {
        // Your divider
       *//* Divider(color = Color(0xFF22323e),
            thickness = 1.dp, modifier = Modifier
                .size(width = 150.dp, height = 1.dp))
        Spacer(modifier = Modifier.height(3.5.dp))*//*
        // Your text and box above the divider
        Column(
            *//*modifier = Modifier
                .padding(start = 50.dp,top=3.5.dp)*//* // Adjust padding as needed
        ) {
            data?.let {
                box(data = it)
                Spacer(modifier = Modifier.height(1.5.dp))
                Text(text = label, color = Color.White)
                Spacer(modifier = Modifier.height(3.5.dp))
            }
        }
    }
}*/

@Composable
fun PlayerHeader(name:String){
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
        val playerinfo= getinfo(name)
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

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun pp(){
    Column (modifier = Modifier.padding(start=50.dp)){
        DisplayPlayerStats()
    }

}

@Composable
fun BattingAndBowlingButtons() {
    val (selectedButton, setSelectedButton) = remember { mutableStateOf("batting") }

    Row(
        modifier = Modifier.fillMaxWidth(), // Buttons fill entire row width
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly // Buttons split space evenly
    ) {
        Button(
            onClick = { setSelectedButton("batting") },
            colors = if (selectedButton == "batting") ButtonDefaults.buttonColors(Color(0xFF8fcce3)) else ButtonDefaults.buttonColors(Color(0xFF22212f)),
            shape = RoundedCornerShape(topStart = 15.dp),
            modifier = Modifier.width(170.dp).height(45.dp)
        ) {
            if (selectedButton == "batting")
                Text(text = "Batting", color = Color.Black)
            else
                Text(text = "Batting", color = Color(0xFF87898e))
        }

        Button(
            onClick = { setSelectedButton("Bowling") },
            colors = if (selectedButton == "Bowling") ButtonDefaults.buttonColors(Color(0xFF8fcce3)) else ButtonDefaults.buttonColors(Color(0xFF22212f)),
            shape = RoundedCornerShape(topEnd = 15.dp),
            modifier = Modifier.width(180.dp).height(45.dp)
        ) {
            if (selectedButton == "Bowling")
                Text(text = "Bowling", color = Color.Black)
            else
                Text(text = "Bowling", color = Color(0xFF87898e))
        }
    }
}
@Composable
fun dropdownbar(){
    CustomDropdownMenu(list = listOf("All","Test","Odi","T20I","Ipl"), defaultSelected = "All", color = Color(0xFF8fcce3), modifier = Modifier, onSelected ={1} )
}

@Composable
fun CustomDropdownMenu(
    list: List<String>, // Menu Options
    defaultSelected: String, // Default Selected Option on load
    color: Color, // Color
    modifier: Modifier, //
    onSelected: (Int) -> Unit, // Pass the Selected Option
) {
    var selectedIndex by remember { mutableStateOf(list.indexOf(defaultSelected)) }
    var expand by remember { mutableStateOf(false) }
    var stroke by remember { mutableStateOf(1) }
    Box(
        modifier
            .padding(8.dp).width(70.dp).height(45.dp)
            .border(
                border = BorderStroke(stroke.dp, color),
                shape = RoundedCornerShape(4.dp)
            )
            .clickable {
                expand = true
                stroke = if (expand) 2 else 1
            },
        contentAlignment = Alignment.Center
    ) {

        Text(
            text =  list[selectedIndex],
            color = color,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
        )

        DropdownMenu(
            expanded = expand,
            onDismissRequest = {
                expand = false
                stroke = if (expand) 2 else 1
            },
            properties = PopupProperties(
                focusable = false,
                dismissOnBackPress = true,
                dismissOnClickOutside = true,
            ),
            modifier = Modifier
                .background(Color(0xFF171825))
                .padding(2.dp)
                .fillMaxWidth(.4f)
        ) {
            list.forEachIndexed { index, item ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = item,
                            color = color,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    onClick = {
                        selectedIndex = index
                        expand = false
                        stroke = if (expand) 2 else 1
                        onSelected(selectedIndex)
                    }
                )
            }
        }

    }
}
