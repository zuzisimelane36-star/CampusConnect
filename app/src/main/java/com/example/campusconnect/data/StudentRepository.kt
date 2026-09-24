package com.example.campusconnect.data

class StudentRepository(private val studentDao: StudentDao) {

    suspend fun authenticateOffline(email: String, passwordHash: String): Boolean {
        val student = studentDao.getStudentByEmail(email)
        return student != null && student.passwordHash == passwordHash
    }

    suspend fun registerStudentLocal(name: String, email: String, passwordHash: String): Boolean {
        val existing = studentDao.getStudentByEmail(email)
        if (existing != null) return false // Already exists
        
        val student = Student(email = email, name = name, passwordHash = passwordHash)
        studentDao.insertStudent(student)
        return true
    }

    suspend fun getStudentProfile(email: String): Student? {
        return studentDao.getStudentByEmail(email)
    }
}
