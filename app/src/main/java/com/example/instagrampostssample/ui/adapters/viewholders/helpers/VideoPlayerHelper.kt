package com.example.instagrampostssample.ui.adapters.viewholders.helpers

import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import javax.inject.Inject

@UnstableApi
class VideoPlayerHelper @Inject constructor(
    private val exoPlayerProvider: ExoPlayerProvider
) {
    private var player: ExoPlayer? = null

    fun initializePlayer(videoView: PlayerView, videoUrl: String) {
        releasePlayer()
        player = exoPlayerProvider.getPlayer().apply {
            videoView.player = this
            setMediaItem(MediaItem.fromUri(videoUrl))
            prepare()
            playWhenReady = false
        }
    }

    fun releasePlayer() {
        player?.let {
            exoPlayerProvider.recyclePlayer(it)
            player = null
        }
    }

    fun playVideo() {
        player?.playWhenReady = true
    }

    fun pauseVideo() {
        player?.playWhenReady = false
    }
}
