package uz.gita.onlineshopallayar.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.onlineshopallayar.data.locale.entities.CartEntity
import uz.gita.onlineshopallayar.data.locale.entities.ProductResponseEntity
import uz.gita.onlineshopallayar.data.remote.model.response.CartResponse

interface CartUseCase {

    fun getUserCarts(): Flow<Result<List<CartEntity>>>

    fun order() : Flow<Result<Unit>>

    fun updateProductCart(product : CartEntity) : Flow<Result<Unit>>

    fun deleteProductID(product: CartEntity) : Flow<Result<Unit>>

    fun increaseProductCount(id : Int) : Flow<Result<Unit>>
    fun decreaseProductCount(id : Int) : Flow<Result<Unit>>

    fun getProductCount(id : Int) : Flow<Result<Int>>

}