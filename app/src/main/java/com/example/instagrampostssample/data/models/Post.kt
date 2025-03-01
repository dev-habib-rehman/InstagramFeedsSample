package com.example.instagrampostssample.data.models

sealed class Post {
    abstract val id: Int

    data class ImagePost(
        override val id: Int,
        val imageUrl: String
    ) : Post()

    data class VideoPost(
        override val id: Int,
        val videoUrl: String
    ) : Post()

    data class MixedPost(
        override val id: Int,
        val imageUrl: String,
        val videoUrl: String
    ) : Post()
}
