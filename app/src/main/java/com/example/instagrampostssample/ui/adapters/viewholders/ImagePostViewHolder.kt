package com.example.instagrampostssample.ui.adapters.viewholders

import com.example.instagrampostssample.data.models.Post
import com.example.instagrampostssample.databinding.ItemPostImageBinding
import com.example.instagrampostssample.ui.adapters.viewholders.helpers.ImageLoader
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class ImagePostViewHolder @AssistedInject constructor(
    @Assisted private val binding: ItemPostImageBinding,
    private val imageLoader: ImageLoader
) : BaseViewHolder(binding.root) {

    @AssistedFactory
    interface Factory {
        fun create(binding: ItemPostImageBinding): ImagePostViewHolder
    }
    override fun bind(post: Post) {
        if (post is Post.ImagePost) {
            imageLoader.loadImage(binding.root.context, binding.ivImagePost, post.imageUrl)
        }
    }
}

