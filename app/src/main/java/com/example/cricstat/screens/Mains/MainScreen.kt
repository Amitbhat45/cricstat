package com.example.cricstat.screens.Mains

import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import com.example.cricstat.R
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cricstat.retrofitmatchlist.ViewModelmatchList
import com.example.cricstat.sign_in.UserData
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.example.cricstat.retrofitmatchlist.Repository1
import com.example.cricstat.retrofitmatchlist.dataclass.Matche
import com.example.cricstat.retrofitmatchlist.dataclass.TypeMatche
import com.example.cricstat.retrofitmatchlist.dataclass.matchList
import com.example.cricstat.retrofitmatchlist.retrofitInstance
import com.example.cricstat.screens.Mains.matches.getLiveDomMatches
import com.example.cricstat.screens.Mains.matches.getLiveIntMatches
import com.example.cricstat.screens.Mains.matches.getLiveLegMatches
import com.example.cricstat.screens.Mains.matches.getRecentDomMatches
import com.example.cricstat.screens.Mains.matches.getRecentIntMatches
import com.example.cricstat.screens.Mains.matches.getRecentLegMatches
import com.example.cricstat.screens.Mains.matches.getUpcomingDomMatches
import com.example.cricstat.screens.Mains.matches.getUpcomingIntMatches
import com.example.cricstat.screens.Mains.matches.getUpcomingLegMatches

import kotlinx.coroutines.launch


