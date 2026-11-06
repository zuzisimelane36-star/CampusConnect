package com.example.campusconnect.data

import kotlinx.coroutines.flow.Flow

class PostRepository(private val postDao: PostDao) {
    val allPosts: Flow<List<Post>> = postDao.getAllPosts()

    suspend fun getPostsList(): List<Post> = postDao.getAllPostsList()

    suspend fun insert(post: Post) {
        postDao.insertPost(post)
    }

    suspend fun delete(post: Post) {
        postDao.deletePost(post)
    }
}
