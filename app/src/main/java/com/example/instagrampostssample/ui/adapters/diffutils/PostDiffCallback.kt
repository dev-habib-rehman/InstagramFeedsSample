package com.example.instagrampostssample.ui.adapters.diffutils

import androidx.recyclerview.widget.DiffUtil
import com.example.instagrampostssample.data.models.Post

class PostDiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }
}