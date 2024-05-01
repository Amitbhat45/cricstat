package com.example.cricstat.screens.stats

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.cricstat.stat.PlayerHeader
import androidx.compose.ui.unit.dp
import com.example.cricstat.R
import com.example.cricstat.stat.DisplayPlayerStats


@Composable
fun singleplayerscreen(){
    Box(modifier = Modifier
        .background(Color(0xFF1b1a25))
        .fillMaxSize())
    Column (modifier = Modifier.fillMaxSize()){

        Image(painter = painterResource(id = R.drawable.baseline_arrow_back_24), contentDescription = "",
            modifier = Modifier.padding(start=15.dp))

        Spacer(modifier = Modifier.height(30.dp))
        Row {
            Spacer(modifier = Modifier.width(30.dp))
            PlayerHeader()
            Spacer(modifier = Modifier.width(77.dp))
            PlayerHeader()
        }
   Spacer(modifier = Modifier.height(10.dp))
        BattingAndBowlingButtons()
        Spacer(modifier = Modifier.height(50.dp))

            OutlinedCard( colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
            ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .width(350.dp)

                    .height(500.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFF22212f))
                ) {
                    Row (modifier = Modifier.padding(start=18.dp)){

                        DisplayPlayerStats()

                        Spacer(modifier = Modifier.width(20.dp))
                        DisplayPlayerStats()

                }
            }}
        
    }
}

@Composable
fun BattingAndBowlingButtons() {
    val (selectedButton, setSelectedButton) = remember { mutableStateOf("batting") }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = { setSelectedButton("batting") },
            colors = if (selectedButton == "batting") ButtonDefaults.buttonColors( Color(0xFF8fcce3)) else ButtonDefaults.buttonColors( Color(0xFF22212f)),

        ) {
            if (selectedButton == "batting")
            Text(text = "Batting",  color = Color.Black)
            else
                Text(text = "Batting",  color = Color(0xFF87898e))

        }
Spacer(modifier = Modifier.width(8.dp))
        Button(
            onClick = { setSelectedButton("Bowling") },
            colors = if (selectedButton == "Bowling") ButtonDefaults.buttonColors(Color(0xFF8fcce3)) else ButtonDefaults.buttonColors( Color(0xFF22212f)),

        ) {
            if (selectedButton == "Bowling")
                Text(text = "Bowling",  color = Color.Black)
            else
                Text(text = "Bowling",  color = Color(0xFF87898e))
        }
    }
}
@Composable
@Preview
fun pre(){
    singleplayerscreen()
}