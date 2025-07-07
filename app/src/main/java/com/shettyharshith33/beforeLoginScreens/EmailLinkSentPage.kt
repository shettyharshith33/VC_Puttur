//package com.shettyharshith33.beforeLoginScreens
//
//import android.content.Intent
//import android.net.Uri
//import android.widget.Toast
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.size
//import androidx.compose.material3.Button
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.text.font.FontFamily
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import com.airbnb.lottie.compose.LottieAnimation
//import com.airbnb.lottie.compose.LottieCompositionSpec
//import com.airbnb.lottie.compose.LottieConstants
//import com.airbnb.lottie.compose.rememberLottieComposition
//import com.google.firebase.auth.FirebaseAuth
//import com.shettyharshith33.vcputtur.R
//import com.shettyharshith33.vcputtur.ui.theme.newGreen
//import com.shettyharshith33.vcputtur.ui.theme.themeBlue
//
//
//
//@Composable
//fun EmailLinkSentPage(navController: NavController) {
//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ) {
//
//
//        val context = LocalContext.current
//        var isVerified by remember { mutableStateOf(false) }
//
//        // Get current user
//        val auth = FirebaseAuth.getInstance()
//        val currentUser = auth.currentUser
//
//        // Function to open the mail app
//        fun openEmailApp() {
//            val intent = Intent(Intent.ACTION_VIEW).apply {
//                data = Uri.parse("mailto:")  // Opens email app
//            }
//            context.startActivity(intent)
//        }
//
//        // Function to check verification status
//        fun checkVerificationStatus() {
//            currentUser?.reload()?.addOnCompleteListener {
//                if (currentUser.isEmailVerified) {
//                    isVerified = true
//                    Toast.makeText(context, "Email Verified!", Toast.LENGTH_SHORT).show()
//                } else {
//                    Toast.makeText(context, "Not Verified Yet. Please Check Your Email.", Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
//
//
//
//        Column(modifier = Modifier.fillMaxSize(),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally){
//            val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.otp_animation))
//            LottieAnimation(
//                composition = composition,
//                modifier = Modifier.size(300.dp),
//                iterations = LottieConstants.IterateForever
//            )
//            Spacer(modifier = Modifier.height(50.dp))
//            Text("Link Sent to your Email",
//                fontFamily = FontFamily.Serif,
//                color = themeBlue
//            )
//            Spacer(modifier = Modifier.height(15.dp))
//            Text("Click and verify your email",
//                fontFamily = FontFamily.Serif,
//                color = newGreen
//            )
//            Spacer(modifier = Modifier.height(15.dp))
//
//            if (!isVerified) {
//                // Show Check Verification Button
//                Button(onClick = { checkVerificationStatus() }) {
//                    Text("I've Verified, Check Now")
//                }
//            } else {
//                // Show Continue Button Once Verified
//                Button(onClick ={navController.navigate(BeforeLoginScreensNavigationObject.LOGIN_SCREEN)} ) {
//                    Text("Continue")
//                }
//            }
//        }
//    }
//}


package com.shettyharshith33.beforeLoginScreens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.*
import com.google.firebase.auth.FirebaseAuth
import com.shettyharshith33.utils.BeforeLoginScreensNavigationObject
import com.shettyharshith33.vcputtur.R
import com.shettyharshith33.vcputtur.ui.theme.newGreen
import com.shettyharshith33.vcputtur.ui.theme.themeBlue

@Composable
fun EmailLinkSentPage(navController: NavController) {
    val context = LocalContext.current
    var isVerified by remember { mutableStateOf(false) }
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    val auth = FirebaseAuth.getInstance()
    val currentUser = auth.currentUser

    fun checkVerificationStatus() {
        currentUser?.reload()?.addOnCompleteListener {
            if (currentUser.isEmailVerified) {
                isVerified = true
                Toast.makeText(context, "Email Verified!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Not Verified Yet. Please Check Your Email.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = screenWidth * 0.1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.otp_animation))
            LottieAnimation(
                composition = composition,
                modifier = Modifier.size(screenWidth * 0.7f),
                iterations = LottieConstants.IterateForever
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.05f))

            Text(
                "Link Sent to your Email",
                fontFamily = FontFamily.Serif,
                color = themeBlue,
                fontSize = (screenWidth.value * 0.05).sp
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.015f))

            Text(
                "Click and verify your email",
                fontFamily = FontFamily.Serif,
                color = newGreen,
                fontSize = (screenWidth.value * 0.045).sp
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.03f))

            if (!isVerified) {
                Button(onClick = { checkVerificationStatus() }) {
                    Text(
                        "I've Verified, Check Now",
                        fontSize = (screenWidth.value * 0.04).sp
                    )
                }
            } else {
                Button(onClick = {
                    navController.navigate(BeforeLoginScreensNavigationObject.LOGIN_SCREEN)
                }) {
                    Text(
                        "Continue",
                        fontSize = (screenWidth.value * 0.04).sp
                    )
                }
            }
        }
    }
}
