package com.example.campusconnect.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students")
data class Student(
    @PrimaryKey val email: String,
    val name: String,
    val passwordHash: String,
    val registrationDate: Long = System.currentTimeMillis(),
    val studentId: String = "STU12345",
    val course: String = "Bachelor of Science in Computer Science",
    val yearOfStudy: Int = 3,
    val profilePictureUri: String? = null
)
