package uz.gita.onlineshopallayar.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.onlineshopallayar.data.remote.model.response.CartResponse

interface CartUseCase {

    fun getUserCarts(): Flow<Result<List<CartResponse>>>
}