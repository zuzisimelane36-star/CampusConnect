package com.example.campusconnect.ui.timetable

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.campusconnect.data.AppDatabase
import com.example.campusconnect.data.TimetableEntry
import com.example.campusconnect.data.TimetableRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TimetableViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: TimetableRepository

    val timetableEntries: StateFlow<List<TimetableEntry>>

    init {
        val database = AppDatabase.getDatabase(application)
        repository = TimetableRepository(database.timetableDao())
        timetableEntries = repository.allEntries.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
        
        viewModelScope.launch {
            val existing = repository.getEntriesList()
            if (existing.isEmpty()) {
                repository.insert(TimetableEntry(subject = "Data Structures & Algorithms", room = "Lab 3B", day = "Monday", time = "09:00 AM"))
                repository.insert(TimetableEntry(subject = "Software Engineering Principles", room = "Hall 201", day = "Tuesday", time = "11:00 AM"))
                repository.insert(TimetableEntry(subject = "Database Management Systems", room = "Room 405", day = "Wednesday", time = "02:00 PM"))
                repository.insert(TimetableEntry(subject = "Mobile App Development", room = "Lab 1A", day = "Thursday", time = "10:00 AM"))
            }
        }
    }

    fun addEntry(subject: String, room: String, day: String, time: String) {
        if (subject.isBlank() || room.isBlank() || day.isBlank() || time.isBlank()) return
        viewModelScope.launch {
            repository.insert(TimetableEntry(subject = subject, room = room, day = day, time = time))
        }
    }

    fun deleteEntry(entry: TimetableEntry) {
        viewModelScope.launch {
            repository.delete(entry)
        }
    }
}
