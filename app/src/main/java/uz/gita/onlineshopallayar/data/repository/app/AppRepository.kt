package uz.gita.onlineshopallayar.data.repository.app

import retrofit2.Response
import uz.gita.onlineshopallayar.data.ProductData
import uz.gita.onlineshopallayar.data.locale.entities.CartEntity
import uz.gita.onlineshopallayar.data.locale.entities.ProductResponseEntity
import uz.gita.onlineshopallayar.data.remote.model.request.CartRequest
import uz.gita.onlineshopallayar.data.remote.model.response.CartResponse
import uz.gita.onlineshopallayar.data.remote.model.response.CategoryResponse
import uz.gita.onlineshopallayar.data.remote.model.response.ProductResponse

interface AppRepository {
    suspend fun getAllProductsFromNetwork(): Response<List<ProductResponse>>
    suspend fun getAllProductsFromLocal(): List<ProductResponseEntity>
    suspend fun insertAllProducts(list: List<ProductResponseEntity>)

    suspend fun getAllCategories(): Response<CategoryResponse>

    suspend fun getSingleCategory(category: String): Response<List<ProductResponse>>

    suspend fun getAllCarts(): Response<List<CartResponse>>

    suspend fun getUserCart(): Response<List<CartResponse>>

    suspend fun addNewCart(cartRequest: CartRequest): Response<CartResponse>

    suspend fun updateCart(id: Int, cartRequest: CartRequest): Response<CartResponse>

    suspend fun deleteCart(id: Int): Response<CartResponse>


    suspend fun getAllProductCartByUserId(): List<CartEntity>

    suspend fun updateProductCart(product: CartEntity)

    suspend fun deleteAllProductCart()

    suspend fun deleteProductCart(product: CartEntity)

    suspend fun addProductToCart(product: ProductData)

}


