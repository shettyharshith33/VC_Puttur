//package com.shettyharshith33.beforeLoginScreens
//
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.navigation.NavController
//import com.google.firebase.auth.FirebaseAuth
//import com.shettyharshith33.utils.BeforeLoginScreensNavigationObject
//
//
//@Composable
//fun AuthCheckScreen(navController: NavController) {
//    val auth = FirebaseAuth.getInstance()
//    val isUserLoggedIn = auth.currentUser != null
//
//    LaunchedEffect(Unit){
//        if (isUserLoggedIn) {
//            navController.navigate(BeforeLoginScreensNavigationObject.HOME_SCREEN) {
//                popUpTo(0) // Clear backstack
//            }
//        } else {
//            navController.navigate(BeforeLoginScreensNavigationObject.ONBOARDING_SCREEN) {
//                popUpTo(0)
//            }
//        }
//    }
//}
//
//
//
//
//
//
//


package com.shettyharshith33.beforeLoginScreens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.shettyharshith33.utils.BeforeLoginScreensNavigationObject

@Composable
fun AuthCheckScreen(navController: NavController) {
    val auth = FirebaseAuth.getInstance()
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    var isChecking by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        val currentUser = auth.currentUser
        Log.d("AuthCheckScreen", "Current User: ${currentUser?.uid}, Email: ${currentUser?.email}")

        if (currentUser == null) {
            Log.d("AuthCheckScreen", "No user logged in, navigating to ONBOARDING_SCREEN")
            navController.navigate(BeforeLoginScreensNavigationObject.ONBOARDING_SCREEN) {
                popUpTo(0)
            }
            isChecking = false
        } else {
            val userRole = sharedPreferences.getString("user_role", "") ?: ""
            val teacherUid = sharedPreferences.getString("teacher_uid", "") ?: ""
            Log.d("AuthCheckScreen", "User Role: $userRole, Teacher UID: $teacherUid")

            when (userRole) {
                "student" -> {
                    navController.navigate(BeforeLoginScreensNavigationObject.HOME_SCREEN) {
                        popUpTo(0)
                    }
                }
                "teacher" -> {
                    if (teacherUid.isNotEmpty()) {
                        navController.navigate("teacherHomeScreen/$teacherUid") {
                            popUpTo(0)
                        }
                    } else {
                        // Invalid teacher session, sign out and clear preferences
                        auth.signOut()
                        sharedPreferences.edit().clear().apply()
                        context.showMsg("No teacher email found. Please log in again.")
                        navController.navigate(BeforeLoginScreensNavigationObject.ONBOARDING_SCREEN) {
                            popUpTo(0)
                        }
                    }
                }
                else -> {
                    // Invalid or missing role, sign out and clear preferences
                    auth.signOut()
                    sharedPreferences.edit().clear().apply()
                    context.showMsg("Invalid session. Please log in again.")
                    navController.navigate(BeforeLoginScreensNavigationObject.ONBOARDING_SCREEN) {
                        popUpTo(0)
                    }
                }
            }
            isChecking = false
        }
    }

    if (isChecking) {
        CircularProgressIndicator(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(align = Alignment.Center)
        )
    }
}

fun Context.showMsg(message: String) {
    android.widget.Toast.makeText(this, message, android.widget.Toast.LENGTH_SHORT).show()
}