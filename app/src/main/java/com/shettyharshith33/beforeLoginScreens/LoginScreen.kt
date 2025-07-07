import com.shettyharshith33.beforeLoginScreens.showMsg
import com.shettyharshith33.beforeLoginScreens.triggerVibration

//package com.shettyharshith33.beforeLoginScreens
//
//import android.content.Context
//import android.os.Handler
//import android.os.Looper
//import android.widget.TextView
//import android.widget.Toast
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.border
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.AlertDialog
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.CircularProgressIndicator
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextButton
//import androidx.compose.material3.TextFieldDefaults
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.toArgb
//import androidx.compose.ui.hapticfeedback.HapticFeedbackType
//import androidx.compose.ui.platform.LocalConfiguration
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.platform.LocalHapticFeedback
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.input.PasswordVisualTransformation
//import androidx.compose.ui.text.input.VisualTransformation
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.compose.ui.window.Dialog
//import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.navigation.NavController
//import com.airbnb.lottie.compose.LottieAnimation
//import com.airbnb.lottie.compose.LottieCompositionSpec
//import com.airbnb.lottie.compose.LottieConstants
//import com.airbnb.lottie.compose.rememberLottieComposition
//import com.google.firebase.auth.FirebaseAuth
//import com.shettyharshith33.firebaseAuth.AuthUser
//import com.shettyharshith33.utils.BeforeLoginScreensNavigationObject
//import com.shettyharshith33.utils.ResultState
//import com.shettyharshith33.vcputtur.R
//import com.shettyharshith33.vcputtur.ui.theme.dodgerBlue
//import com.shettyharshith33.vcputtur.ui.theme.myGreen
//import com.shettyharshith33.vcputtur.ui.theme.netWorkRed
//import com.shettyharshith33.vcputtur.ui.theme.poppinsFontFamily
//import com.shettyharshith33.vcputtur.ui.theme.textColor
//import com.shettyharshith33.viewmodels.AuthViewModel
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//
//@Composable
//fun LoginScreen(
//    navController: NavController,
//    viewModel: AuthViewModel = hiltViewModel()
//) {
//    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.login_animation_2))
//    var loginEmail by remember { mutableStateOf("") }
//    var loginPassword by remember { mutableStateOf("") }
//    val scope = rememberCoroutineScope()
//    val context = LocalContext.current
//    var isDialog by remember { mutableStateOf(false) }
//    var emailError by remember { mutableStateOf(false) }
//    var passwordError by remember { mutableStateOf(false) }
//    val configuration = LocalConfiguration.current
//    val screenWidth = configuration.screenWidthDp.dp
//    val screenHeight = configuration.screenHeightDp.dp
//    val haptic = LocalHapticFeedback.current
//    var showForgotPasswordDialog by remember { mutableStateOf(false) }
//    var passwordVisible by remember { mutableStateOf(false) }
//
//    if (isDialog) {
//        Dialog(onDismissRequest = {}) {
//            CircularProgressIndicator()
//        }
//    }
//
//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(horizontal = screenWidth * 0.1f),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Top
//        ) {
//            Spacer(modifier = Modifier.height(screenHeight * 0.08f))
//
//            Text(
//                "Vivekananda College of",
//                fontSize = (screenWidth.value * 0.05f).sp,
//                color = dodgerBlue,
//                fontFamily = poppinsFontFamily,
//                fontWeight = FontWeight.Bold
//            )
//            Text(
//                "Arts, Science and Commerce",
//                fontSize = (screenWidth.value * 0.05f).sp,
//                color = dodgerBlue,
//                fontFamily = poppinsFontFamily,
//                fontWeight = FontWeight.Bold
//            )
//            Text(
//                "(Autonomous)",
//                fontSize = (screenWidth.value * 0.04f).sp,
//                color = dodgerBlue,
//                fontFamily = poppinsFontFamily,
//                fontWeight = FontWeight.Bold
//            )
//
//            Spacer(modifier = Modifier.height(screenHeight * 0.02f))
//
//            LottieAnimation(
//                composition = composition,
//                modifier = Modifier.size(screenWidth * 0.5f),
//                iterations = LottieConstants.IterateForever
//            )
//            Text(
//                "Login",
//                fontSize = 30.sp,
//                color = dodgerBlue,
//                fontFamily = poppinsFontFamily,
//                fontWeight = FontWeight.Bold
//            )
//            Spacer(modifier = Modifier.height(20.dp))
//
//
//            Text(
//                "Enter your e-mail",
//                fontSize = 15.sp,
//                color = dodgerBlue,
//                fontFamily = poppinsFontFamily,
//                fontWeight = FontWeight.W500
//            )
//            Spacer(modifier = Modifier.height(10.dp))
//
//            OutlinedTextField(
//                modifier = Modifier
//                    .border(0.5.dp, textColor, shape = RoundedCornerShape(5.dp))
//                    .height(50.dp)
//                    .fillMaxWidth(),
//                singleLine = true,
//                value = loginEmail,
//                onValueChange = { loginEmail = it },
//                placeholder = { Text("E-mail") },
//                colors = TextFieldDefaults.colors(
//                    unfocusedIndicatorColor = if (emailError) Color.Red else dodgerBlue,
//                    focusedIndicatorColor = if (emailError) Color.Red else dodgerBlue
//                )
//            )
//
//            Spacer(modifier = Modifier.height(20.dp))
//            Text(
//                "Enter your password",
//                fontSize = 15.sp,
//                color = dodgerBlue,
//                fontFamily = poppinsFontFamily,
//                fontWeight = FontWeight.W500
//            )
//            Spacer(modifier = Modifier.height(10.dp))
//
//            OutlinedTextField(
//                modifier = Modifier
//                    .border(0.5.dp, textColor, shape = RoundedCornerShape(5.dp))
//                    .height(50.dp)
//                    .fillMaxWidth(),
//                value = loginPassword,
//                onValueChange = { loginPassword = it },
//                placeholder = { Text("Password") },
//                visualTransformation =
//                if (passwordVisible)
//                    VisualTransformation.None
//                else {
//                    PasswordVisualTransformation()
//                },
//                trailingIcon = {
//                    val icon = if (passwordVisible)
//                        R.drawable.visibilty
//                    else
//                        R.drawable.visibilty_off
//
//                    Image(painterResource(icon), contentDescription = "",
//                        modifier = Modifier.clickable { passwordVisible =!passwordVisible })
//                },
//                singleLine = true,
//                colors = TextFieldDefaults.colors(
//                    unfocusedIndicatorColor = if (passwordError) Color.Red else dodgerBlue,
//                    focusedIndicatorColor = if (passwordError) Color.Red else dodgerBlue
//                )
//            )
//
//            if (showForgotPasswordDialog) {
//                ShowForgotPasswordDialog(
//                    context = context,
//                    onDismiss = { showForgotPasswordDialog = false })
//            }
//
//            TextButton(onClick = { showForgotPasswordDialog = true }) {
//                Text("Forgot Password?", color = Color.Blue)
//            }
//
//            Button(
//                onClick = {
//                    if (loginEmail.isEmpty() || loginPassword.isEmpty()) {
//                        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
//                        emailError = true
//                        passwordError = true
//                        context.showMsg("Email and Password cannot be empty")
//                        triggerVibration(context)
//                        return@Button
//                    }
//                    scope.launch(Dispatchers.Main) {
//                        viewModel.loginUser(AuthUser(loginEmail, loginPassword)).collect { result ->
//                            isDialog = when (result) {
//                                is ResultState.Success -> {
//                                    val firebaseUser = FirebaseAuth.getInstance().currentUser
//                                    if (firebaseUser != null && firebaseUser.isEmailVerified) {
//                                        Handler(Looper.getMainLooper()).post {
//                                            showColoredToast(context, "Login Successful", true)
//                                        }
//                                        navController.navigate(BeforeLoginScreensNavigationObject.HOME_SCREEN)
//                                    } else {
//                                        Handler(Looper.getMainLooper()).post {
//                                            showColoredToast(context, "Email not verified", false)
//                                        }
//                                        FirebaseAuth.getInstance().signOut()
//                                    }
//                                    false
//                                }
//
//                                is ResultState.Failure -> {
//                                    val errorMsg = result.msg.toString().lowercase()
//                                    val errorMessage = when {
//                                        "password is invalid" in errorMsg -> "Incorrect Password"
//                                        "no user record" in errorMsg || "there is no user" in errorMsg -> "Email Not Registered"
//                                        "network error" in errorMsg -> "Check Your Internet Connection 🌐"
//                                        else -> "Email or Password is Incorrect"
//                                    }
//                                    Handler(Looper.getMainLooper()).post {
//                                        showColoredToast(context, errorMessage, false)
//                                    }
//                                    false
//                                }
//
//                                is ResultState.Loading -> true
//                            }
//                        }
//                    }
//                },
//                modifier = Modifier.width(150.dp),
//                colors = ButtonDefaults.buttonColors().copy(containerColor = dodgerBlue)
//            ) {
//                Text("Login", color = Color.White)
//            }
//        }
//    }
//}
//

















