package com.example.instagrampostssample.ui.adapters.viewholders.helpers

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class ImageLoader {
    fun loadImage(context: Context, imageView: ImageView, imageUrl: String) {
        Glide.with(context).load(imageUrl).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView)
    }
}