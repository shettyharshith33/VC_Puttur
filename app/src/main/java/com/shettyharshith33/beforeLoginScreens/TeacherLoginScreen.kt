package com.shettyharshith33.beforeLoginScreens

import SetStatusBarColor
import ShowForgotPasswordDialog
import android.content.Context
import android.os.Handler
import android.os.Looper
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.firebase.auth.FirebaseAuth
import com.shettyharshith33.firebaseAuth.AuthUser
import com.shettyharshith33.utils.ResultState
import com.shettyharshith33.vcputtur.R
import com.shettyharshith33.vcputtur.ui.theme.poppinsFontFamily
import com.shettyharshith33.vcputtur.ui.theme.textColor
import com.shettyharshith33.viewmodels.AuthViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import showColoredToast

@Composable
fun TeacherLoginScreen(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel()
) {
    SetStatusBarColor(textColor, useDarkIcons = false)
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
            Spacer(modifier = Modifier.height(screenHeight * 0.09f))

            Text(
                "Vivekananda College of",
                fontSize = (screenWidth.value * 0.05f).sp,
                color = textColor,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Bold
            )
            Text(
                "Arts, Science and Commerce",
                fontSize = (screenWidth.value * 0.05f).sp,
                color = textColor,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Bold
            )
            Text(
                "(Autonomous)",
                fontSize = (screenWidth.value * 0.04f).sp,
                color = textColor,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.09f))
            Text(
                "Teacher Login",
                fontSize = 30.sp,
                color = textColor,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                "Enter your e-mail",
                fontSize = 15.sp,
                color = textColor,
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
                    unfocusedIndicatorColor = if (emailError) Color.Red else textColor,
                    focusedIndicatorColor = if (emailError) Color.Red else textColor
                )
            )

            Spacer(modifier = Modifier.height(20.dp))
            Text(
                "Enter your password",
                fontSize = 15.sp,
                color = textColor,
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
                    unfocusedIndicatorColor = if (passwordError) Color.Red else textColor,
                    focusedIndicatorColor = if (passwordError) Color.Red else textColor
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
                                        // Store user role and teacher UID (email) in SharedPreferences
                                        sharedPreferences.edit()
                                            .putString("user_role", "teacher")
                                            .putString("teacher_uid", loginEmail)
                                            .apply()

                                        Handler(Looper.getMainLooper()).post {
                                            showColoredToast(context, "Login Successful", true)
                                        }
                                        navController.navigate("teacherHomeScreen/$loginEmail") {
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
                colors = ButtonDefaults.buttonColors().copy(containerColor = textColor)
            ) {
                Text("Login", color = Color.White)
            }
        }
    }
}