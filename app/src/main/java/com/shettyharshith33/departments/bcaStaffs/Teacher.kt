package com.shettyharshith33.departments.bcaStaffs

import com.google.firebase.Timestamp

data class Teacher(
    val name: String = "",
    val qualification: String = "",
    val experience: String = "",
    val imageUrl: String = "",
    val desig : String ="",
    val bcaExplanation : String="",
    val status : String="",
    val lastUpdated: Timestamp? = null
)

