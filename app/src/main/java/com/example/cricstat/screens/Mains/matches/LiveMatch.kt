package com.example.cricstat.screens.Mains.matches

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.example.cricstat.R
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cricstat.retrofitmatchlist.ViewModelmatchList
import com.example.cricstat.retrofitmatchlist.dataclass.MatchInfo
import com.example.cricstat.retrofitmatchlist.dataclass.Matche
import org.checkerframework.checker.units.qual.C

@Composable
fun getLiveMatches(viewModel:ViewModelmatchList) {
    val liveMatches by viewModel.liveMatches.observeAsState(initial = null)

    liveMatches?.let { matchList ->
        val allMatches = mutableListOf<Matche>()
        for (typeMatche in matchList.typeMatches) {
            for (seriesMatche in typeMatche.seriesMatches ?: emptyList()) {
                seriesMatche.seriesAdWrapper?.matches?.let { matches ->
                    allMatches.addAll(matches)
                }
            }
        }
        LazyRow {
            items(allMatches) { match ->
                liveScoreCard(match = match)
            }
        }
    }

}

@Composable
fun liveScoreCard(match:Matche) {
    Card(
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier
            .width(300.dp)
            .padding(15.dp)
            .height(230.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent // Make the card's background transparent
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFF86b8d0),
                            Color(0xFF8dbdd6), // Start color
                            Color(0xFF8fcce3) // End color
                        )
                    )
                )
        ) {
            Column (modifier = Modifier.fillMaxSize()){
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(painter = painterResource(id = R.drawable.live), contentDescription = "live",
                        modifier = Modifier
                            .size(42.dp)
                            .padding(start = 10.dp, top = 8.dp))
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(
                        text = "${match.matchInfo.matchDesc}",
                        color = Color(0xFF1a252a),
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 10.dp, top = 8.dp), // Ensure 10.dp padding from the right end
                        maxLines = 1, // Limit the text to a single line
                        overflow = TextOverflow.Ellipsis,
                        style = TextStyle(fontSize = 11.5.sp),
                        //textAlign = TextAlign.End// Show ellipsis if the text is too long
                    )
                }
                Spacer(modifier = Modifier.height(1.5.dp))
                Text(
                    text = "${match.matchInfo.seriesName}",
                    color = Color(0xFF1a252a),
                    modifier = Modifier.padding(start = 10.dp),
                    maxLines = 1, // Limit the text to a single line
                    overflow = TextOverflow.Ellipsis, // Show ellipsis if the text is too long
                    style = TextStyle(fontSize = 12.5.sp) )



            }
        }
    }
}

@Preview
@Composable
fun prev(){
    dummyCard()
}
@Composable
fun dummyCard() {
    Card(
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier
            .width(300.dp)
            .padding(15.dp)
            .height(230.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent // Make the card's background transparent
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFF86b8d0),
                            Color(0xFF8dbdd6), // Start color
                            Color(0xFF8fcce3) // End color
                        )
                    )
                )
        ) {
            Column (modifier = Modifier.fillMaxSize()){
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(painter = painterResource(id = R.drawable.live), contentDescription = "live",
                        modifier = Modifier
                            .size(42.dp)
                            .padding(start = 10.dp, top = 8.dp))
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(
                        text = "1st match",
                        color = Color(0xFF1a252a),
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 10.dp, top = 8.dp), // Ensure 10.dp padding from the right end
                        maxLines = 1, // Limit the text to a single line
                        overflow = TextOverflow.Ellipsis,
                        style = TextStyle(fontSize = 11.5.sp),
                        //textAlign = TextAlign.End// Show ellipsis if the text is too long
                    )
                }
                Spacer(modifier = Modifier.height(1.5.dp))
                Text(
                    text = "indian prem league",
                    color = Color(0xFF1a252a),
                    modifier = Modifier.padding(start = 10.dp),
                    maxLines = 1, // Limit the text to a single line
                    overflow = TextOverflow.Ellipsis, // Show ellipsis if the text is too long
                    style = TextStyle(fontSize = 12.5.sp) )



            }
        }
    }
}