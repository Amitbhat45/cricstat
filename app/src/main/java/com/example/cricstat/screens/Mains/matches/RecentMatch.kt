package com.example.cricstat.screens.Mains.matches

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.cricstat.retrofitmatchlist.ViewModelmatchList
import com.example.cricstat.retrofitmatchlist.dataclass.Matche

@Composable
fun getRecentIntMatches(viewModel: ViewModelmatchList) {
    val recentMatches by viewModel.recentMatches.observeAsState(initial = null)


    recentMatches?.let { matchList ->
        val filteredMatches = mutableListOf<Matche>()
        for (typeMatch in matchList.typeMatches) {
            for (seriesMatch in typeMatch.seriesMatches ?: emptyList()) {
                seriesMatch.seriesAdWrapper?.matches?.let { matches ->
                    val filtered = matches.filter { typeMatch.matchType == "International" }
                        .groupBy { it.matchInfo.matchId } // Group matches by matchId
                        .map { it.value.first() } // Take the first element from each group (unique match)
                        .toList() // Convert back to a list
                    filteredMatches.addAll(filtered)
                }
            }
        }
            /* Column {
                  {// match ->
                     //recentScoreCard(match = match)

                 }
                 Text(text = "abcd")
                 Text(text = "abcd")
                 Text(text = "abcd")
             }*/

            LazyRow(){
                items(filteredMatches) { match ->
                    recentScoreCard(match = match)
                }
            }
        }

    }



@Composable
fun getRecentLegMatches(viewModel: ViewModelmatchList) {
    val recentMatches by viewModel.recentMatches.observeAsState(initial = null)

    recentMatches?.let { matchList ->
        val filteredMatches = mutableListOf<Matche>()
        for (typeMatch in matchList.typeMatches) {
            for (seriesMatch in typeMatch.seriesMatches ?: emptyList()) {
                seriesMatch.seriesAdWrapper?.matches?.let { matches ->
                    val filtered = matches.filter { typeMatch.matchType == "League" }
                        .groupBy { it.matchInfo.matchId } // Group matches by matchId
                        .map { it.value.first() } // Take the first element from each group (unique match)
                        .toList() // Convert back to a list
                    filteredMatches.addAll(filtered)
                }
            }
        }

            LazyRow (){
                items(filteredMatches) { match ->
                    //val imgid=match.matchInfo.team1.imageId.toString()
                    recentLegDomScoreCard(match = match)
                }
            }

        }
    }



@Composable
fun getRecentDomMatches(viewModel: ViewModelmatchList) {
    val recentMatches by viewModel.recentMatches.observeAsState(initial = null)

    recentMatches?.let { matchList ->
        val filteredMatches = mutableListOf<Matche>()
        for (typeMatch in matchList.typeMatches) {
            for (seriesMatch in typeMatch.seriesMatches ?: emptyList()) {
                seriesMatch.seriesAdWrapper?.matches?.let { matches ->
                    val filtered = matches.filter { typeMatch.matchType == "Domestic" }
                        .groupBy { it.matchInfo.matchId } // Group matches by matchId
                        .map { it.value.first() } // Take the first element from each group (unique match)
                        .toList() // Convert back to a list
                    filteredMatches.addAll(filtered)
                }
            }
        }

        Column (modifier = Modifier.padding(start=10.dp)){
            for (match in filteredMatches) {
                //val imgid=match.matchInfo.team1.imageId.toString() (assuming you don't need this line)
                recentLegDomScoreCard(match = match)
            }
        }
            }
        }




