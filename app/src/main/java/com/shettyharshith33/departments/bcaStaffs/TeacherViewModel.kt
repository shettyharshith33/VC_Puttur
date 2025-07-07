package com.shettyharshith33.departments.bcaStaffs

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class TeacherViewModel : ViewModel() {
    var teachers by mutableStateOf<List<Teacher>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun fetchBCATeachers() {

        isLoading = true
        errorMessage = null

        Firebase.firestore.collection("bcaStaffs")
            .get()
            .addOnSuccessListener { result ->
                teachers = result.documents.mapNotNull { it.toObject(Teacher::class.java) }
                isLoading = false
            }
            .addOnFailureListener {
                isLoading = true
                errorMessage= "Unable to fetch data! Make sure that you are connected to the internet."
            }
    }

    fun fetchBCOMTeachers() {
        isLoading = true
        errorMessage = null
        Firebase.firestore.collection("bcomStaffs")
            .get()
            .addOnSuccessListener { result ->
                teachers = result.documents.mapNotNull { it.toObject(Teacher::class.java) }
                isLoading = false
            }
            .addOnFailureListener {
                isLoading = false
                errorMessage= "Unable to fetch data! Make sure that you are connected to the internet."
            }
    }


    fun fetchHistoryTeachers() {
        isLoading = true
        errorMessage = null
        Firebase.firestore.collection("historyStaffs")
            .get()
            .addOnSuccessListener { result ->
                teachers = result.documents.mapNotNull { it.toObject(Teacher::class.java) }
                isLoading = false
            }
            .addOnFailureListener {
                isLoading = false
                errorMessage= "Unable to fetch data! Make sure that you are connected to the internet."
            }
    }
}
