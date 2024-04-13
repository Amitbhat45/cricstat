package com.example.cricstat.screens.Mains.matches

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.example.cricstat.R
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.cricstat.retrofitmatchlist.ImageFetcher
import com.example.cricstat.retrofitmatchlist.ViewModelmatchList
import com.example.cricstat.retrofitmatchlist.dataclass.MatchInfo
import com.example.cricstat.retrofitmatchlist.dataclass.Matche
import com.example.cricstat.retrofitmatchlist.dataclass.Team1
import org.checkerframework.checker.units.qual.C

@Composable
fun getLiveMatches(viewModel:ViewModelmatchList) {
    val liveMatches by viewModel.liveMatches.observeAsState(initial = null)
    val imageFetcher = ImageFetcher(viewModel)
    val imageBytes by viewModel.imageBytes.observeAsState()
    val bitmap = imageBytes?.let { BitmapFactory.decodeByteArray(it, 0, it.size) }

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
                if (bitmap != null) {
                    liveScoreCard(match = match,imageFetcher,bitmap)
                }
            }
        }
    }

}

@Composable
fun liveScoreCard(match:Matche,imageFetcher: ImageFetcher,bitmap: Bitmap) {
    Card(
        shape = RoundedCornerShape(32.dp),
        modifier = Modifier
            .width(280.dp)
            .padding(15.dp)
            .height(210.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent // Make the card's background transparent
        ), elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
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

                }
                Spacer(modifier = Modifier.height(1.dp))

                Row ( verticalAlignment = Alignment.CenterVertically){
                    Text(
                        text = "${match.matchInfo.matchDesc}:",
                        color = Color(0xFF1a252a),
                        modifier = Modifier
                            //.weight(1f)
                            .padding(start = 15.dp), // Ensure 10.dp padding from the right end
                        maxLines = 1, // Limit the text to a single line
                        //overflow = TextOverflow.Ellipsis,
                        style = TextStyle(fontSize = 11.5.sp),
                        fontWeight = FontWeight.W500
                        //textAlign = TextAlign.End// Show ellipsis if the text is too long
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = "${match.matchInfo.seriesName}",
                        color = Color(0xFF1a252a),
                        // modifier = Modifier.padding(start = 15.dp),
                        maxLines = 1, // Limit the text to a single line
                        overflow = TextOverflow.Ellipsis, // Show ellipsis if the text is too long
                        style = TextStyle(fontSize = 11.5.sp),
                        fontWeight = FontWeight.W500)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    imageFetcher.fetchImage(match.matchInfo.team1.imageId.toString())
                    bitmap?.asImageBitmap()?.let {
                        Image(bitmap = it, contentDescription = "", modifier = Modifier
                            .size(55.dp)
                            .padding(start = 15.dp))
                    }
                    Spacer(modifier = Modifier.width(100.dp))
                    Text(text = "300-7", color = Color(0xFF000101),style = TextStyle(fontSize = 20.sp), fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = "(50)",color = Color(0xFF1a252a),style = TextStyle(fontSize = 11.5.sp))
                }

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
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(2.dp, Color.White), // Increase border thickness and color for a shining effect
        shape = RoundedCornerShape(32.dp),
        modifier = Modifier
            .width(280.dp)
            .padding(15.dp)
            .height(210.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
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
                            .padding(start = 15.dp, top = 8.dp))
                    Spacer(modifier = Modifier.width(20.dp))

                }
                Spacer(modifier = Modifier.height(1.dp))
                Row ( verticalAlignment = Alignment.CenterVertically){
                    Text(
                        text = "1st match :",
                        color = Color(0xFF1a252a),
                        modifier = Modifier
                            //.weight(1f)
                            .padding(start = 15.dp), // Ensure 10.dp padding from the right end
                        maxLines = 1, // Limit the text to a single line
                        //overflow = TextOverflow.Ellipsis,
                        style = TextStyle(fontSize = 11.5.sp),
                        fontWeight = FontWeight.W500
                        //textAlign = TextAlign.End// Show ellipsis if the text is too long
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = "indian prem league",
                        color = Color(0xFF1a252a),
                        // modifier = Modifier.padding(start = 15.dp),
                        maxLines = 1, // Limit the text to a single line
                        overflow = TextOverflow.Ellipsis, // Show ellipsis if the text is too long
                        style = TextStyle(fontSize = 11.5.sp),
                        fontWeight = FontWeight.W500)
                }


                Spacer(modifier = Modifier.height(5.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(painter = painterResource(id = R.drawable.img_2), contentDescription = "", modifier = Modifier
                        .size(55.dp)
                        .padding(start = 15.dp))
                    Spacer(modifier = Modifier.width(100.dp))
                    Text(text = "142-3", color = Color(0xFF000101),style = TextStyle(fontSize = 20.sp), fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = "(27)",color = Color(0xFF1a252a),style = TextStyle(fontSize = 11.5.sp))
                }
                Spacer(modifier = Modifier.height(1.5.dp))
                Text(text = "pak need 250 to win", modifier = Modifier.padding(start=15.dp),style = TextStyle(fontSize = 11.5.sp),fontWeight = FontWeight.W600)


            }
        }
    }
}