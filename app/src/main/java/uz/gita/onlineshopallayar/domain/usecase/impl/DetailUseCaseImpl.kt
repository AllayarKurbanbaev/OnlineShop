package uz.gita.onlineshopallayar.domain.usecase.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.onlineshopallayar.data.ProductData
import uz.gita.onlineshopallayar.data.repository.app.AppRepository
import uz.gita.onlineshopallayar.domain.usecase.DetailUseCase
import javax.inject.Inject


class DetailUseCaseImpl @Inject constructor(
    private val repository: AppRepository
) : DetailUseCase {
    override fun addProductToCart(product: ProductData) = flow<Result<Unit>> {
        emit(Result.success(repository.addProductToCart(product)))
    }.catch {
        emit(Result.failure(Exception(it)))
    }.flowOn(Dispatchers.IO)
}