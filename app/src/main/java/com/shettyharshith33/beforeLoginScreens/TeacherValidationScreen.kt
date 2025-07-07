package com.shettyharshith33.beforeLoginScreens

import SetStatusBarColor
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.shettyharshith33.utils.BeforeLoginScreensNavigationObject
import com.shettyharshith33.vcputtur.ui.theme.poppinsFontFamily
import com.shettyharshith33.vcputtur.ui.theme.textColor

@Composable
fun TeacherValidationScreen(navController: NavController)
{
    val secretKey = "1234"
    val haptic = LocalHapticFeedback.current
    val context = LocalContext.current

    SetStatusBarColor(textColor,useDarkIcons = false)

    var enteredKey by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Enter Secret Key",
            fontFamily = poppinsFontFamily,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = enteredKey,
            onValueChange = { enteredKey = it },
            placeholder = { Text("Secret Key ")},
            modifier = Modifier.fillMaxWidth(0.8f),
            singleLine = true,
            colors = TextFieldDefaults.colors().copy(
                unfocusedIndicatorColor = textColor,
                focusedIndicatorColor = textColor
            )
        )
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            colors = ButtonDefaults.buttonColors().copy(
                containerColor = textColor
            ),
            onClick = {
                if (enteredKey == secretKey)
                {
                    navController.navigate(BeforeLoginScreensNavigationObject.TEACHER_LOGIN_SCREEN)
                }
                else if(enteredKey.isEmpty()){
                    haptic.performHapticFeedback(
                        HapticFeedbackType.TextHandleMove
                    )
                    context.showMsg("Secret key is mandatory!")
                    triggerVibration(context)
                }
                else
                {
                    haptic.performHapticFeedback(
                        HapticFeedbackType.TextHandleMove
                    )
                    context.showMsg("Incorrect secret key, Can't let you in!")
                    triggerVibration(context)
                }
                      },
            modifier = Modifier.fillMaxWidth(0.5f)
        ) {
            Text("Validate",
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.SemiBold)
        }
    }

}