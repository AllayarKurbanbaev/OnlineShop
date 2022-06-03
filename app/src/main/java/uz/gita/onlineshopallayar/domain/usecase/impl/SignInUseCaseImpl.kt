package uz.gita.onlineshopallayar.domain.usecase.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.onlineshopallayar.data.remote.model.request.LoginRequest
import uz.gita.onlineshopallayar.data.remote.model.response.LoginResponse
import uz.gita.onlineshopallayar.data.repository.auth.AuthRepository
import uz.gita.onlineshopallayar.domain.usecase.SignInUseCase
import javax.inject.Inject

class SignInUseCaseImpl @Inject constructor(
    private val repository: AuthRepository
) : SignInUseCase {
    override fun signInUser(loginRequest: LoginRequest) = flow<Result<LoginResponse>> {
        val response = repository.signInUser(loginRequest)
        if (response.isSuccessful) {
            response.body()?.let {
                emit(Result.success(it))
                repository.saveUserID(loginRequest)
            }
        } else {
            emit(Result.failure(Exception("Not found user")))
        }
    }.catch {
        emit(Result.failure(Exception(it)))
    }.flowOn(Dispatchers.IO)

}