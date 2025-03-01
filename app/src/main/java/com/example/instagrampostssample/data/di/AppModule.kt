package com.example.instagrampostssample.data.di

import android.content.Context
import com.example.instagrampostssample.data.local.LocalDataSource
import com.example.instagrampostssample.data.repositories.PostRepository
import com.example.instagrampostssample.data.repositories.PostRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideLocalDataSource(@ApplicationContext context: Context): LocalDataSource {
        return LocalDataSource(context)
    }

    @Provides
    @Singleton
    fun providePostRepository(localDataSource: LocalDataSource): PostRepository {
        return PostRepositoryImpl(localDataSource)
    }
}