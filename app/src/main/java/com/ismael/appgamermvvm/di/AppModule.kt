package com.ismael.appgamermvvm.di

import com.google.firebase.auth.FirebaseAuth
import com.ismael.appgamermvvm.data.repository.AuthRepositoryImpl
import com.ismael.appgamermvvm.domain.repository.AuthRepository
import com.ismael.appgamermvvm.domain.use_cases_auth.AuthUseCase
import com.ismael.appgamermvvm.domain.use_cases_auth.GetCurrentUser
import com.ismael.appgamermvvm.domain.use_cases_auth.Login
import com.ismael.appgamermvvm.domain.use_cases_auth.Logout
import com.ismael.appgamermvvm.domain.use_cases_auth.SignUp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

  @Provides
  fun provideFirebaseAuth() : FirebaseAuth = FirebaseAuth.getInstance()

  @Provides
  fun provideAuthRepository(impl : AuthRepositoryImpl) : AuthRepository = impl

  @Provides
  fun provideAuthUseCase(repository: AuthRepository) = AuthUseCase(
    getCurrentUser = GetCurrentUser(repository),
    login = Login(repository),
    logout = Logout(repository),
    signUp = SignUp(repository)

  )
}