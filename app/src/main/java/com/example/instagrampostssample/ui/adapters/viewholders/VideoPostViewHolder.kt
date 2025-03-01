package com.example.instagrampostssample.ui.adapters.viewholders

import androidx.media3.common.util.UnstableApi
import com.example.instagrampostssample.data.models.Post
import com.example.instagrampostssample.databinding.ItemPostVideoBinding
import com.example.instagrampostssample.ui.adapters.viewholders.helpers.VideoPlayerHelper
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

@UnstableApi
class VideoPostViewHolder @AssistedInject constructor(
    @Assisted private val binding: ItemPostVideoBinding,
    private val videoPlayerHelper: VideoPlayerHelper
) : BaseViewHolder(binding.root) {

    @AssistedFactory
    interface Factory {
        fun create(binding: ItemPostVideoBinding): VideoPostViewHolder
    }

    override fun bind(post: Post) {
        if (post is Post.VideoPost) {
            videoPlayerHelper.initializePlayer(binding.pvVideoPost, post.videoUrl)
        }
    }

    override fun releaseResources() {
        videoPlayerHelper.releasePlayer()
        binding.pvVideoPost.player = null
    }

    override fun playVideo() {
        videoPlayerHelper.playVideo()
    }

    override fun pauseVideo() {
        videoPlayerHelper.pauseVideo()
    }
}

