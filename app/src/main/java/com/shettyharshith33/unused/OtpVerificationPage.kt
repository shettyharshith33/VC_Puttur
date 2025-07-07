package com.shettyharshith33.unused

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.shettyharshith33.vcputtur.R
import com.shettyharshith33.vcputtur.ui.theme.themeBlue
import com.shettyharshith33.viewmodels.AuthViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun OtpVerificationPage(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var otpValues by remember { mutableStateOf(List(6) { "" }) }
    val focusRequesters = remember { List(6) { FocusRequester() } }
    var timeLeft by remember { mutableIntStateOf(60) }
    var isOtpExpired by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.otp_animation))
    var showErrorToast by remember { mutableStateOf(false) }

    LaunchedEffect(timeLeft) {
        if (timeLeft > 0) {
            delay(1000L)
            timeLeft--
        } else {
            isOtpExpired = true
        }
    }



    LaunchedEffect(showErrorToast) {
        if (showErrorToast) {
            Toast.makeText(context, "Error verifying OTP. Please try again!", Toast.LENGTH_SHORT)
                .show()
            showErrorToast = false
        }
    }


//
//    val animatedScale by animateFloatAsState(
//            targetValue = scale, animationSpec = tween(
//                durationMillis = 1000, // Duration for one cycle
//                easing = LinearOutSlowInEasing
//            )
//        )
//
//        // Continuous loop
//        LaunchedEffect(Unit) {
//            while (true) {
//                scale = 1.1f
//                delay(1000L)
//                scale = 1f
//                delay(1000L)
//            }
//        }


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(90.dp))


        LottieAnimation(
            composition = composition,
            modifier = Modifier.size(200.dp),
            iterations = LottieConstants.IterateForever
        )



        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Enter OTP", fontSize = 20.sp)
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = if (!isOtpExpired) "OTP sent. Valid for $timeLeft sec"
            else "OTP expired! Resend OTP",
            fontSize = 14.sp,
            color = if (isOtpExpired) Color.Red else Color.Gray,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            otpValues.forEachIndexed { index, value ->
                OutlinedTextField(
                    value = value,
                    onValueChange = { newValue ->
                        if (newValue.length <= 1) {
                            otpValues = otpValues.toMutableList().apply { set(index, newValue) }
                            if (newValue.isNotEmpty() && index < 5) {
                                focusRequesters[index + 1].requestFocus()
                            }
                        }
                    },
                    modifier = Modifier
                        .width(45.dp)
                        .focusRequester(focusRequesters[index]),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                coroutineScope.launch {
                    if (isOtpExpired) {
                        timeLeft = 60
                        isOtpExpired = false
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = themeBlue),
            modifier = Modifier
                .padding(16.dp)
                .height(50.dp)
                .width(200.dp)
        ) {
            Text(
                text = if (isOtpExpired) "Resend OTP" else "Verify OTP",
                color = Color.White,
                fontSize = 18.sp
            )
        }
    }
}