import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.firebase.auth.FirebaseAuth
import com.shettyharshith33.firebaseAuth.AuthUser
import com.shettyharshith33.utils.BeforeLoginScreensNavigationObject
import com.shettyharshith33.utils.ResultState
import com.shettyharshith33.vcputtur.R
import com.shettyharshith33.vcputtur.ui.theme.dodgerBlue
import com.shettyharshith33.vcputtur.ui.theme.myGreen
import com.shettyharshith33.vcputtur.ui.theme.netWorkRed
import com.shettyharshith33.vcputtur.ui.theme.poppinsFontFamily
import com.shettyharshith33.vcputtur.ui.theme.textColor
import com.shettyharshith33.viewmodels.AuthViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.login_animation_2))
    var loginEmail by remember { mutableStateOf("") }
    var loginPassword by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    var isDialog by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    val haptic = LocalHapticFeedback.current
    var showForgotPasswordDialog by remember { mutableStateOf(false) }
    var passwordVisible by remember { mutableStateOf(false) }

    if (isDialog) {
        Dialog(onDismissRequest = {}) {
            CircularProgressIndicator()
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
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(screenHeight * 0.08f))

            Text(
                "Vivekananda College of",
                fontSize = (screenWidth.value * 0.05f).sp,
                color = dodgerBlue,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Bold
            )
            Text(
                "Arts, Science and Commerce",
                fontSize = (screenWidth.value * 0.05f).sp,
                color = dodgerBlue,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Bold
            )
            Text(
                "(Autonomous)",
                fontSize = (screenWidth.value * 0.04f).sp,
                color = dodgerBlue,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.02f))

            LottieAnimation(
                composition = composition,
                modifier = Modifier.size(screenWidth * 0.5f),
                iterations = LottieConstants.IterateForever
            )
            Text(
                "Login",
                fontSize = 30.sp,
                color = dodgerBlue,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                "Enter your e-mail",
                fontSize = 15.sp,
                color = dodgerBlue,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.W500
            )
            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                modifier = Modifier
                    .border(0.5.dp, textColor, shape = RoundedCornerShape(5.dp))
                    .height(50.dp)
                    .fillMaxWidth(),
                singleLine = true,
                value = loginEmail,
                onValueChange = { loginEmail = it },
                placeholder = { Text("E-mail") },
                colors = TextFieldDefaults.colors(
                    unfocusedIndicatorColor = if (emailError) Color.Red else dodgerBlue,
                    focusedIndicatorColor = if (emailError) Color.Red else dodgerBlue
                )
            )

            Spacer(modifier = Modifier.height(20.dp))
            Text(
                "Enter your password",
                fontSize = 15.sp,
                color = dodgerBlue,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.W500
            )
            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                modifier = Modifier
                    .border(0.5.dp, textColor, shape = RoundedCornerShape(5.dp))
                    .height(50.dp)
                    .fillMaxWidth(),
                value = loginPassword,
                onValueChange = { loginPassword = it },
                placeholder = { Text("Password") },
                visualTransformation =
                if (passwordVisible)
                    VisualTransformation.None
                else {
                    PasswordVisualTransformation()
                },
                trailingIcon = {
                    val icon = if (passwordVisible)
                        R.drawable.visibilty
                    else
                        R.drawable.visibilty_off

                    Image(painterResource(icon), contentDescription = "",
                        modifier = Modifier.clickable { passwordVisible = !passwordVisible })
                },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    unfocusedIndicatorColor = if (passwordError) Color.Red else dodgerBlue,
                    focusedIndicatorColor = if (passwordError) Color.Red else dodgerBlue
                )
            )

            if (showForgotPasswordDialog) {
                ShowForgotPasswordDialog(
                    context = context,
                    onDismiss = { showForgotPasswordDialog = false })
            }

            TextButton(onClick = { showForgotPasswordDialog = true }) {
                Text("Forgot Password?", color = Color.Blue)
            }

            Button(
                onClick = {
                    if (loginEmail.isEmpty() || loginPassword.isEmpty()) {
                        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                        emailError = true
                        passwordError = true
                        context.showMsg("Email and Password cannot be empty")
                        triggerVibration(context)
                        return@Button
                    }
                    scope.launch(Dispatchers.Main) {
                        viewModel.loginUser(AuthUser(loginEmail, loginPassword)).collect { result ->
                            isDialog = when (result) {
                                is ResultState.Success -> {
                                    val firebaseUser = FirebaseAuth.getInstance().currentUser
                                    if (firebaseUser != null && firebaseUser.isEmailVerified) {
                                        // Store user role as student in SharedPreferences
                                        sharedPreferences.edit()
                                            .putString("user_role", "student")
                                            .apply()

                                        Handler(Looper.getMainLooper()).post {
                                            showColoredToast(context, "Login Successful", true)
                                        }
                                        navController.navigate(BeforeLoginScreensNavigationObject.HOME_SCREEN) {
                                            popUpTo(0)
                                        }
                                    } else {
                                        Handler(Looper.getMainLooper()).post {
                                            showColoredToast(context, "Email not verified", false)
                                        }
                                        FirebaseAuth.getInstance().signOut()
                                        sharedPreferences.edit().clear().apply()
                                    }
                                    false
                                }

                                is ResultState.Failure -> {
                                    val errorMsg = result.msg.toString().lowercase()
                                    val errorMessage = when {
                                        "password is invalid" in errorMsg -> "Incorrect Password"
                                        "no user record" in errorMsg || "there is no user" in errorMsg -> "Email Not Registered"
                                        "network error" in errorMsg -> "Check Your Internet Connection 🌐"
                                        else -> "Email or Password is Incorrect"
                                    }
                                    Handler(Looper.getMainLooper()).post {
                                        showColoredToast(context, errorMessage, false)
                                    }
                                    false
                                }

                                is ResultState.Loading -> true
                            }
                        }
                    }
                },
                modifier = Modifier.width(150.dp),
                colors = ButtonDefaults.buttonColors().copy(containerColor = dodgerBlue)
            ) {
                Text("Login", color = Color.White)
            }
        }
    }
}






















