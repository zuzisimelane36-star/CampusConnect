package com.example.campusconnect.data

import kotlinx.coroutines.flow.Flow

class TimetableRepository(private val timetableDao: TimetableDao) {
    val allEntries: Flow<List<TimetableEntry>> = timetableDao.getAllEntries()

    suspend fun getEntriesList(): List<TimetableEntry> = timetableDao.getAllEntriesList()

    suspend fun insert(entry: TimetableEntry) {
        timetableDao.insertEntry(entry)
    }

    suspend fun delete(entry: TimetableEntry) {
        timetableDao.deleteEntry(entry)
    }
}
