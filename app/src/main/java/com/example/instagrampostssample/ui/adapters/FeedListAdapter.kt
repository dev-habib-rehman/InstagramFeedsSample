package com.example.instagrampostssample.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.media3.common.util.UnstableApi
import androidx.recyclerview.widget.ListAdapter
import com.example.instagrampostssample.data.models.Post
import com.example.instagrampostssample.databinding.ItemPostImageBinding
import com.example.instagrampostssample.databinding.ItemPostMixedBinding
import com.example.instagrampostssample.databinding.ItemPostVideoBinding
import com.example.instagrampostssample.ui.adapters.diffutils.PostDiffCallback
import com.example.instagrampostssample.ui.utils.PostType
import com.example.instagrampostssample.ui.adapters.viewholders.BaseViewHolder
import com.example.instagrampostssample.ui.adapters.viewholders.ImagePostViewHolder
import com.example.instagrampostssample.ui.adapters.viewholders.MixedPostViewHolder
import com.example.instagrampostssample.ui.adapters.viewholders.VideoPostViewHolder
import javax.inject.Inject

@UnstableApi
class FeedListAdapter @Inject constructor(
    private val imageFactory: ImagePostViewHolder.Factory,
    private val videoFactory: VideoPostViewHolder.Factory,
    private val mixedFactory: MixedPostViewHolder.Factory
) : ListAdapter<Post, BaseViewHolder>(PostDiffCallback()) {
    @UnstableApi
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (PostType.entries[viewType]) {
            PostType.IMAGE -> {
                val binding = ItemPostImageBinding.inflate(inflater, parent, false)
                imageFactory.create(binding)
            }

            PostType.VIDEO -> {
                val binding = ItemPostVideoBinding.inflate(inflater, parent, false)
                videoFactory.create(binding)
            }

            PostType.MIXED -> {
                val binding = ItemPostMixedBinding.inflate(inflater, parent, false)
                mixedFactory.create(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is Post.ImagePost -> PostType.IMAGE.ordinal
            is Post.VideoPost -> PostType.VIDEO.ordinal
            is Post.MixedPost -> PostType.MIXED.ordinal
        }
    }

    fun getPostAt(position: Int): Post {
        return getItem(position)
    }

    override fun onViewRecycled(holder: BaseViewHolder) {
        /*holder.releaseResources()
        super.onViewRecycled(holder)*/
        if (holder is VideoPostViewHolder || holder is MixedPostViewHolder) {
            holder.pauseVideo()
        }
        holder.releaseResources()
    }
}