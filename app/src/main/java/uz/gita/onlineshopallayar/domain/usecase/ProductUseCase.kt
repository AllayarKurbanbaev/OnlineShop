package uz.gita.onlineshopallayar.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.onlineshopallayar.data.ProductData
import uz.gita.onlineshopallayar.data.locale.entities.CartEntity

interface ProductUseCase {
    fun getAllProduct(): Flow<Result<List<ProductData>>>

    fun addProductToCart(product: ProductData): Flow<Result<Unit>>

    fun saveProductId(id: Int)

}