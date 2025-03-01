package com.example.instagrampostssample.data.repositories

import com.example.instagrampostssample.data.models.Post
import kotlinx.coroutines.flow.Flow
import com.example.instagrampostssample.data.utils.Result

interface PostRepository {
    suspend fun getPosts(): Flow<Result<List<Post>>>
}