@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun recentScoreCard(match:Matche) {

    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        //border = BorderStroke(2.dp, Color.White), // Increase border thickness and color for a shining effect
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .width(350.dp)
            .padding(10.dp)
            .height(170.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF22212f))
        )
        {
            Column (modifier = Modifier.fillMaxSize()){
                Row ( verticalAlignment = Alignment.CenterVertically){
                    Text(
                        text = "${match.matchInfo.matchDesc}",
                        color = Color(0xFF87898e),
                        modifier = Modifier
                            //.weight(1f)
                            .padding(start = 15.dp,top=10.dp), // Ensure 10.dp padding from the right end
                        maxLines = 1, // Limit the text to a single line
                        //overflow = TextOverflow.Ellipsis,
                        style = TextStyle(fontSize = 11.5.sp),
                        fontWeight = FontWeight.W500
                        //textAlign = TextAlign.End// Show ellipsis if the text is too long
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = "${match.matchInfo.seriesName}",

                        color = Color(0xFF87898e),
                        modifier = Modifier.padding(top = 10.dp),
                        maxLines = 1, // Limit the text to a single line
                        overflow = TextOverflow.Ellipsis, // Show ellipsis if the text is too long
                        style = TextStyle(fontSize = 11.5.sp),
                        fontWeight = FontWeight.W500)
                    Spacer(modifier = Modifier.width(1.dp))
                    Text(
                        text = "${match.matchInfo.venueInfo.ground}",
                        color = Color(0xFF87898e),
                        modifier = Modifier.padding(top = 10.dp),
                        maxLines = 1, // Limit the text to a single line
                        overflow = TextOverflow.Ellipsis, // Show ellipsis if the text is too long
                        style = TextStyle(fontSize = 11.5.sp),
                        fontWeight = FontWeight.W500)


                }


                Spacer(modifier = Modifier.height(3.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    val teamname=match.matchInfo.team1.teamName
                    val code=getCountryCode(teamname)
                    GlideImage(model = "https://flagcdn.com/w160/$code.png", contentDescription ="" ,
                        modifier = Modifier
                            .size(50.dp)
                            .padding(start = 15.dp))
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(
                        text = "${match.matchInfo.team1.teamSName}",
                        color = Color(0xFFffffff),
                        maxLines = 1, // Limit the text to a single line
                        overflow = TextOverflow.Ellipsis, // Show ellipsis if the text is too long
                        style = TextStyle(fontSize = 16.sp),
                        fontWeight = FontWeight.W500)
                    Spacer(modifier = Modifier.width(15.dp))
                    if(match.matchScore.team1Score!=null){
                        Text(text = "${match.matchScore?.team1Score?.inngs1?.runs}-${match.matchScore?.team1Score?.inngs1?.wickets}", color = Color(0xFF6eb5ee),style = TextStyle(fontSize = 20.sp), fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(text = "(${match.matchScore?.team1Score?.inngs1?.overs})",color = Color(0xFFffffff),style = TextStyle(fontSize = 11.5.sp))
                    }else{
                        Text(text = "yet to bat")
                    }

                }
                Spacer(modifier = Modifier.height(1.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    val teamname=match.matchInfo.team2.teamName
                    val code=getCountryCode(teamname).toString()

                    GlideImage(model = "https://flagcdn.com/w160/$code.png", contentDescription ="" ,
                        modifier = Modifier
                            .size(50.dp)
                            .padding(start = 15.dp))
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(
                        text = "${match.matchInfo.team2.teamSName}",
                        color = Color(0xFFffffff),
                        maxLines = 1, // Limit the text to a single line
                        overflow = TextOverflow.Ellipsis, // Show ellipsis if the text is too long
                        style = TextStyle(fontSize = 16.sp),
                        fontWeight = FontWeight.W500)
                    Spacer(modifier = Modifier.width(15.dp))
                    if(match.matchScore.team2Score!=null){
                        Text(text = "${match.matchScore?.team2Score?.inngs1?.runs}-${match.matchScore?.team2Score?.inngs1?.wickets}", color = Color(0xFF6eb5ee),style = TextStyle(fontSize = 20.sp), fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(text = "(${match.matchScore?.team2Score?.inngs1?.overs})",color = Color(0xFFffffff),style = TextStyle(fontSize = 11.5.sp))
                    }else{
                        Text(text = "yet to bat")
                    }

                }
                Spacer(modifier = Modifier.height(4.5.dp))
                Text(text = "${match.matchInfo.status}", modifier = Modifier.padding(start=15.dp),style = TextStyle(fontSize = 11.5.sp),fontWeight = FontWeight.W600,
                    color = Color(0xFF5DEBD7)
                )


            }
            Column (modifier = Modifier
                .padding(start = 235.dp)
                .fillMaxSize(),
            ){
                Row( modifier = Modifier.fillMaxSize(),verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                    Divider(color = Color(0xFF22323e),
                        thickness = 1.dp, modifier = Modifier
                            .padding(top = 5.dp)
                            .size(width = 1.dp, height = 60.dp))
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(text = "Finished", color = Color(0xFFFF7575))
                }


            }


        }
    }

}

@Composable
fun recentLegDomScoreCard(match:Matche) {

    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        //border = BorderStroke(2.dp, Color.White), // Increase border thickness and color for a shining effect
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .width(350.dp)
            .padding(10.dp)
            .height(170.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF22212f))
        )
        {
            Column (modifier = Modifier.fillMaxSize()){
                Row ( verticalAlignment = Alignment.CenterVertically){
                    Text(
                        text = "${match.matchInfo.matchDesc}",
                        color = Color(0xFF87898e),
                        modifier = Modifier
                            //.weight(1f)
                            .padding(start = 15.dp,top=10.dp), // Ensure 10.dp padding from the right end
                        maxLines = 1, // Limit the text to a single line
                        //overflow = TextOverflow.Ellipsis,
                        style = TextStyle(fontSize = 11.5.sp),
                        fontWeight = FontWeight.W500
                        //textAlign = TextAlign.End// Show ellipsis if the text is too long
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = "${match.matchInfo.seriesName}",

                        color = Color(0xFF87898e),
                        modifier = Modifier.padding(top = 10.dp),
                        maxLines = 1, // Limit the text to a single line
                        overflow = TextOverflow.Ellipsis, // Show ellipsis if the text is too long
                        style = TextStyle(fontSize = 11.5.sp),
                        fontWeight = FontWeight.W500)
                    Spacer(modifier = Modifier.width(1.dp))
                    Text(
                        text = "${match.matchInfo.venueInfo.ground}",
                        color = Color(0xFF87898e),
                        modifier = Modifier.padding(top = 10.dp),
                        maxLines = 1, // Limit the text to a single line
                        overflow = TextOverflow.Ellipsis, // Show ellipsis if the text is too long
                        style = TextStyle(fontSize = 11.5.sp),
                        fontWeight = FontWeight.W500)


                }


                Spacer(modifier = Modifier.height(3.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    val teamname=match.matchInfo.team1.teamName
                    val flag= getIplFlag(teamname)
                    if (flag!=null){
                        flag?.let { painterResource(id = it) }
                            ?.let { Image(painter = it, contentDescription ="" ,
                                modifier = Modifier
                                    .size(50.dp)
                                    .padding(start = 15.dp)) }
                        Spacer(modifier = Modifier.width(20.dp))
                        Text(
                            text = "${match.matchInfo.team1.teamSName}",
                            color = Color(0xFFffffff),
                            maxLines = 1, // Limit the text to a single line
                            overflow = TextOverflow.Ellipsis, // Show ellipsis if the text is too long
                            style = TextStyle(fontSize = 16.sp),
                            fontWeight = FontWeight.W500,
                        )
                        Spacer(modifier = Modifier.width(15.dp))
                        if(match.matchScore?.team1Score!=null){
                            Text(text = "${match.matchScore?.team1Score.inngs1?.runs}-${match.matchScore?.team1Score.inngs1?.wickets}", color = Color(0xFF6eb5ee),style = TextStyle(fontSize = 20.sp), fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(text = "(${match.matchScore?.team1Score.inngs1?.overs})",color = Color(0xFFffffff),style = TextStyle(fontSize = 11.5.sp))
                        }else{
                            Text(text = "yet to bat")
                        }
                    }else{
                        Spacer(modifier = Modifier.height(50.dp))
                        Text(
                            text = "${match.matchInfo.team1.teamSName}",
                            color = Color(0xFFffffff),
                            maxLines = 1, // Limit the text to a single line
                            overflow = TextOverflow.Ellipsis, // Show ellipsis if the text is too long
                            style = TextStyle(fontSize = 16.sp),
                            fontWeight = FontWeight.W500,
                            modifier = Modifier
                                .padding(start = 15.dp))
                        Spacer(modifier = Modifier.width(15.dp))
                        if(match.matchScore.team1Score!=null){
                            Text(text = "${match.matchScore?.team1Score?.inngs1?.runs}-${match.matchScore?.team1Score?.inngs1?.wickets}", color = Color(0xFF6eb5ee),style = TextStyle(fontSize = 20.sp), fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(text = "(${match.matchScore?.team1Score?.inngs1?.overs})",color = Color(0xFFffffff),style = TextStyle(fontSize = 11.5.sp))
                        }else{
                            Text(text = "yet to bat")
                        }
                    }


                }
                Spacer(modifier = Modifier.height(1.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    val teamname=match.matchInfo.team2.teamName
                    val flag= getIplFlag(teamname)
                    if (flag!=null){
                        flag?.let { painterResource(id = it) }
                            ?.let { Image(painter = it, contentDescription ="" ,
                                modifier = Modifier
                                    .size(50.dp)
                                    .padding(start = 15.dp)) }
                        Spacer(modifier = Modifier.width(20.dp))
                        Text(
                            text = "${match.matchInfo.team2.teamSName}",
                            color = Color(0xFFffffff),
                            maxLines = 1, // Limit the text to a single line
                            overflow = TextOverflow.Ellipsis, // Show ellipsis if the text is too long
                            style = TextStyle(fontSize = 16.sp),
                            fontWeight = FontWeight.W500,
                        )
                        Spacer(modifier = Modifier.width(15.dp))
                        if(match.matchScore?.team1Score!=null){
                            Text(text = "${match.matchScore?.team2Score?.inngs1?.runs}-${match.matchScore?.team2Score?.inngs1?.wickets}", color = Color(0xFF6eb5ee),style = TextStyle(fontSize = 20.sp), fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(text = "(${match.matchScore?.team2Score?.inngs1?.overs})",color = Color(0xFFffffff),style = TextStyle(fontSize = 11.5.sp))
                        }else{
                            Text(text = "yet to bat")
                        }
                    }else{
                        Spacer(modifier = Modifier.height(50.dp))
                        Text(
                            text = "${match.matchInfo.team2.teamSName}",
                            color = Color(0xFFffffff),
                            maxLines = 1, // Limit the text to a single line
                            overflow = TextOverflow.Ellipsis, // Show ellipsis if the text is too long
                            style = TextStyle(fontSize = 16.sp),
                            fontWeight = FontWeight.W500,
                            modifier = Modifier

                                .padding(start = 15.dp))
                        Spacer(modifier = Modifier.width(15.dp))
                        if(match.matchScore?.team1Score!=null){
                            Text(text = "${match.matchScore?.team2Score?.inngs1?.runs}-${match.matchScore?.team2Score?.inngs1?.wickets}", color = Color(0xFF6eb5ee),style = TextStyle(fontSize = 20.sp), fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(text = "(${match.matchScore?.team2Score?.inngs1?.overs})",color = Color(0xFFffffff),style = TextStyle(fontSize = 11.5.sp))
                        }else{
                            Text(text = "yet to bat")
                        }
                    }


                }
                Spacer(modifier = Modifier.height(2.dp))
                Text(text = "${match.matchInfo.status}", modifier = Modifier.padding(start=15.dp),style = TextStyle(fontSize = 11.5.sp),fontWeight = FontWeight.W600,
                    color = Color(0xFF5DEBD7))


            }
            Column (modifier = Modifier
                .padding(start = 235.dp)
                .fillMaxSize(),
            ){
                Row( modifier = Modifier.fillMaxSize(),verticalAlignment = Alignment.CenterVertically) {
                    Divider(color = Color(0xFF22323e),
                        thickness = 1.dp, modifier = Modifier
                            .padding(top = 5.dp)
                            .size(width = 1.dp, height = 60.dp))
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(text = "Finished", color = Color(0xFFFF7575))
                }


            }


        }
    }

}
