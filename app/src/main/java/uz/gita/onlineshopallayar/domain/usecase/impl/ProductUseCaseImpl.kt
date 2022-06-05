package uz.gita.onlineshopallayar.domain.usecase.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import uz.gita.onlineshopallayar.data.ProductData
import uz.gita.onlineshopallayar.data.locale.entities.toProductData
import uz.gita.onlineshopallayar.data.remote.model.response.toProductData
import uz.gita.onlineshopallayar.data.remote.model.response.toProductEntity
import uz.gita.onlineshopallayar.data.repository.app.AppRepository
import uz.gita.onlineshopallayar.domain.usecase.ProductUseCase
import uz.gita.onlineshopallayar.utils.isConnected
import javax.inject.Inject

class ProductUseCaseImpl @Inject constructor(
    private val repository: AppRepository
) : ProductUseCase {
    override fun getAllProduct() = flow<Result<List<ProductData>>> {
        if (isConnected()) {
            val response = repository.getAllProductsFromNetwork()
            if (response.isSuccessful) {
                response.body()?.let { list ->
                    repository.insertAllProducts(list.map { it.toProductEntity() })
                    emit(Result.success(list.map { it.toProductData() }))
                }
            } else {
                emit(Result.failure(Exception("No products")))
            }
        } else {

            emit(Result.success(repository.getAllProductsFromLocal().map {
                it.toProductData()
            }))
        }
    }.catch {
        emit(Result.failure(Exception(it)))
    }.flowOn(Dispatchers.IO)

    override fun addProductToCart(product: ProductData) = flow<Result<Unit>> {
        emit(Result.success(repository.addProductToCart(product)))
    }.catch {
        emit(Result.failure(Exception(it)))
    }.flowOn(Dispatchers.IO)

    override fun saveProductId(id: Int) {
        repository.saveProductID(id)
    }

}