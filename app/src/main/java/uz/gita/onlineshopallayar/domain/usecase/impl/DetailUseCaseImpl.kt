package uz.gita.onlineshopallayar.domain.usecase.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import uz.gita.onlineshopallayar.data.ProductData
import uz.gita.onlineshopallayar.data.locale.entities.toProductData
import uz.gita.onlineshopallayar.data.remote.model.response.ProductResponse
import uz.gita.onlineshopallayar.data.remote.model.response.toProductData
import uz.gita.onlineshopallayar.data.repository.app.AppRepository
import uz.gita.onlineshopallayar.domain.usecase.DetailUseCase
import uz.gita.onlineshopallayar.utils.isConnected
import javax.inject.Inject


class DetailUseCaseImpl @Inject constructor(
    private val repository: AppRepository
) : DetailUseCase {
    override fun addProductToCart() {

    }

    override fun getSingleProduct() = flow<Result<ProductData>> {
        if (isConnected()){
            val response = repository.getSingleProductFromNetwork()
            Timber.tag("WWW").d(response.toString())
            if (response.isSuccessful) {
                response.body()?.let { data ->
                    emit(Result.success(data.toProductData()))
                }
            } else {
                emit(Result.failure(Exception("Error")))
            }
        } else {
            emit(Result.success(repository.getSingleProductFromLocal().toProductData()))
        }

    }.catch {
        emit(Result.failure(Exception(it)))
    }.flowOn(Dispatchers.IO)
}