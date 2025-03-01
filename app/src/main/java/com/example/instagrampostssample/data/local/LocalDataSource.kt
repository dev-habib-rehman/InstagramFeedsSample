package com.example.instagrampostssample.data.local

import android.content.Context
import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import com.example.instagrampostssample.R
import com.example.instagrampostssample.data.models.Post
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun getData(): List<Post> {
        return listOf(
            Post.ImagePost(0, getDrawableUri(R.drawable.pexels_arts_1)),
            Post.ImagePost(1, getDrawableUri(R.drawable.pexels_arts_9)),
            Post.VideoPost(2, getRawUri(R.raw.short_reel_1)),
            Post.MixedPost(
                3, getDrawableUri(R.drawable.pexels_arts_2), getRawUri(R.raw.short_reel_3)
            ),
            Post.ImagePost(4, getDrawableUri(R.drawable.pexels_arts_2)),
            Post.VideoPost(5, getRawUri(R.raw.short_reel_4)),
            Post.MixedPost(
                6, getDrawableUri(R.drawable.pexels_arts_3), getRawUri(R.raw.short_reel_5)
            ),
            Post.ImagePost(7, getDrawableUri(R.drawable.pexels_arts_4)),
            Post.VideoPost(8, getRawUri(R.raw.short_reel_6)),
            Post.MixedPost(
                9, getDrawableUri(R.drawable.pexels_arts_5), getRawUri(R.raw.short_reel_7)
            ),
            Post.ImagePost(10, getDrawableUri(R.drawable.pexels_arts_6)),
            Post.VideoPost(11, getRawUri(R.raw.short_reel_8)),
            Post.MixedPost(
                12, getDrawableUri(R.drawable.pexels_arts_7), getRawUri(R.raw.short_reel_9)
            ),
            Post.ImagePost(13, getDrawableUri(R.drawable.pexels_arts_8)),
            Post.VideoPost(14, getRawUri(R.raw.short_reel_10)),
            Post.MixedPost(
                15, getDrawableUri(R.drawable.pexels_arts_9), getRawUri(R.raw.short_reel_11)
            )
        )
    }

    private fun getDrawableUri(@DrawableRes drawableId: Int): String {
        return Uri.parse("android.resource://${context.packageName}/$drawableId").toString()
    }

    private fun getRawUri(@RawRes rawId: Int): String {
        return Uri.parse("android.resource://${context.packageName}/$rawId").toString()
    }
}