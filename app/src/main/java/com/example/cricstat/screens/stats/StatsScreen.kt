package com.example.cricstat.screens.stats

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.cricstat.stat.PlayerHeader
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import com.example.cricstat.R
import com.example.cricstat.stat.DisplayPlayerStats


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PlayerStats(){
    Box(modifier = Modifier
        .background(Color(0xFF171825))
        .fillMaxSize())
    Column (modifier = Modifier.fillMaxSize()){

        Image(painter = painterResource(id = R.drawable.baseline_arrow_circle_left_24), contentDescription = "",
            modifier = Modifier.padding(start=15.dp))

        Spacer(modifier = Modifier.height(30.dp))
        Row {
            Spacer(modifier = Modifier.width(23.dp))
            PlayerHeader("Virat Kohli")
            Spacer(modifier = Modifier.width(77.dp))
            PlayerHeader("Rohit Sharma")
        }
       Spacer(modifier = Modifier.height(20.dp))


        Spacer(modifier = Modifier.height(80.dp))

        Column (Modifier.verticalScroll(rememberScrollState())){
            DisplayPlayerStats()
        }





            }
        }









@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
fun pre(){
    PlayerStats()
}