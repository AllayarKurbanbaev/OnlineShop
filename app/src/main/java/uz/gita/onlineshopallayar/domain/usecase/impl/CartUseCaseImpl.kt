package uz.gita.onlineshopallayar.domain.usecase.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import uz.gita.onlineshopallayar.data.locale.entities.ProductResponseEntity
import uz.gita.onlineshopallayar.data.repository.app.AppRepository
import uz.gita.onlineshopallayar.domain.usecase.CartUseCase
import javax.inject.Inject


class CartUseCaseImpl @Inject constructor(
    private val repository: AppRepository
) : CartUseCase {

    override fun getUserCarts() = flow<Result<List<ProductResponseEntity>>> {
        val list: ArrayList<ProductResponseEntity> = arrayListOf()
        val response = repository.getUserCart()
        if (response.isSuccessful) {
            response.body()?.let { data ->
                Timber.tag("BBB").d(data.toString())
                for (j in data.indices) {
                    for (i in 0 until data[j].products.size) {
                        repository.saveProductID(data[j].products[i].productId)
                        list.add(repository.getSingleProductFromLocal())
                    }
                }
                emit(Result.success(list))
            }
        } else {
            emit(Result.failure(Exception("Error")))
        }
    }.catch {
        emit(Result.failure(Exception(it)))
    }.flowOn(Dispatchers.IO)

}