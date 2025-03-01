package com.example.instagrampostssample.data.di

import android.content.Context
import androidx.annotation.OptIn
import androidx.media3.common.util.UnstableApi
import androidx.media3.database.StandaloneDatabaseProvider
import androidx.media3.datasource.DataSource
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.datasource.cache.Cache
import androidx.media3.datasource.cache.CacheDataSource
import androidx.media3.datasource.cache.LeastRecentlyUsedCacheEvictor
import androidx.media3.datasource.cache.SimpleCache
import com.example.instagrampostssample.ui.adapters.viewholders.helpers.ExoPlayerProvider
import com.example.instagrampostssample.ui.adapters.viewholders.helpers.ImageLoader
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.io.File
import javax.inject.Singleton

@Module
@OptIn(UnstableApi::class)
@InstallIn(SingletonComponent::class)
object MediaModule {

    @Provides
    @Singleton
    fun provideDatabaseProvider(@ApplicationContext context: Context): StandaloneDatabaseProvider {
        return StandaloneDatabaseProvider(context)
    }

    @Provides
    @Singleton
    fun provideMediaCache(
        @ApplicationContext context: Context,
        databaseProvider: StandaloneDatabaseProvider
    ): Cache {
        return SimpleCache(
            File(context.cacheDir, "media3_cache"),
            LeastRecentlyUsedCacheEvictor(50 * 1024 * 1024), // 50MB
            databaseProvider
        )
    }

    @Provides
    @Singleton
    fun provideDataSourceFactory(
        @ApplicationContext context: Context,
        cache: Cache
    ): DataSource.Factory {
        return CacheDataSource.Factory()
            .setCache(cache)
            .setUpstreamDataSourceFactory(DefaultDataSource.Factory(context))
    }

    @Provides
    @Singleton
    fun providePlayerProvider(
        @ApplicationContext context: Context,
        dataSourceFactory: DataSource.Factory
    ): ExoPlayerProvider {
        return ExoPlayerProvider(context, dataSourceFactory)
    }

    @Provides
    fun provideImageLoader(): ImageLoader {
        return ImageLoader()
    }
}