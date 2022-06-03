package uz.gita.onlineshopallayar.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.onlineshopallayar.data.ProductData
import uz.gita.onlineshopallayar.data.remote.model.response.ProductResponse

interface DetailUseCase {
    fun addProductToCart()

    fun getSingleProduct() : Flow<Result<ProductData>>

}