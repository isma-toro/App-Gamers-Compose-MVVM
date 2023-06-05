package com.ismael.appgamermvvm.domain.use_cases.posts

import com.ismael.appgamermvvm.domain.repository.PostRepository
import javax.inject.Inject

class GetPostByIdUser @Inject constructor(private val repository : PostRepository){
  operator fun invoke(idUser : String) = repository.getPostsByUserId(idUser)

}