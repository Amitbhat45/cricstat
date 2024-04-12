package com.example.cricstat.screens.Logins

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.annotation.DrawableRes
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.cricstat.R
import com.example.cricstat.sign_in.SignInState


data class Logos(
    @DrawableRes var logo: Int,
    var txt1: String,
    var txt2: String
)

val logos1 = listOf(
    Logos(
        R.drawable.logo,
        "cricstat",
        "Your go-to destination for live scores \n and player comparisons"
    ),
    Logos(
        R.drawable.logo2,
        "Live Score",
        "Stay ahead of the \n game  with real-time scores"
    ),
    Logos(
        R.drawable.logo3,
        "stats",
        "Level up your cricket \nknowledge with detailed player analysis."
    ),
    Logos(
        R.drawable.logo4,
        "compare",
        "Explore and compare \nthe stats of your cricket idols"
    )
)

@Composable
fun setStatusBarColor(color: Color) {
    val context = LocalContext.current
    val window = (context as? ComponentActivity)?.window
    window?.statusBarColor = color.toArgb()
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
@Composable
fun login1(
    /*lifecycleScope: LifecycleCoroutineScope,
    applicationContext: Context,
    navController: NavController,
    launcher: ActivityResultLauncher<IntentSenderRequest>,*/
    state: SignInState,
    onSignInClick: () -> Unit,
) {
//googleauthclient define
    /*val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }*/

    //launcher define
    val context = LocalContext.current
    state.signInError?.let { error ->
        Toast.makeText(
            context,
            error,
            Toast.LENGTH_LONG
        ).show()
    }

    setStatusBarColor(color = Color(0xFF181818))
    var pageCount by remember { mutableStateOf(0) }
    val pagerstate = rememberPagerState(pageCount = {
        4
    })
    var selectedImageIndex by remember { mutableStateOf(0) }




    Surface(color = Color(0xFF181818)) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Spacer(modifier = Modifier.height(130.dp))


                Image(
                    painter = painterResource(id = logos1[selectedImageIndex].logo), // Use image based on selected index
                    contentDescription = "",
                    modifier = Modifier
                        .size(200.dp)

                        .align(CenterHorizontally)


                )
            }

            HorizontalPager(
                // pageCount = 4,
                state = pagerstate,
                modifier = Modifier.fillMaxSize()
            ) { index ->
                val offset = calculateTransitionOffset(pagerstate.currentPageOffsetFraction)
                LaunchedEffect(pagerstate.currentPage) {
                    // Update selectedImageIndex when currentPage changes (swipe)
                    selectedImageIndex = pagerstate.currentPage
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        // .padding(top = 130.dp)
                        .align(alignment = Center)
                        .padding(top = 350.dp)
                        .animateContentSize(animationSpec = tween(durationMillis = 300))
                ) {


                    Text(
                        text = logos1[index].txt1, // Use text based on current index
                        color = Color.White,
                        style = TextStyle(fontSize = 30.sp),
                        modifier = Modifier
                            .alpha(offset)
                            .align(CenterHorizontally)
                    )

                    Text(
                        text = logos1[index].txt2, // Use text based on current index
                        color = Color(0xFF868686),
                        style = TextStyle(
                            color = Color(0xFF868686),
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .alpha(offset) // Show text gradually during transition
                            .align(CenterHorizontally), maxLines = 2
                    )

                }

            }
            Column(modifier = Modifier.fillMaxSize()) {
                PageIndicator( // Ensure Box size and alpha are appropriate
                    count = 4,
                    pagerstate = pagerstate,
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .padding(top = 450.dp)

                )

            }
            Column(modifier = Modifier.fillMaxSize().padding(top = 630.dp)) {
                /* val viewModel = viewModel<SignInViewModel>()
                 val state by viewModel.state.collectAsStateWithLifecycle()*/

                /*  auth_button(state = SignInState(),
                      onSignInClick = {
                          executeSignIn( lifecycleScope,googleAuthUiClient, launcher = launcher)
                      })*/


                Button(
                    onClick = {
                        onSignInClick()
                    },
                    modifier = Modifier

                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(horizontal = 25.dp), // Left and right padding
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.tel_skyblue))
                ) {

                    Text(text = "Continue with Google")
                }
            }


        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PageIndicator(
    count: Int,
    pagerstate: PagerState,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        repeat(count) { index ->
            Box(
                modifier = Modifier
                    .size(7.dp)
                    .clip(RoundedCornerShape(50))
                    .background(
                        if (pagerstate.currentPage == index) Color(0xff2da5e0) else Color(
                            0xff868686
                        )

                    )
            )

            if (index != count) {
                Spacer(modifier = Modifier.padding(horizontal = 4.dp))
            }
        }
    }
}

fun calculateTransitionOffset(pageOffset: Float): Float {
    // Invert the pageOffset and adjust it to be within range [0, 1]
    val invertedOffset = 1f - pageOffset.coerceIn(0f, 1f)
    // Apply any additional transformations or easing curves as needed
    return invertedOffset
}


@Preview
@Composable
fun prev(){
    val context= LocalContext.current
    val navController = rememberNavController()
   // login1(context,navController)
}