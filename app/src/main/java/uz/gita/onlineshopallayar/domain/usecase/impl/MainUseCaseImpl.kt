package uz.gita.onlineshopallayar.domain.usecase.impl

import uz.gita.onlineshopallayar.data.repository.app.AppRepository
import uz.gita.onlineshopallayar.data.repository.auth.AuthRepository
import uz.gita.onlineshopallayar.domain.usecase.MainUseCase
import javax.inject.Inject


class MainUseCaseImpl @Inject constructor(
    private val repository: AuthRepository
): MainUseCase{
    override fun signOut() {
        repository.signOut()
    }

}