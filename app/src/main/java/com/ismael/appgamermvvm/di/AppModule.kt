package com.ismael.appgamermvvm.di

import androidx.navigation.Navigator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.ismael.appgamermvvm.core.Constants.POSTS
import com.ismael.appgamermvvm.core.Constants.USERS
import com.ismael.appgamermvvm.data.repository.AuthRepositoryImpl
import com.ismael.appgamermvvm.data.repository.PostRepositoryImpl
import com.ismael.appgamermvvm.data.repository.UserRepositoryImpl
import com.ismael.appgamermvvm.domain.repository.AuthRepository
import com.ismael.appgamermvvm.domain.repository.PostRepository
import com.ismael.appgamermvvm.domain.repository.UsersRepository
import com.ismael.appgamermvvm.domain.use_cases.auth.AuthUseCase
import com.ismael.appgamermvvm.domain.use_cases.auth.GetCurrentUser
import com.ismael.appgamermvvm.domain.use_cases.auth.Login
import com.ismael.appgamermvvm.domain.use_cases.auth.Logout
import com.ismael.appgamermvvm.domain.use_cases.auth.SignUp
import com.ismael.appgamermvvm.domain.use_cases.posts.CreatePost
import com.ismael.appgamermvvm.domain.use_cases.posts.DeleteLikePost
import com.ismael.appgamermvvm.domain.use_cases.posts.DeletePost
import com.ismael.appgamermvvm.domain.use_cases.posts.GetPostByIdUser
import com.ismael.appgamermvvm.domain.use_cases.posts.GetPosts
import com.ismael.appgamermvvm.domain.use_cases.posts.LikePost
import com.ismael.appgamermvvm.domain.use_cases.posts.PostsUseCases
import com.ismael.appgamermvvm.domain.use_cases.posts.UpdatePost
import com.ismael.appgamermvvm.domain.use_cases.users.Create
import com.ismael.appgamermvvm.domain.use_cases.users.GetUserById
import com.ismael.appgamermvvm.domain.use_cases.users.SaveImage
import com.ismael.appgamermvvm.domain.use_cases.users.Update
import com.ismael.appgamermvvm.domain.use_cases.users.UsersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

  @Provides
  fun providesFirebaseFireStore() : FirebaseFirestore = Firebase.firestore

  @Provides
  fun providesFirebaseStorage() : FirebaseStorage = FirebaseStorage.getInstance()

  @Provides
  @Named(USERS)
  fun providesStorageUsersRef(storage: FirebaseStorage) : StorageReference = storage.reference.child(
    USERS)

  @Provides
  @Named(USERS)
  fun providesUsersRef(db : FirebaseFirestore): CollectionReference = db.collection(USERS)

  @Provides
  @Named(POSTS)
  fun providesStoragePostRef(storage: FirebaseStorage) : StorageReference = storage.reference.child(
    POSTS)

  @Provides
  @Named(POSTS)
  fun providesPostsRef(db : FirebaseFirestore): CollectionReference = db.collection(POSTS)

  @Provides
  fun provideFirebaseAuth() : FirebaseAuth = FirebaseAuth.getInstance()

  @Provides
  fun provideAuthRepository(impl : AuthRepositoryImpl) : AuthRepository = impl

  @Provides
  fun provideUserRepository(impl : UserRepositoryImpl) : UsersRepository = impl

  @Provides
  fun providesPostsRepository(impl : PostRepositoryImpl) : PostRepository = impl

  @Provides
  fun provideAuthUseCase(repository: AuthRepository) = AuthUseCase(
    getCurrentUser = GetCurrentUser(repository),
    login = Login(repository),
    logout = Logout(repository),
    signUp = SignUp(repository)
  )

  @Provides
  fun provideUserUseCase(repository: UsersRepository) = UsersUseCase(
    create = Create(repository),
    getUserById = GetUserById(repository),
    update = Update(repository),
    saveImage = SaveImage(repository)
  )

  @Provides
  fun providePostsUseCase(repository: PostRepository) = PostsUseCases(
    create = CreatePost(repository),
    getPosts = GetPosts(repository),
    getPostsByIdUser = GetPostByIdUser(repository),
    deletePost = DeletePost(repository),
    updatePost = UpdatePost(repository),
    likePost = LikePost(repository),
    deleteLikePost = DeleteLikePost(repository)
  )
}