package com.example.campusconnect.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "timetable_entries")
data class TimetableEntry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val subject: String,
    val room: String,
    val day: String,
    val time: String
)
