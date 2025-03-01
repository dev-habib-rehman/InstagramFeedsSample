package com.example.instagrampostssample.data.repositories

import com.example.instagrampostssample.data.local.LocalDataSource
import com.example.instagrampostssample.data.models.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton
import com.example.instagrampostssample.data.utils.Result

@Singleton
class PostRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource
) : PostRepository {

    override suspend fun getPosts(): Flow<Result<List<Post>>> = flow {
        emit(Result.Success(localDataSource.getData(), 200))
    }
}