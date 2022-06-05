package uz.gita.onlineshopallayar.domain.usecase.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.onlineshopallayar.data.locale.entities.CartEntity
import uz.gita.onlineshopallayar.data.repository.app.AppRepository
import uz.gita.onlineshopallayar.domain.usecase.CartUseCase
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
        val response = repository.deleteAllProductCart()
        emit(Result.success(response))
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
        emit(Result.success(repository.deleteProductCart(product)))
    }.catch {
        emit(Result.failure(Exception(it)))
    }.flowOn(Dispatchers.IO)

}