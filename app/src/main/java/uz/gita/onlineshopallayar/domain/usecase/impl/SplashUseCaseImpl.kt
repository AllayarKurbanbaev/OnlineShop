package uz.gita.onlineshopallayar.domain.usecase.impl

import timber.log.Timber
import uz.gita.onlineshopallayar.data.repository.auth.AuthRepository
import uz.gita.onlineshopallayar.domain.usecase.SplashUseCase
import javax.inject.Inject

class SplashUseCaseImpl @Inject constructor(
    private val repository: AuthRepository
) : SplashUseCase {
    override fun checking(): Boolean {
        return repository.check()
    }

}