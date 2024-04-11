package com.example.cricstat.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.rememberCoroutineScope
import com.example.cricstat.retrofitmatchlist.Repository1
import com.example.cricstat.retrofitmatchlist.dataclass.matchList
import com.example.cricstat.retrofitmatchlist.retrofitInstance

@Composable
fun MainSCreen(userData: UserData?,
               onSignOut: () -> Unit,){

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
    val viewModelScope = rememberCoroutineScope()

val matchList=retrofitInstance.ProvideApi(retrofitInstance.provideRetrofit())
    val repository=Repository1(matchList)
    /*val myViewModel: ViewModelmatchList= viewModel(
        factory = MyViewModelFactory(repository)
    )*/
    val myViewModel=ViewModelmatchList(repository)

    MatchListScreen(viewModel = myViewModel)
}
@Composable
fun MatchListScreen(viewModel: ViewModelmatchList ) {
    /*val liveMatches by viewModel.liveMatches.observeAsState(initial = emptyList())
    val upcomingMatches by viewModel.upcomingMatches.observeAsState(initial = emptyList())*/
    val recentMatches by viewModel.recentMatches.observeAsState(initial = emptyList())

    LazyColumn {
        items(recentMatches) { scorecard ->
            ScorecardCard(scorecard = scorecard)
        }
    }

    // Display the matches using LazyColumn or any other UI component
}

@Composable
fun ScorecardCard(scorecard: matchList) {
    Card(
        shape = RoundedCornerShape(8.dp),

        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "${scorecard.typeMatches}")

        }
    }
}
