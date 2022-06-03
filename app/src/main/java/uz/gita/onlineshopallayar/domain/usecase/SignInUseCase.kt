package uz.gita.onlineshopallayar.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.onlineshopallayar.data.remote.model.request.LoginRequest
import uz.gita.onlineshopallayar.data.remote.model.response.LoginResponse

interface SignInUseCase {

    fun signInUser(
        loginRequest: LoginRequest
    ): Flow<Result<LoginResponse>>

}