package uz.gita.onlineshopallayar.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.onlineshopallayar.data.repository.app.AppRepository
import uz.gita.onlineshopallayar.data.repository.app.AppRepositoryImpl
import uz.gita.onlineshopallayar.data.repository.auth.AuthRepository
import uz.gita.onlineshopallayar.data.repository.auth.AuthRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @[Binds Singleton]
    fun getAuthRepository(impl: AuthRepositoryImpl): AuthRepository

    @[Binds Singleton]
    fun getAppRepository(impl: AppRepositoryImpl): AppRepository
}