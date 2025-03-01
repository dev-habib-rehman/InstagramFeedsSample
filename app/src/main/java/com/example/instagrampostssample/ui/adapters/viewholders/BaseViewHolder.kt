package com.example.instagrampostssample.ui.adapters.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrampostssample.data.models.Post

abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    open fun bind(post: Post) {}
    open fun playVideo() {}
    open fun pauseVideo() {}
    open fun releaseResources() {}
}
