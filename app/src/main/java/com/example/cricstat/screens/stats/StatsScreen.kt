package com.example.cricstat.screens.stats

import android.os.Build
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.cricstat.stat.PlayerHeader
import androidx.compose.ui.unit.dp
import com.example.cricstat.GlobalNavController
import com.example.cricstat.R
import com.example.cricstat.sign_in.UserData
import com.example.cricstat.stat.DisplayPlayerStats
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@Composable
fun setStatusBarColor(color: Color) {
    val context = LocalContext.current
    val window = (context as? ComponentActivity)?.window
    window?.statusBarColor = color.toArgb()
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PlayerStats(player1:String,player2:String,userData: UserData?){
    Box(modifier = Modifier
        .background(Color(0xFF171825))
        .fillMaxSize())
    Column (modifier = Modifier.fillMaxSize()){

        val navController = GlobalNavController.navController
        com.example.cricstat.screens.Mains.setStatusBarColor(color = Color(0xFF171825))


        Image(painter = painterResource(id = R.drawable.baseline_arrow_back_24), contentDescription = "",
            modifier = Modifier.padding(start=17.dp).clickable (onClick = {navController.navigate("addplayerscreen")}))

        Spacer(modifier = Modifier.height(30.dp))
        Row {
            Spacer(modifier = Modifier.width(23.dp))
            PlayerHeader(player1)
            Spacer(modifier = Modifier.width(77.dp))
            PlayerHeader(player2)
        }
       Spacer(modifier = Modifier.height(20.dp))
        Column (Modifier.verticalScroll(rememberScrollState())){
            DisplayPlayerStats(player1,player2)
        }
        var players: List<String> by remember { mutableStateOf(emptyList()) }

        LaunchedEffect(player1,player2) {
           /*val updatedList = players.toMutableList()
            updatedList.add("$player1 and $player2")
           var players = "$player1 and $player2"*/
            players = players + "$player1 and $player2"
            recentcompareList(players, userData)
        }


    }
        }



suspend fun recentcompareList(recentlist: List<String>, userData: UserData?) {
    val currentUserId = userData?.userId
    val db = Firebase.firestore
    if (currentUserId != null) {
        db.collection("users").document(currentUserId).update(
            mapOf("recentSearches" to FieldValue.arrayUnion(*recentlist.toTypedArray()))
        )
    }
}





@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
fun pre(){
    //PlayerStats()
}