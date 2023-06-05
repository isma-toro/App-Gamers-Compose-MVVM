package com.ismael.appgamermvvm.domain.use_cases.posts

data class PostsUseCases(
  val create : CreatePost,
  val getPosts : GetPosts,
  val getPostsByIdUser : GetPostByIdUser,
  val deletePost : DeletePost,
  val updatePost : UpdatePost,
  val likePost : LikePost,
  val deleteLikePost : DeleteLikePost
)
