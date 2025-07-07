package com.shettyharshith33.viewmodels

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.shettyharshith33.firebaseAuth.AuthUser
import com.shettyharshith33.firebaseAuth.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repo: AuthRepository
) : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    fun createUser(authUser: AuthUser) = repo.createUser(authUser)

    fun loginUser(authUser: AuthUser) = repo.loginUser(authUser)

    fun getCurrentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }

    fun signInWithGoogle(idToken: String, onResult: (Boolean) -> Unit) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                onResult(task.isSuccessful)
            }
    }
}