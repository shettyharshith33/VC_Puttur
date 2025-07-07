package com.shettyharshith33.utils

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.identity.SignInCredential
import com.google.firebase.auth.FirebaseAuth
import com.shettyharshith33.vcputtur.R
import kotlinx.coroutines.tasks.await

class GoogleAuthUIClient(
    private val context: Context,
    private val oneTapClient: SignInClient
) {
    private val signInRequest = BeginSignInRequest.builder()
        .setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                .setServerClientId(context.getString(R.string.default_web_client_id)) // IMPORTANT!
                .setFilterByAuthorizedAccounts(false)
                .build()
        )
        .setAutoSelectEnabled(true)
        .build()

    suspend fun signIn(): IntentSender? {
        return try {
            oneTapClient.beginSignIn(signInRequest)
                .await()
                .pendingIntent
                .intentSender
        } catch (e: Exception) {
            null
        }
    }

    suspend fun signInWithIntent(intent: Intent): SignInCredential? {
        return try {
            oneTapClient.getSignInCredentialFromIntent(intent)
        } catch (e: Exception) {
            null
        }
    }

    fun signOut() {
        FirebaseAuth.getInstance().signOut()
    }
}