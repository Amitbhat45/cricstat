package com.example.cricstat.screens.Mains

import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cricstat.retrofitmatchlist.ViewModelmatchList
import com.example.cricstat.sign_in.UserData
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import com.example.cricstat.retrofitmatchlist.Repository1
import com.example.cricstat.retrofitmatchlist.dataclass.Matche
import com.example.cricstat.retrofitmatchlist.dataclass.TypeMatche
import com.example.cricstat.retrofitmatchlist.dataclass.matchList
import com.example.cricstat.retrofitmatchlist.retrofitInstance
import com.example.cricstat.screens.Mains.matches.getLiveMatches


@Composable
fun setStatusBarColor(color: Color) {
    val context = LocalContext.current
    val window = (context as? ComponentActivity)?.window
    window?.statusBarColor = color.toArgb()
}
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainSCreen(userData: UserData?,
               onSignOut: () -> Unit,){

    val matchList=retrofitInstance.ProvideApi(retrofitInstance.provideRetrofit())
    val repository=Repository1(matchList)
    /*val myViewModel: ViewModelmatchList= viewModel(
        factory = MyViewModelFactory(repository)
    )*/
    val myViewModel=ViewModelmatchList(repository)
    
    
    setStatusBarColor(color = Color(0xff22212f))
    
    
    Surface (modifier = Modifier.fillMaxSize(),
        color = Color(0xff22212f),

    ){
        Scaffold (containerColor = Color(0xff22212f),
            modifier = Modifier.padding(all=5.dp),
            bottomBar = {  Box(modifier = Modifier.padding(all = 30.dp)) { // Wrap the BottomAppBar in a Box and apply 30.dp padding
                BottomAppBarr()
            }}){
            Column {
                Column (modifier = Modifier
                    //.padding(top = 30.dp)
                    .padding(start = 17.dp)){
                    Box(modifier = Modifier.padding(start = 5.dp)){
                        TopAppBar(userData = userData)
                    }
                    Spacer(modifier = Modifier.height(27.dp))
                    getLiveMatches(viewModel = myViewModel)
                }


            }
        }
    }

    /*Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if(userData?.profilePictureUrl != null) {
            AsyncImage(
                model = userData.profilePictureUrl,
                contentDescription = "Profile picture",
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        if(userData?.username != null) {
            Text(
                text = userData.username,
                textAlign = TextAlign.Center,
                fontSize = 36.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        Button(onClick = onSignOut) {
            Text(text = "Sign out")
        }
    }*/

    /*val repository = providesRepository1


    // Create the custom ViewModelFactory
    val factory = MyViewModelFactory(repository1)

    // Use the ViewModelProvider to get the ViewModel instance with the custom factory
    val viewModel: ViewModelmatchList by viewModel(factory = factory)*/




    
}
/*@Composable
fun MatchListScreen(viewModel: ViewModelmatchList) {
    val recentMatches by viewModel.recentMatches.observeAsState(initial = null)

    recentMatches?.let { matchList ->
        val allMatches = mutableListOf<Matche>()
        for (typeMatche in matchList.typeMatches) {
            for (seriesMatche in typeMatche.seriesMatches ?: emptyList()) {
                seriesMatche.seriesAdWrapper?.matches?.let { matches ->
                    allMatches.addAll(matches)
                }
            }
        }

        LazyColumn {
            items(allMatches) { match ->
                ScorecardCard(match = match)
            }
        }
    }
}


@Composable
fun ScorecardCard(match:Matche) {
    Card(
        shape = RoundedCornerShape(8.dp),

        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "${match.matchInfo.stateTitle}")
            Text(text = "${match.matchInfo.seriesName}")

        }
    }
}*/
