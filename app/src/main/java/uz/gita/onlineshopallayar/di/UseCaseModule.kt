package uz.gita.onlineshopallayar.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.onlineshopallayar.domain.usecase.*
import uz.gita.onlineshopallayar.domain.usecase.impl.*

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {
    @Binds
    fun bindSignInUseCase(impl: SignInUseCaseImpl): SignInUseCase

    @Binds
    fun bindProductUseCase(impl: ProductUseCaseImpl): ProductUseCase

    @Binds
    fun bindMainUseCase(impl: MainUseCaseImpl): MainUseCase

    @Binds
    fun bindSplashUseCase(impl: SplashUseCaseImpl): SplashUseCase

    @Binds
    fun bindDetailUseCase(impl: DetailUseCaseImpl): DetailUseCase

    @Binds
    fun bindCartUseCase(impl: CartUseCaseImpl): CartUseCase
}