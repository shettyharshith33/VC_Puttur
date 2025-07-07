//package com.shettyharshith33.firebaseAuth.repository
//
//import android.util.Log
//import com.google.firebase.auth.FirebaseAuth
//import com.shettyharshith33.firebaseAuth.AuthUser
//import com.shettyharshith33.utils.ResultState
//import kotlinx.coroutines.channels.awaitClose
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.callbackFlow
//import javax.inject.Inject
//
//class AuthRepositoryImpl @Inject constructor(
//    private val authDb: FirebaseAuth
//) : AuthRepository {
//    override fun createUser(authUser: AuthUser): Flow<ResultState<String>> = callbackFlow {
//        trySend(ResultState.Loading)
//
//        authDb.createUserWithEmailAndPassword(
//            authUser.email!!,
//            authUser.password!!
//        ).addOnCompleteListener {
//            if(it.isSuccessful){
//                trySend(ResultState.Success("Account Create Successfully"))
//                Log.d("main","Current User ID: ${authDb.currentUser?.uid}")
//            }
//        }.addOnFailureListener {
//            trySend(ResultState.Failure(it))
//        }
//        awaitClose {
//            close()
//        }
//    }
//
//    override fun loginUser(authUser: AuthUser): Flow<ResultState<String>> = callbackFlow{
//        trySend(ResultState.Loading)
//        authDb.signInWithEmailAndPassword(
//            authUser.email!!,
//            authUser.password!!
//        ).addOnSuccessListener {
//            trySend(ResultState.Success("Logged-in Successfully"))
//            Log.d("main","Current User ID: ${authDb.currentUser?.uid}")
//        }.addOnFailureListener {
//            trySend(ResultState.Failure(it))
//            }
//        awaitClose {
//            close()
//        }
//    }
//}



package com.shettyharshith33.firebaseAuth.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.shettyharshith33.firebaseAuth.AuthUser
import com.shettyharshith33.utils.ResultState
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDb: FirebaseAuth
) : AuthRepository {

    //private lateinit var onVerificationCode : String

    override fun createUser(authUser: AuthUser): Flow<ResultState<String>> = callbackFlow {
        trySend(ResultState.Loading)

        authDb.createUserWithEmailAndPassword(authUser.email!!, authUser.password!!)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    trySend(ResultState.Success("Account Created Successfully"))
                    Log.d("main", "Current User ID: ${authDb.currentUser?.uid}")
                }
            }
            .addOnFailureListener { exception ->
                if (exception is FirebaseAuthUserCollisionException) {
                    trySend(ResultState.Failure(Exception("User already exists!")))
                } else {
                    trySend(ResultState.Failure(exception))
                }
            }

        awaitClose { close() }
    }

    override fun loginUser(authUser: AuthUser): Flow<ResultState<String>> = callbackFlow {
        trySend(ResultState.Loading)
        authDb.signInWithEmailAndPassword(authUser.email!!, authUser.password!!)
            .addOnSuccessListener {
                trySend(ResultState.Success("Logged in Successfully"))
                Log.d("main", "Current User ID: ${authDb.currentUser?.uid}")
            }
            .addOnFailureListener { exception ->
                trySend(ResultState.Failure(exception))
            }

        awaitClose { close() }
    }
}