@Composable
fun setStatusBarColor(color: Color) {
    val context = LocalContext.current
    val window = (context as? ComponentActivity)?.window
    window?.statusBarColor = color.toArgb()
}
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class,
    ExperimentalStdlibApi::class
)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainSCreen(userData: UserData?,
               onSignOut: () -> Unit,){
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { hometabs.size})
    val selectedTabIndex = remember { derivedStateOf { pagerState.currentPage } }

    val matchList=retrofitInstance.ProvideApi(retrofitInstance.provideRetrofit())
    val repository=Repository1(matchList)
    /*val myViewModel: ViewModelmatchList= viewModel(
        factory = MyViewModelFactory(repository)
    )*/
    val myViewModel=ViewModelmatchList(repository)
    
    
    setStatusBarColor(color = Color(0xff1b1a25))
    
    
    Surface (modifier = Modifier.fillMaxSize(),
        color = Color(0xff1b1a25),

    ){
        Scaffold (containerColor = Color(0xff1b1a25),
            modifier = Modifier.padding(all=5.dp),
            bottomBar = {  Box(modifier = Modifier.padding(all = 30.dp)) { // Wrap the BottomAppBar in a Box and apply 30.dp padding
                BottomAppBarr()
            }}){
            Column {
                Column (modifier = Modifier
                    //.padding(top = 30.dp)
                    .padding(start = 0.dp)){
                    Box(modifier = Modifier.padding(start = 8.dp)){
                        TopAppBar(userData = userData)
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    TabRow(
                        selectedTabIndex = selectedTabIndex.value,
                        modifier = Modifier.fillMaxWidth(),
                        containerColor = Color(0xFF1b1a25),
                        indicator = { tabPositions ->
                            TabRowDefaults.Indicator(
                                color = Color(0xFF8fcce3), // Change the color here
                                modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex.value])
                            )
                        }

                    ) {
                        hometabs.forEachIndexed { index, currentTab ->
                            Tab(
                                selected = selectedTabIndex.value == index,
                                selectedContentColor = Color(0xFFffffff),
                                unselectedContentColor = MaterialTheme.colorScheme.outline,
                                onClick = {
                                    scope.launch {
                                        pagerState.animateScrollToPage(currentTab.index)
                                    }
                                },
                                text = { Text(text = hometabs[index].txt1) },

                            )
                        }
                    }

                    HorizontalPager(
                        state = pagerState,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Column (modifier = Modifier.fillMaxSize()){

                                if(hometabs[selectedTabIndex.value].txt1=="Live"){
                                   Column (modifier = Modifier
                                       .fillMaxSize() // Fills the available space
                                       .height(300.dp) // Sets a minimum height (adjust as needed)
                                       .verticalScroll(rememberScrollState())) {
                                       Spacer(modifier = Modifier.height(5.dp))
                                        Row (verticalAlignment = Alignment.CenterVertically){
                                            Image(painter = painterResource(id = R.drawable.img_3), contentDescription = "",modifier = Modifier.size(16.dp))
                                            Spacer(modifier = Modifier.width(1.8.dp))
                                            Text(
                                                text = "International",
                                                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.W600),
                                                color = Color(0xFFe3b05f)
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(10.dp))
                                        getLiveIntMatches(viewModel = myViewModel)
                                        Spacer(modifier = Modifier.height(12.dp))
                                        Row (verticalAlignment = Alignment.CenterVertically){
                                            Image(painter = painterResource(id = R.drawable.leglogo), contentDescription = "",modifier = Modifier.size(26.dp))
                                            Spacer(modifier = Modifier.width(1.8.dp))
                                            Text(
                                                text = "League",
                                                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.W600),
                                                color = Color(0xFFe3b05f)
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(10.dp))
                                        getLiveLegMatches(viewModel = myViewModel)
                                        Spacer(modifier = Modifier.height(12.dp))
                                        Row (verticalAlignment = Alignment.CenterVertically){
                                            Image(painter = painterResource(id = R.drawable.domesticlogo), contentDescription = "",modifier = Modifier.size(16.dp))
                                            Spacer(modifier = Modifier.width(1.8.dp))
                                            Text(
                                                text = "Domestic",
                                                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.W600),
                                                color = Color(0xFFe3b05f)
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(10.dp))
                                        getLiveDomMatches(viewModel = myViewModel)

                                    }
                                }
                                else if(hometabs[selectedTabIndex.value].txt1=="Recent"){
                                    Column (modifier = Modifier
                                        .fillMaxSize() // Fills the available space
                                        .height(300.dp) // Sets a minimum height (adjust as needed)
                                        .verticalScroll(rememberScrollState())) {
                                        Spacer(modifier = Modifier.height(5.dp))
                                        Row (verticalAlignment = Alignment.CenterVertically){
                                            Image(painter = painterResource(id = R.drawable.img_3), contentDescription = "",modifier = Modifier.size(16.dp))
                                            Spacer(modifier = Modifier.width(1.8.dp))
                                            Text(
                                                text = "International",
                                                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.W600),
                                                color = Color(0xFFe3b05f)
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(10.dp))
                                        getRecentIntMatches(viewModel = myViewModel)
                                        Spacer(modifier = Modifier.height(12.dp))
                                        Row (verticalAlignment = Alignment.CenterVertically){
                                            Image(painter = painterResource(id = R.drawable.leglogo), contentDescription = "",modifier = Modifier.size(26.dp))
                                            Spacer(modifier = Modifier.width(1.8.dp))
                                            Text(
                                                text = "League",
                                                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.W600),
                                                color = Color(0xFFe3b05f)
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(10.dp))
                                        getRecentLegMatches(viewModel = myViewModel)
                                        Spacer(modifier = Modifier.height(12.dp))
                                        Row (verticalAlignment = Alignment.CenterVertically){
                                            Image(painter = painterResource(id = R.drawable.domesticlogo), contentDescription = "",modifier = Modifier.size(16.dp))
                                            Spacer(modifier = Modifier.width(1.8.dp))
                                            Text(
                                                text = "Domestic",
                                                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.W600),
                                                color = Color(0xFFe3b05f)
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(10.dp))
                                        getRecentDomMatches(viewModel = myViewModel)

                                    }
                                }else{
                                    Column (modifier = Modifier
                                        .fillMaxSize() // Fills the available space
                                        .height(300.dp) // Sets a minimum height (adjust as needed)
                                        .verticalScroll(rememberScrollState())) {
                                        Spacer(modifier = Modifier.height(5.dp))
                                        Row (verticalAlignment = Alignment.CenterVertically){
                                            Image(painter = painterResource(id = R.drawable.img_3), contentDescription = "",modifier = Modifier.size(16.dp))
                                            Spacer(modifier = Modifier.width(1.8.dp))
                                            Text(
                                                text = "International",
                                                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.W600),
                                                color = Color(0xFFe3b05f)
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(10.dp))
                                        getUpcomingIntMatches(viewModel = myViewModel)
                                        Spacer(modifier = Modifier.height(12.dp))
                                        Row (verticalAlignment = Alignment.CenterVertically){
                                            Image(painter = painterResource(id = R.drawable.leglogo), contentDescription = "",modifier = Modifier.size(26.dp))
                                            Spacer(modifier = Modifier.width(1.8.dp))
                                            Text(
                                                text = "League",
                                                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.W600),
                                                color = Color(0xFFe3b05f)
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(10.dp))
                                        getUpcomingLegMatches(viewModel = myViewModel)
                                        Spacer(modifier = Modifier.height(12.dp))
                                        Row (verticalAlignment = Alignment.CenterVertically){
                                            Image(painter = painterResource(id = R.drawable.domesticlogo), contentDescription = "",modifier = Modifier.size(16.dp))
                                            Spacer(modifier = Modifier.width(1.8.dp))
                                            Text(
                                                text = "Domestic",
                                                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.W600),
                                                color = Color(0xFFe3b05f)
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(10.dp))
                                        getUpcomingDomMatches(viewModel = myViewModel)

                                    }
                                }
                            }



                        }
                    }

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
}*//*
enum class HomeTabs(

    val text: String
) {
    Shop(

        text = "Live"
    ),
    Favourite(

        text = "Recent"
    ),
    Profile(

        text = "Upcoming"
    )
}*/
data class HomeTabs(
    val index: Int,
    var txt1: String
)

val hometabs= listOf(
    HomeTabs(
        0,
        "Live"
    ),
    HomeTabs(
        1,
        "Recent"
    ),
    HomeTabs(
        2,
        "Upcoming"
    )

)