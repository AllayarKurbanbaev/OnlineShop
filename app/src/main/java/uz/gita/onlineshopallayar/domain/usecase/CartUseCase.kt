package uz.gita.onlineshopallayar.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.onlineshopallayar.data.locale.entities.CartEntity

interface CartUseCase {

    fun getUserCarts(): Flow<Result<List<CartEntity>>>

    fun order(): Flow<Result<Unit>>

    fun updateProductCart(product: CartEntity): Flow<Result<Unit>>

    fun deleteProductID(product: CartEntity): Flow<Result<Unit>>
}