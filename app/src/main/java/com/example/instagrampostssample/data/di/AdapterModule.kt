package com.example.instagrampostssample.data.di

import androidx.annotation.OptIn
import androidx.media3.common.util.UnstableApi
import com.example.instagrampostssample.ui.adapters.FeedListAdapter
import com.example.instagrampostssample.ui.adapters.viewholders.ImagePostViewHolder
import com.example.instagrampostssample.ui.adapters.viewholders.MixedPostViewHolder
import com.example.instagrampostssample.ui.adapters.viewholders.VideoPostViewHolder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
@UnstableApi
object AdapterModule {
    @Provides
    fun provideFeedAdapter(
        imageFactory: ImagePostViewHolder.Factory,
        videoFactory: VideoPostViewHolder.Factory,
        mixedFactory: MixedPostViewHolder.Factory
    ): FeedListAdapter {
        return FeedListAdapter(imageFactory, videoFactory, mixedFactory)
    }
}