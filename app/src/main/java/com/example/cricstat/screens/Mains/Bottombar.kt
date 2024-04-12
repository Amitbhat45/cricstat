package com.example.cricstat.screens.Mains

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.PratikFagadiya.smoothanimationbottombar.model.SmoothAnimationBottomBarScreens
import com.example.cricstat.R

@Composable
fun Bottombar(navController: NavController){
    val bottomNavigationItems = listOf(
        SmoothAnimationBottomBarScreens(
            navController.navigate("mainscreen"),
            stringResource(),
            R.drawable.baseline_home_24
        ), SmoothAnimationBottomBarScreens(
            Screens.TrendingScreen.route,
            stringResource(id = R.string.trending),
            R.drawable.baseline_trending_up_24
        ), SmoothAnimationBottomBarScreens(
            Screens.FeedScreen.route,
            stringResource(id = R.string.feed),
            R.drawable.baseline_feed_24
        )
    )
}


