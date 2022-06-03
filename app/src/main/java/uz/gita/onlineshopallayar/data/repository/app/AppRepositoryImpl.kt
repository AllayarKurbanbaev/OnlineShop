package uz.gita.onlineshopallayar.data.repository.app

import retrofit2.Response
import timber.log.Timber
import uz.gita.onlineshopallayar.data.locale.SharedPref
import uz.gita.onlineshopallayar.data.locale.dao.Dao
import uz.gita.onlineshopallayar.data.locale.entities.ProductResponseEntity
import uz.gita.onlineshopallayar.data.remote.api.Api
import uz.gita.onlineshopallayar.data.remote.model.request.CartRequest
import uz.gita.onlineshopallayar.data.remote.model.response.CartResponse
import uz.gita.onlineshopallayar.data.remote.model.response.CategoryResponse
import uz.gita.onlineshopallayar.data.remote.model.response.ProductResponse
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val api: Api,
    private val dao: Dao,
    private val preferences: SharedPref
) : AppRepository {
    override suspend fun getAllProductsFromNetwork(): Response<List<ProductResponse>> {
        Timber.tag("SSS").d(api.getAllProducts().toString())
        return api.getAllProducts()
    }

    override suspend fun getAllProductsFromLocal(): List<ProductResponseEntity> {
        return dao.getAllProducts()
    }

    override suspend fun insertAllProducts(list: List<ProductResponseEntity>) {
        dao.deleteAllProducts()
        dao.insert(list)
    }

    override suspend fun getSingleProductFromLocal(): ProductResponseEntity {
        return dao.getProductById(preferences.productId)
    }

    override suspend fun getSingleProductFromNetwork(): Response<ProductResponse> {
//        Timber.tag("AAA").d(api.getSingleProduct(preferense.productId).toString())
        Timber.tag("AAA").d(preferences.productId.toString())
        return api.getSingleProduct("/products/${preferences.productId}")
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

    override fun saveProductID(id: Int) {
        Timber.tag("AAA").d(id.toString())
        preferences.productId = id
    }


}