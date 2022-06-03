package uz.gita.onlineshopallayar.domain.usecase.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.onlineshopallayar.data.remote.model.response.CartResponse
import uz.gita.onlineshopallayar.data.repository.app.AppRepository
import uz.gita.onlineshopallayar.domain.usecase.CartUseCase
import javax.inject.Inject


class CartUseCaseImpl @Inject constructor(
    private val repository: AppRepository
) : CartUseCase {

    override fun getUserCarts() = flow<Result<List<CartResponse>>> {
        val response = repository.getUserCart()
        if (response.isSuccessful) {
            response.body()?.let { data ->
                emit(Result.success(data))
            }
        } else {
            emit(Result.failure(Exception("Error")))
        }
    }.catch {
        emit(Result.failure(Exception(it)))
    }.flowOn(Dispatchers.IO)

}