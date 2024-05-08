package com.example.cricstat.screens.Mains

import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cricstat.R
import com.example.cricstat.screens.stats.playeraddscreen
import com.example.cricstat.screens.stats.statss
import com.example.cricstat.sign_in.GoogleAuthUiClient
import com.exyte.animatednavbar.AnimatedNavigationBar
import com.exyte.animatednavbar.animation.balltrajectory.Parabolic
import com.exyte.animatednavbar.animation.balltrajectory.Straight
import com.exyte.animatednavbar.animation.balltrajectory.Teleport
import com.exyte.animatednavbar.animation.indendshape.Height
import com.exyte.animatednavbar.animation.indendshape.shapeCornerRadius
import com.exyte.animatednavbar.utils.noRippleClickable
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient


@Composable
fun BottomAppBarr(navController: NavController){





    val navigationBarItems =remember{NavigationBarItems.values()}
    var selectedIndex by remember { mutableStateOf(0) }
    AnimatedNavigationBar(selectedIndex = selectedIndex,
        modifier = Modifier.height(64.dp),
        cornerRadius = shapeCornerRadius(cornerRadius = 50.dp),
        ballAnimation = Parabolic(tween(300)),
        indentAnimation = Height(tween(300)),
        barColor = Color(0xFF8fcce3),
        ballColor = Color(0xFF8fcce3)
    ) {
        navigationBarItems.forEach{item->
            Box(modifier = Modifier
                .fillMaxSize()
                .noRippleClickablee { selectedIndex = item.ordinal }
                , contentAlignment = Alignment.Center){
             Icon(imageVector = item.icon, contentDescription = "",
                 modifier= Modifier
                     .size(26.dp)
                     .clickable {
                         when (item) {
                             NavigationBarItems.Home -> navController.navigate("mainscreen")
                             NavigationBarItems.search -> navController.navigate("addplayerscreen")
                             //NavigationBarItems.compare -> navController.navigate("loginprofile")
                         }
                     },
                 tint = if(selectedIndex==item.ordinal) Color(0xFF030618)
             else Color(0xFF44434f))
            }
        }

    }


}

enum class NavigationBarItems(val icon:ImageVector){
    Home(icon = Icons.Default.Home),
    search(icon=Icons.Default.Search),

}

fun Modifier.noRippleClickablee(onClick:()->Unit):Modifier=composed{
    clickable (
        indication = null,
        interactionSource = remember { MutableInteractionSource() }
    ){
        onClick()
    }
}

@Composable
fun BottomAppBarr2(navController: NavController){





    val navigationBarItems =remember{NavigationBarItems.values()}
    var selectedIndex by remember { mutableStateOf(1) }
    AnimatedNavigationBar(selectedIndex = selectedIndex,
        modifier = Modifier.height(64.dp),
        cornerRadius = shapeCornerRadius(cornerRadius = 50.dp),
        ballAnimation = Parabolic(tween(300)),
        indentAnimation = Height(tween(300)),
        barColor = Color(0xFF8fcce3),
        ballColor = Color(0xFF8fcce3)
    ) {
        navigationBarItems.forEach{item->
            Box(modifier = Modifier
                .fillMaxSize()
                .noRippleClickablee { selectedIndex = item.ordinal }
                , contentAlignment = Alignment.Center){
                Icon(imageVector = item.icon, contentDescription = "",
                    modifier= Modifier
                        .size(26.dp)
                        .clickable {
                            when (item) {
                                NavigationBarItems.Home -> navController.navigate("mainscreen")
                                NavigationBarItems.search -> navController.navigate("addplayerscreen")
                                //NavigationBarItems.compare -> navController.navigate("loginprofile")
                            }
                        },
                    tint = if(selectedIndex==item.ordinal) Color(0xFF030618)
                    else Color(0xFF44434f))
            }
        }

    }


}