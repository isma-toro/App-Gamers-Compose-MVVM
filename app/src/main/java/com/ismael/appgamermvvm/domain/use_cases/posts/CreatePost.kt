package com.ismael.appgamermvvm.domain.use_cases.posts

import com.ismael.appgamermvvm.domain.model.Post
import com.ismael.appgamermvvm.domain.repository.PostRepository
import java.io.File
import javax.inject.Inject

class CreatePost @Inject constructor(private val repository: PostRepository){
  suspend operator fun invoke(post : Post, file : File) = repository.create(post, file)
}