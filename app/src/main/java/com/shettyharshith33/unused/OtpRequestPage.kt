package com.shettyharshith33.unused

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.shettyharshith33.utils.BeforeLoginScreensNavigationObject
import com.shettyharshith33.vcputtur.R
import com.shettyharshith33.vcputtur.ui.theme.buttonYellow
import com.shettyharshith33.vcputtur.ui.theme.textColor
import com.shettyharshith33.vcputtur.ui.theme.themeBlue
import com.shettyharshith33.viewmodels.AuthViewModel
import java.util.concurrent.TimeUnit


@Composable
fun ShowLoadingDialog(){
    Dialog(onDismissRequest = { /*TODO*/ }) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .padding(20.dp)
        ) {
            CircularProgressIndicator(
                modifier = Modifier.scale(1.5f),
                color = buttonYellow
            )
        }
    }
}

@Composable
fun OtpRequestPage(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel()
) {
    var phoneNumber by remember { mutableStateOf("") }
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current


    //
    val auth= FirebaseAuth.getInstance()
    var verificationId by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }
    if (isLoading){
        ShowLoadingDialog()
    }
    //


    val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            Toast.makeText(context, "OTP Auto-Verified!", Toast.LENGTH_SHORT).show()
        }

        override fun onVerificationFailed(e: FirebaseException) {
            isLoading = false
            Toast.makeText(context, "OTP Failed: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
            Log.e("OTP Error", "Verification failed", e)
        }

        override fun onCodeSent(id: String, token: PhoneAuthProvider.ForceResendingToken) {
            isLoading = false
            verificationId = id
            Toast.makeText(context, "OTP Sent!", Toast.LENGTH_SHORT).show()
            navController.navigate("otpVerificationPage/$id") // Passing verification ID
        }
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(100.dp))

        Text(
            "Vivekananda College of",
            fontSize = 25.sp,
            color = textColor,
            fontWeight = FontWeight.Bold
        )
        Text(
            "Arts, Science and Commerce",
            fontSize = 25.sp,
            color = textColor,
            fontWeight = FontWeight.Bold
        )
        Text("(Autonomous)", fontSize = 18.sp, color = textColor, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(15.dp))
        Image(
            modifier = Modifier.size(100.dp),
            painter = painterResource(R.drawable.collegelogo),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.height(90.dp))
        Text("Verification", color = textColor, fontSize = 30.sp, fontWeight = FontWeight.W500)
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            "Enter Your Phone Number",
            color = textColor,
            fontSize = 20.sp,
            fontWeight = FontWeight.W400
        )
        Spacer(modifier = Modifier.height(10.dp))

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    leadingIcon = { Icon(imageVector = Icons.Default.Phone, contentDescription = "Phone Icon") },
                    value = phoneNumber,
                    prefix = { Text("+91") },
                    onValueChange = {
                        if (it.length <=10 )
                        {
                            phoneNumber = it
                        }
                    },
                    label = { Text("Phone Number") },
                    placeholder = { Text("Enter phone number", color = Color.LightGray) },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    shape = RoundedCornerShape(15.dp)
                )
                Spacer(modifier = Modifier.height(50.dp))

                Button(
                    modifier = Modifier
                        .width(130.dp)
                        .height(50.dp),
                    onClick = {
                        if (phoneNumber.length == 10)
                        {
                            navController.navigate(BeforeLoginScreensNavigationObject.OTP_VERIFICATION_PAGE)
                            isLoading = true
                            val fullPhoneNumber = "+91$phoneNumber" // Change country code if needed
                            val options = PhoneAuthOptions.newBuilder(auth)
                                .setPhoneNumber(fullPhoneNumber)
                                .setTimeout(60L, TimeUnit.SECONDS)
                                .setActivity(context as androidx.activity.ComponentActivity)
                                .setCallbacks(callbacks)
                                .build()
                            PhoneAuthProvider.verifyPhoneNumber(options)
                        } else {
                            Toast.makeText(context, "Enter valid phone number!", Toast.LENGTH_SHORT).show()
                        }
                    },
                    enabled = !isLoading,
                    colors = ButtonDefaults.buttonColors(containerColor = themeBlue)
                ) {
                    Text("Get OTP", color = Color.White)
                }
            }
        }
    }
}


