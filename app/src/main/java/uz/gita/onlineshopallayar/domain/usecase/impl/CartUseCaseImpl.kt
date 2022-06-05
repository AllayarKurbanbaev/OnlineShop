package uz.gita.onlineshopallayar.domain.usecase.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.onlineshopallayar.data.locale.entities.CartEntity
import uz.gita.onlineshopallayar.data.repository.app.AppRepository
import uz.gita.onlineshopallayar.domain.usecase.CartUseCase
import uz.gita.onlineshopallayar.utils.isConnected
import javax.inject.Inject


class CartUseCaseImpl @Inject constructor(
    private val repository: AppRepository
) : CartUseCase {

    override fun getUserCarts() = flow<Result<List<CartEntity>>> {
        emit(Result.success(repository.getAllProductCartByUserId()))
    }.catch {
        emit(Result.failure(Exception(it)))
    }.flowOn(Dispatchers.IO)

    override fun order() = flow<Result<Unit>> {
        if (isConnected()) {
            val response = repository.deleteAllProductCart()
            emit(Result.success(response))
        } else {
            emit(Result.failure(Exception("Connect your internet")))
        }
    }.catch {
        emit(Result.failure(Exception(it)))
    }.flowOn(Dispatchers.IO)

    override fun updateProductCart(product: CartEntity) = flow<Result<Unit>> {
        val response = repository.updateProductCart(product)
        emit(Result.success(response))
    }.catch {
        emit(Result.failure(Exception(it)))
    }.flowOn(Dispatchers.IO)

    override fun deleteProductID(product: CartEntity) = flow<Result<Unit>> {
        val respone = repository.deleteProductCart(product)
        emit(Result.success(respone))
    }.catch {
        emit(Result.failure(Exception(it)))
    }.flowOn(Dispatchers.IO)

    override fun increaseProductCount(id: Int) = flow<Result<Unit>> {
        val response = repository.increaseProductCount(id)
        emit(Result.success(response))
    }.catch {
        emit(Result.failure(Exception(it)))
    }.flowOn(Dispatchers.IO)

    override fun decreaseProductCount(id: Int) = flow<Result<Unit>> {
        val response = repository.decreaseProductCount(id)
        emit(Result.success(response))
    }.catch {
        emit(Result.failure(Exception(it)))
    }.flowOn(Dispatchers.IO)

    override fun getProductCount(id: Int) = flow<Result<Int>> {
        val response = repository.getProductCount(id)
        emit(Result.success(response))
    }.catch {
        emit(Result.failure(Exception(it)))
    }.flowOn(Dispatchers.IO)

}