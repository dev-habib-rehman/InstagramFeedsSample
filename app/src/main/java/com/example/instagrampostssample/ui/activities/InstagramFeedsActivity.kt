package com.example.instagrampostssample.ui.activities

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.media3.common.util.UnstableApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrampostssample.R
import com.example.instagrampostssample.data.models.Post
import com.example.instagrampostssample.data.utils.Result
import com.example.instagrampostssample.databinding.ActivityInstagramFeedsBinding
import com.example.instagrampostssample.ui.adapters.FeedListAdapter
import com.example.instagrampostssample.ui.adapters.viewholders.MixedPostViewHolder
import com.example.instagrampostssample.ui.adapters.viewholders.VideoPostViewHolder
import com.example.instagrampostssample.ui.viewmodels.FeedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.max
import kotlin.math.min

@AndroidEntryPoint
@UnstableApi
class InstagramFeedsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInstagramFeedsBinding
    private var currentPlayingPosition = -1

    @Inject
    lateinit var postAdapter: FeedListAdapter
    private val viewModel: FeedViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_instagram_feeds
        )
        setWindow()
        setupRecyclerView()
        observeViewModel()
    }

    private fun setWindow() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    @UnstableApi
    private fun setupRecyclerView() {
        binding.rvFeedMain.apply {
            layoutManager = LinearLayoutManager(this@InstagramFeedsActivity)
            adapter = postAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    handleVideoPlayback(recyclerView, newState)
                }
            })
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.posts.collect {
                    when (it) {
                        is Result.Empty -> Unit
                        is Result.Failure -> Unit
                        is Result.Loading -> Unit
                        is Result.Success -> postAdapter.submitList(it.data)
                    }
                }
            }
        }
    }

    private fun handleVideoPlayback(recyclerView: RecyclerView, newState: Int) {
        when (newState) {
            RecyclerView.SCROLL_STATE_IDLE -> playMostVisibleVideo(recyclerView)
            RecyclerView.SCROLL_STATE_DRAGGING -> pauseCurrentVideo(recyclerView)
            RecyclerView.SCROLL_STATE_SETTLING -> pauseCurrentVideo(recyclerView)
        }
    }

    private fun playMostVisibleVideo(recyclerView: RecyclerView) {
        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
        val firstVisible = layoutManager.findFirstVisibleItemPosition()
        val lastVisible = layoutManager.findLastVisibleItemPosition()

        var mostVisiblePosition = -1
        var maxVisibility = 0f

        for (i in firstVisible..lastVisible) {
            val viewHolder = recyclerView.findViewHolderForAdapterPosition(i)
            val visibility = calculateVisibility(recyclerView, viewHolder?.itemView)

            if (visibility > maxVisibility && isVideoPost(i)) {
                maxVisibility = visibility
                mostVisiblePosition = i
            }
        }

        if (mostVisiblePosition != -1 && mostVisiblePosition != currentPlayingPosition) {
            pauseCurrentVideo(recyclerView)
            playVideoAtPosition(recyclerView, mostVisiblePosition)
            currentPlayingPosition = mostVisiblePosition
        }
    }

    private fun isVideoPost(position: Int): Boolean {
        return postAdapter.getPostAt(position).let {
            it is Post.VideoPost || it is Post.MixedPost
        }
    }

    private fun calculateVisibility(recyclerView: RecyclerView, view: View?): Float {
        if (view == null) return 0f

        val recyclerHeight = recyclerView.height
        val itemTop = view.top - recyclerView.paddingTop
        val itemBottom = view.bottom - recyclerView.paddingTop

        val visibleHeight = (min(itemBottom, recyclerHeight) - max(itemTop, 0)).coerceAtLeast(0)
        return visibleHeight.toFloat() / view.height
    }

    private fun playVideoAtPosition(recyclerView: RecyclerView, position: Int) {
        recyclerView.findViewHolderForAdapterPosition(position)?.let {
            when (it) {
                is VideoPostViewHolder -> it.playVideo()
                is MixedPostViewHolder -> it.playVideo()
            }
        }
    }

    private fun pauseCurrentVideo(recyclerView: RecyclerView) {
        if (currentPlayingPosition != -1) {
            recyclerView.findViewHolderForAdapterPosition(currentPlayingPosition)?.let {
                when (it) {
                    is VideoPostViewHolder -> it.pauseVideo()
                    is MixedPostViewHolder -> it.pauseVideo()
                }
            }
        }
    }
}