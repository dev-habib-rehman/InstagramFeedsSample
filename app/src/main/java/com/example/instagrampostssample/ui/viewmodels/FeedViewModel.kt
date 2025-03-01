package com.example.instagrampostssample.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.instagrampostssample.data.models.Post
import com.example.instagrampostssample.data.repositories.PostRepository
import com.example.instagrampostssample.data.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val postRepository: PostRepository
) : ViewModel() {

    private val _posts = MutableStateFlow<Result<List<Post>>>(Result.Loading())
    val posts: StateFlow<Result<List<Post>>> = _posts.asStateFlow()

    init {
        loadPosts()
    }

    private fun loadPosts() {
        _posts.value = Result.Loading()
        viewModelScope.launch {
            postRepository.getPosts().collect {
                _posts.value = it
            }
        }
    }
}
