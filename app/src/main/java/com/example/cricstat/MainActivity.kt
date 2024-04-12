package com.example.cricstat

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.cricstat.ui.theme.CricstatTheme
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.cricstat.screens.Mains.MainSCreen
import com.example.cricstat.screens.Logins.login1
import com.example.cricstat.sign_in.GoogleAuthUiClient
import com.example.cricstat.sign_in.SignInViewModel
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var signInClient: SignInClient
    private lateinit var googleAuthUiClient: GoogleAuthUiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signInClient = Identity.getSignInClient(applicationContext)
        googleAuthUiClient = if (signInClient != null) {
            GoogleAuthUiClient(
                context = applicationContext,
                oneTapClient = signInClient
            )
        } else {
            // Handle the case where SignInClient is null
            // You can log an error or throw an exception
            throw IllegalStateException("Failed to initialize GoogleAuthUiClient: SignInClient is null")
        }
        setContent {
            //window.statusBarColor=getColor(R.color.tel_black)
            CricstatTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    // MyActivityContent(this)
                    val navController = rememberNavController()



                    NavHost(navController = navController, startDestination = "login1") {
                        composable("login1") {
                            val viewModel = viewModel<SignInViewModel>()
                            val state by viewModel.state.collectAsStateWithLifecycle()

                            LaunchedEffect(key1 = Unit) {
                                if (googleAuthUiClient.getSignedInUser() != null) {
                                    navController.navigate("mainscreen")
                                }
                            }
                            val launcher = rememberLauncherForActivityResult(
                                contract = ActivityResultContracts.StartIntentSenderForResult(),
                                onResult = { result ->
                                    Log.d("MainActivity", "Sign-in result: ${result.resultCode}")
                                    if (result.resultCode == RESULT_OK) {
                                        lifecycleScope.launch {
                                            try {
                                                val signInResult =
                                                    googleAuthUiClient.signInWithIntent(
                                                        intent = result.data ?: return@launch
                                                    )
                                                viewModel.onSignInResult(signInResult)
                                            } catch (e: Exception) {
                                                Log.e(
                                                    "MainActivity",
                                                    "Error processing sign-in result",
                                                    e
                                                )
                                            }
                                        }
                                    }
                                }
                            )


                            LaunchedEffect(key1 = state.isSignInSuccessful) {
                                if (state.isSignInSuccessful) {
                                    Toast.makeText(
                                        applicationContext,
                                        "Sign in successful",
                                        Toast.LENGTH_LONG
                                    ).show()

                                    navController.navigate("mainscreen")
                                    viewModel.resetState()
                                }
                            }

                            login1(/*lifecycleScope = lifecycleScope,applicationContext = applicationContext, navController = navController, launcher =launcher,*/
                                state = state,
                                onSignInClick = {
                                    lifecycleScope.launch {
                                        val signInIntentSender = googleAuthUiClient.signIn()
                                        if (signInIntentSender != null) {
                                            launcher.launch(
                                                IntentSenderRequest.Builder(
                                                    signInIntentSender
                                                ).build()
                                            )
                                        } else {
                                            Log.d("Login1", "Sign-in intent sender is null.")
                                        }
                                    }

                                })
                        }


                        composable("mainscreen") {
                            MainSCreen(
                                userData = googleAuthUiClient.getSignedInUser(),
                                onSignOut = {
                                    lifecycleScope.launch {
                                        googleAuthUiClient.signOut()
                                        Toast.makeText(
                                            applicationContext,
                                            "Signed out",
                                            Toast.LENGTH_LONG
                                        ).show()

                                        navController.popBackStack()
                                    }
                                }
                            )
                        }

                    }
                }



            }
        }
    }
}