fun showColoredToast(context: Context, message: String, isSuccess: Boolean) {
    try {
        val toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
        val view = toast.view

        if (view != null) {
            val bgColor = if (isSuccess) myGreen.toArgb() else netWorkRed.toArgb()
            view.setBackgroundColor(bgColor)

            val textView = view.findViewById<TextView>(android.R.id.message)
            textView?.setTextColor(if (isSuccess) Color.Black.toArgb() else Color.White.toArgb())

            toast.show()
        } else {
            throw Exception("Custom toast not supported")
        }
    } catch (e: Exception) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}

fun resetPassword(context: Context, email: String) {
    if (email.isBlank()) {
        Handler(Looper.getMainLooper()).post {
            showColoredToast(context, "Please enter an email", false)
        }
        return
    }

    FirebaseAuth.getInstance().sendPasswordResetEmail(email)
        .addOnSuccessListener {
            showColoredToast(context, "Reset link sent! Check your email", true)
        }
        .addOnFailureListener {
            Handler(Looper.getMainLooper()).post {
                showColoredToast(context, "Failed to send reset link", false)
            }
        }
}




@Composable
fun ShowForgotPasswordDialog(
    context: Context,
    onDismiss: () -> Unit
) {
    var email by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = {},
        title = { Text("Reset Password") },
        text = {
            Column {
                Text("Enter your registered email to receive a password reset link.")
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    singleLine = true
                )
            }
        },
        confirmButton = {
            TextButton(onClick = {
                resetPassword(context, email)
                onDismiss()
            }) {
                Text("Send Reset Link")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Cancel")
            }
        }
    )
}



