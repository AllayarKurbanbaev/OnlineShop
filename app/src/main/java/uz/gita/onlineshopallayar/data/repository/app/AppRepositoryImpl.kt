package uz.gita.onlineshopallayar.data.repository.app

import retrofit2.Response
import timber.log.Timber
import uz.gita.onlineshopallayar.data.ProductData
import uz.gita.onlineshopallayar.data.locale.SharedPref
import uz.gita.onlineshopallayar.data.locale.dao.CartDao
import uz.gita.onlineshopallayar.data.locale.dao.ProductDao
import uz.gita.onlineshopallayar.data.locale.entities.CartEntity
import uz.gita.onlineshopallayar.data.locale.entities.ProductResponseEntity
import uz.gita.onlineshopallayar.data.remote.api.Api
import uz.gita.onlineshopallayar.data.remote.model.request.CartRequest
import uz.gita.onlineshopallayar.data.remote.model.response.CartResponse
import uz.gita.onlineshopallayar.data.remote.model.response.CategoryResponse
import uz.gita.onlineshopallayar.data.remote.model.response.ProductResponse
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val api: Api,
    private val productDao: ProductDao,
    private val cartDao: CartDao,
    private val preferences: SharedPref
) : AppRepository {


    override suspend fun getAllProductsFromNetwork(): Response<List<ProductResponse>> {
        return api.getAllProducts()
    }

    override suspend fun getAllProductsFromLocal(): List<ProductResponseEntity> {
        return productDao.getAllProducts()
    }

    override suspend fun insertAllProducts(list: List<ProductResponseEntity>) {
        productDao.deleteAllProducts()
        productDao.insert(list)
    }

    override suspend fun getAllCategories(): Response<CategoryResponse> {
        return api.getAllCategories()
    }

    override suspend fun getSingleCategory(category: String): Response<List<ProductResponse>> {
        return api.getSingleCategory(category)
    }

    override suspend fun getAllCarts(): Response<List<CartResponse>> {

        return api.getAllCarts()
    }

    override suspend fun getUserCart(): Response<List<CartResponse>> {
        return api.getUserCarts("/carts/user/${preferences.userId}")
    }

    override suspend fun addNewCart(cartRequest: CartRequest): Response<CartResponse> {
        return api.addNewCart(cartRequest)
    }

    override suspend fun updateCart(id: Int, cartRequest: CartRequest): Response<CartResponse> {
        return api.updateCart("/carts/$id", cartRequest)
    }

    override suspend fun deleteCart(id: Int): Response<CartResponse> {
        return api.deleteCart("/carts/$id")
    }

    override suspend fun getAllProductCartByUserId(): List<CartEntity> {
        val response = cartDao.getAllCartProductsByUserId(preferences.userId)
        Timber.tag("TTT").d(response.toString())
        return response
    }

    override suspend fun updateProductCart(product: CartEntity) {
        return cartDao.update(product)
    }

    override suspend fun deleteAllProductCart() {
        return cartDao.deleteAllProductByUserId(preferences.userId)
    }

    override suspend fun deleteProductCart(product: CartEntity) {
        return cartDao.delete(product)
    }

    override suspend fun addProductToCart(product: ProductData) {
        Timber.tag("ZZZ").d(product.toString())
        val model: CartEntity = CartEntity(
            product.id,
            product.title,
            product.description,
            product.price,
            preferences.userId,
            1,
            product.image
        )
        return cartDao.insert(model)
    }
}