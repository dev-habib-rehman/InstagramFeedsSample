package com.example.instagrampostssample.ui.adapters.viewholders

import androidx.media3.common.util.UnstableApi
import com.example.instagrampostssample.data.models.Post
import com.example.instagrampostssample.databinding.ItemPostMixedBinding
import com.example.instagrampostssample.ui.adapters.viewholders.helpers.ImageLoader
import com.example.instagrampostssample.ui.adapters.viewholders.helpers.VideoPlayerHelper
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

@UnstableApi
class MixedPostViewHolder @AssistedInject constructor(
    @Assisted private val binding: ItemPostMixedBinding,
    private val videoPlayerHelper: VideoPlayerHelper,
    private val imageLoader: ImageLoader
) : BaseViewHolder(binding.root) {

    @AssistedFactory
    interface Factory {
        fun create(binding: ItemPostMixedBinding): MixedPostViewHolder
    }

    override fun bind(post: Post) {
        if (post is Post.MixedPost) {
            imageLoader.loadImage(binding.root.context, binding.ivImageMixedPost, post.imageUrl)
            videoPlayerHelper.initializePlayer(binding.pvVideoMixedPost, post.videoUrl)
        }
    }

    override fun releaseResources() {
        videoPlayerHelper.releasePlayer()
        binding.pvVideoMixedPost.player = null
    }

    override fun playVideo() {
        videoPlayerHelper.playVideo()
    }

    override fun pauseVideo() {
        videoPlayerHelper.pauseVideo()
    }
}

