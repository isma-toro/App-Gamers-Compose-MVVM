package com.ismael.appgamermvvm.domain.use_cases.posts

import com.ismael.appgamermvvm.domain.repository.PostRepository
import javax.inject.Inject

class GetPosts @Inject constructor(private val repository: PostRepository) {
  operator fun invoke() = repository.getPosts()

}