package com.example.campusconnect.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.campusconnect.data.AppDatabase
import com.example.campusconnect.data.Post
import com.example.campusconnect.data.PostRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: PostRepository

    val posts: StateFlow<List<Post>>

    init {
        val database = AppDatabase.getDatabase(application)
        repository = PostRepository(database.postDao())
        posts = repository.allPosts.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

        viewModelScope.launch {
            val existing = repository.getPostsList()
            if (existing.isEmpty()) {
                repository.insert(Post(title = "Annual Hackathon 2027 - Register Now!", content = "Join us for 48 hours of coding, prizes, and networking at Hall A.", author = "Tech Club", timestamp = "2 hours ago", category = "Event"))
                repository.insert(Post(title = "Rugby Finals: Emeris Varsity College vs Collegians", content = "Come support our varsity team at the main campus stadium this Saturday at 3 PM.", author = "Sports Dept", timestamp = "4 hours ago", category = "Sports"))
                repository.insert(Post(title = "Library Workshop: Researching Made Easy", content = "Learn advanced academic search techniques at the digital lab.", author = "Library", timestamp = "Yesterday", category = "Workshop"))
                repository.insert(Post(title = "Career Fair - Hall B, 10 AM", content = "Over 40 top employers attending. Bring your updated CV and student ID.", author = "Career Center", timestamp = "2 days ago", category = "Career"))
            }
        }
    }

    fun addPost(title: String, content: String, author: String, category: String) {
        if (title.isBlank() || content.isBlank()) return
        viewModelScope.launch {
            repository.insert(
                Post(
                    title = title,
                    content = content,
                    author = author.ifBlank { "Student Council" },
                    timestamp = "Just now",
                    category = category.ifBlank { "General" }
                )
            )
        }
    }

    fun deletePost(post: Post) {
        viewModelScope.launch {
            repository.delete(post)
        }
    }
}
