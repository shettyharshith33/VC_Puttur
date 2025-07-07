package com.shettyharshith33.firebaseAuth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.google.firebase.database.FirebaseDatabase
import com.shettyharshith33.utils.BeforeLoginScreensNavigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(statusBarStyle = SystemBarStyle.light(Color.White.toArgb(),Color.White.toArgb()))
        setContent {
            val navController = rememberNavController()


            val context = LocalContext.current
            LaunchedEffect(Unit) {
                val db = FirebaseDatabase.getInstance().reference
                db.child("lastLoggedInTeacher").get().addOnSuccessListener { snapshot ->
                    val uid = snapshot.child("uid").value?.toString()
                    val name = snapshot.child("name").value?.toString()

                    if (!uid.isNullOrEmpty()) {
                        navController.navigate("teachers_home_screen/$uid")
                    }
                }
            }

            BeforeLoginScreensNavigation(navController)
        }
    }
}