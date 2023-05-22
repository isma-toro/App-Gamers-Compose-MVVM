package com.ismael.appgamermvvm.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.ismael.appgamermvvm.core.Constants.USERS
import com.ismael.appgamermvvm.data.repository.AuthRepositoryImpl
import com.ismael.appgamermvvm.data.repository.UserRepositoryImpl
import com.ismael.appgamermvvm.domain.repository.AuthRepository
import com.ismael.appgamermvvm.domain.repository.UsersRepository
import com.ismael.appgamermvvm.domain.use_cases.auth.AuthUseCase
import com.ismael.appgamermvvm.domain.use_cases.auth.GetCurrentUser
import com.ismael.appgamermvvm.domain.use_cases.auth.Login
import com.ismael.appgamermvvm.domain.use_cases.auth.Logout
import com.ismael.appgamermvvm.domain.use_cases.auth.SignUp
import com.ismael.appgamermvvm.domain.use_cases.users.Create
import com.ismael.appgamermvvm.domain.use_cases.users.GetUserById
import com.ismael.appgamermvvm.domain.use_cases.users.SaveImage
import com.ismael.appgamermvvm.domain.use_cases.users.Update
import com.ismael.appgamermvvm.domain.use_cases.users.UsersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

  @Provides
  fun providesFirebaseFireStore() : FirebaseFirestore = Firebase.firestore

  @Provides
  fun providesFirebaseStorage() : FirebaseStorage = FirebaseStorage.getInstance()

  @Provides
  fun providesStorageUsersRef(storage: FirebaseStorage) : StorageReference = storage.reference.child(
    USERS)

  @Provides
  fun providesUsersRef(db : FirebaseFirestore): CollectionReference = db.collection(USERS)

  @Provides
  fun provideFirebaseAuth() : FirebaseAuth = FirebaseAuth.getInstance()

  @Provides
  fun provideAuthRepository(impl : AuthRepositoryImpl) : AuthRepository = impl

  @Provides
  fun provideUserRepository(impl : UserRepositoryImpl) : UsersRepository = impl

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
}