package uz.gita.onlineshopallayar.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.onlineshopallayar.data.ProductData
import uz.gita.onlineshopallayar.data.remote.model.request.CartRequest
import uz.gita.onlineshopallayar.data.remote.model.response.CartResponse

interface ProductUseCase {
    fun getAllProduct(): Flow<Result<List<ProductData>>>

    fun addNewCart(cartRequest: CartRequest): Flow<Result<CartResponse>>

    fun saveProductId(id: Int)

}