package com.example.instagrampostssample.ui.adapters.viewholders.helpers

import android.content.Context
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import java.util.LinkedList
import javax.inject.Inject

@UnstableApi
class ExoPlayerProvider @Inject constructor(
    private val context: Context, private val dataSourceFactory: DataSource.Factory
) {
    private val playerPool = LinkedList<ExoPlayer>()
    private val maxPoolSize = 11

    fun getPlayer(): ExoPlayer {
        return synchronized(playerPool) {
            playerPool.pollFirst() ?: createNewPlayer()
        }
    }

    private fun createNewPlayer(): ExoPlayer {
        return ExoPlayer.Builder(context).setMediaSourceFactory(
                ProgressiveMediaSource.Factory(dataSourceFactory)
            ).build()
    }

    fun recyclePlayer(player: ExoPlayer) {
        synchronized(playerPool) {
            if (playerPool.size < maxPoolSize) {
                player.stop()
                player.clearMediaItems()
                playerPool.add(player)
            } else {
                player.release()
            }
        }
    }
}