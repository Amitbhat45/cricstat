package com.example.cricstat.screens.Mains

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.cricstat.GlobalNavController
import com.example.cricstat.R
import com.example.cricstat.sign_in.UserData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.tasks.await

@Composable
fun loginprofile(userData: UserData?,
                 onSignOut: () -> Unit,){
    val navController=GlobalNavController.navController
    Box(modifier = Modifier
        .background(Color(0xff1b1a25))
        .fillMaxSize()){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),


        ){
            //Spacer(modifier = )
            Image(painter = painterResource(id = R.drawable.baseline_arrow_back_24), contentDescription = "",
                modifier = Modifier
                    .padding(start = 17.dp)
                    .clickable(onClick = { navController.navigate("mainscreen") }))
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedCard(
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF22212f),
                ),
                //border = BorderStroke(2.dp, Color.White), // Increase border thickness and color for a shining effect
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .width(400.dp)
                    .padding(10.dp)
                    .height(250.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
            )
            {
                Column (modifier =Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center){


            if(userData?.username != null) {
                if(userData?.profilePictureUrl != null) {
                    AsyncImage(
                        model = userData.profilePictureUrl,
                        contentDescription = "Profile picture",
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }
                Text(
                    text = userData.username,
                    textAlign = TextAlign.Center,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

        }
    }
        Spacer(modifier = Modifier.height(3.dp))
            OutlinedCard(
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF22212f),
                ),
                //border = BorderStroke(2.dp, Color.White), // Increase border thickness and color for a shining effect
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .width(400.dp)
                    .padding(10.dp)
                    .fillMaxHeight(),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
            ){
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Recent Comparisons", style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.W600),
                    color = Color(0xFF5DEBD7), modifier = Modifier.padding(start = 15.dp))
                Spacer(modifier = Modifier.height(10.dp))
                val userId=userData?.userId
                var players: List<String>? by remember { mutableStateOf(emptyList()) }

                LaunchedEffect(Unit) {
                     players = retrieveRecentSearchesForUser(userId)
                    Log.d("search history","$players")
                   // searchhistory.add()
                }
                Column(modifier = Modifier.fillMaxWidth()) { // Wrap elements in a Column
                    if (players?.isNotEmpty() == true) {
                        players!!.forEachIndexed { index, search ->
                            val parts = search.split("and")
                            var playerName1=""
                            var playerName2=""
                            if (parts.size == 2) {
                                 playerName1 = parts[0].trim()
                                 playerName2 = parts[1].trim()}
                            Text(text = search, modifier = Modifier.padding(bottom = 5.dp,start=10.dp).
                            clickable { navController.navigate("statsscreen/$playerName1/$playerName2")})
                            if (index < players?.lastIndex!!) {
                                Spacer(modifier = Modifier.height(2.dp))
                            }
                        }
                    } else {
                        Text(text = "No recent comparisons yet.", modifier = Modifier.padding(top = 10.dp))
                    }
                }



            }
            Spacer(modifier = Modifier.height(3.dp))
            OutlinedCard(
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF22212f),
                ),
                //border = BorderStroke(2.dp, Color.White), // Increase border thickness and color for a shining effect
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .width(400.dp)
                    .padding(10.dp)
                    .height(80.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
            ){
                Box(modifier = Modifier.fillMaxSize()) {
                    Row(horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(top=13.dp)// Not strictly necessary here
                    ) {
                        Text(text = "Sign out :", style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.W600),
                            color = Color(0xFFe3b05f), modifier = Modifier.padding(start = 15.dp))
                        Spacer(modifier = Modifier.width(155.dp))
                        Button(onClick = onSignOut,
                            shape = RoundedCornerShape(topEnd = 0.dp, topStart = 0.dp, bottomEnd = 0.dp, bottomStart = 0.dp),
                            colors = ButtonDefaults.buttonColors(Color(0xFF8fcce3))
                        ) {
                            Text(text = "Sign out", color = Color.Black)
                        }
                    }
                }
            }
        
        }}

}

suspend fun retrieveRecentSearchesForUser(userId: String?): List<String>? {
    val db = Firebase.firestore

    val docSnapshot = userId?.let { db.collection("users").document(it).get().await() }
    if (docSnapshot?.exists() == true) {
        val recentSearches = docSnapshot.get("recentSearches") as?List<String>  ?: null
        // Alternative (if "recentSearches" might not be a list):
        // val recentSearchesString = docSnapshot.getString("recentSearches") ?: ""
        // return recentSearchesString.split(", ").toList()  // Only if stored as a comma-separated string

        return recentSearches
    } else {
        println("User document does not exist.")
        return emptyList()
    }
}
/*
class MyViewModel : ViewModel() {

    private val _players = MutableLiveData<List<String>>()
    val players: LiveData<List<String>> get() = _players

    suspend fun retrievePlayersForUser(userId: String) {
        val players = retrievePlayersForUserFromRepository(userId) // Replace with your data access logic
        _players.postValue(players)
    }

    fun fetchPlayers(userId: String) {
        viewModelScope.launch {
            retrievePlayersForUser(userId)
        }
    }
}*/
