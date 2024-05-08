package com.example.cricstat.screens.stats

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.cricstat.GlobalNavController
import com.example.cricstat.R
import com.example.cricstat.screens.Mains.BottomAppBarr
import com.example.cricstat.screens.Mains.BottomAppBarr2
import com.example.cricstat.ui.theme.black
import com.example.cricstat.ui.theme.white1

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun playeraddscreen(){
    val navController=GlobalNavController.navController
    var showDialog by remember { mutableStateOf(false) }
    var playerName1 by remember { mutableStateOf("Add Player1") }
    var playerName2 by remember { mutableStateOf("Add Player2") }
    com.example.cricstat.screens.Mains.setStatusBarColor(color = Color(0xFF171825))
    Scaffold (containerColor = Color(0xff1b1a25),
        modifier = Modifier.padding(all=5.dp),
        bottomBar = {  Box(modifier = Modifier.padding(all = 30.dp)) {
            BottomAppBarr2(navController)
        }}){
    Box(modifier = Modifier
        .background(Color(0xFF171825))
        .fillMaxSize()){
        Column (modifier = Modifier.fillMaxSize(), ) {
            Row {
                Box(modifier = Modifier.padding(top = 170.dp, start = 40.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.playeradd),
                        contentDescription = "add",
                        modifier = Modifier
                            .size(120.dp)
                            .clickable { showDialog = true }
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Box(modifier = Modifier.padding(top = 170.dp, end = 40.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.playeradd),
                        contentDescription = "add",
                        modifier = Modifier
                            .size(120.dp)
                            .clickable { showDialog = true }
                    )
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            Row {
                Text(
                    text =  playerName1,
                    color = Color(0xFFe3b05f),
                    style = TextStyle(fontSize = 20.sp),
                    modifier = Modifier.padding(start=44.dp).wrapContentSize(),
                    maxLines = 1
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text =  playerName2,
                    color = Color(0xFFe3b05f),
                    style = TextStyle(fontSize = 20.sp),
                    modifier = Modifier.padding(end=44.dp).wrapContentSize(),
                    maxLines = 1

                )
            }
            Spacer(modifier = Modifier.height(50.dp))
            Row(Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.Center) {
        Button(onClick = { navController.navigate("statsscreen/$playerName1/$playerName2") },
            colors = ButtonDefaults.buttonColors(Color(0xFF8fcce3))) {
            Text(text = "submit", color = black)
            }
            }
            Spacer(modifier = Modifier.height(30.dp))
            Row(Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.Center) {
            Text(text = "(data available from 2002 )", color = white1,
                style = TextStyle(fontSize = 13.sp))}

            if (showDialog) {
                Dialog(onDismissRequest = { showDialog = false }) {
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = Color(0x80171825), // Slightly transparent background
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            OutlinedTextField(
                                value = playerName1,
                                onValueChange = { playerName1 = it },
                                label = { Text("Player Name 1",color = white1) },
                                placeholder = { Text("Enter Player Name", color = Color.Gray) },
                                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                                keyboardActions = KeyboardActions(onDone = { /* Handle done action */ }),
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    //backgroundColor = Color.Transparent,
                                    focusedBorderColor = Color.Transparent,
                                    unfocusedBorderColor = Color.Transparent,
                                    cursorColor = Color.White,

                                ),
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            OutlinedTextField(
                                value = playerName2,
                                onValueChange = { playerName2 = it },
                                label = { Text("Player Name 2", color = white1) },
                                placeholder = { Text("Enter Player Name", color = Color.Gray) },
                                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                                keyboardActions = KeyboardActions(onDone = { /* Handle done action */ }),
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                   // backgroundColor = Color.Transparent,
                                    focusedBorderColor = Color.Transparent,
                                    unfocusedBorderColor = Color.Transparent,
                                    cursorColor = Color.White
                                ),
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Button(
                                onClick = { showDialog=false },
                                colors = ButtonDefaults.buttonColors( Color(0xFF8fcce3))
                            ) {
                                Text("OK", color = Color.Black)
                            }
                        }
                    }
                }
            }




        }
    }
}}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPlayerDialog() {
    var playerName by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(true) }

    if (showDialog) {
        Dialog(onDismissRequest = { showDialog = false }) {
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = Color(0x80FFFFFF),
                modifier = Modifier.padding(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Add Player",
                        //style = MaterialTheme.typography.,
                        color = Color.White,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    OutlinedTextField(
                        value = playerName,
                        onValueChange = { playerName = it },
                        label = { Text("Player Name") },
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(onDone = {  }),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                           // backgroundColor = Color.Transparent,
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent,
                            cursorColor = Color.White
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = { showDialog=false },
                        colors = ButtonDefaults.buttonColors( Color(0xFF8fcce3))
                    ) {
                        Text("OK", color = Color.White)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun www(){
    playeraddscreen()
}
@Composable
fun ColoredText(text: String, color: Color) {
    Text(text, color = color)
}