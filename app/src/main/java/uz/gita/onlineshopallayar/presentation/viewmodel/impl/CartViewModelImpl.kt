package uz.gita.onlineshopallayar.presentation.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.onlineshopallayar.data.locale.entities.CartEntity
import uz.gita.onlineshopallayar.domain.usecase.CartUseCase
import uz.gita.onlineshopallayar.presentation.viewmodel.CartViewModel
import uz.gita.onlineshopallayar.utils.isConnected
import javax.inject.Inject

@HiltViewModel
class CartViewModelImpl @Inject constructor(
    private val useCase: CartUseCase
) : ViewModel(), CartViewModel {
    override val errorLiveData = MutableLiveData<String>()
    override val loadLiveData = MutableLiveData<List<CartEntity>>()
    override val orderLiveData = MutableLiveData<String>()
    override val deleteLiveData = MutableLiveData<String>()
    override val messageLiveData = MutableLiveData<String>()
    private val list = ArrayList<CartEntity>()

    override fun order() {
        if (isConnected()) {
            useCase.order().onEach {
                it.onSuccess {
                    load()
                    orderLiveData.value = "Ordered"
                }
                it.onFailure { throwable ->
                    errorLiveData.value = throwable.message
                }
            }.launchIn(viewModelScope)
        } else messageLiveData.value = "Not connection network"
    }

    override fun plusClick(pos: Int) {
        list[pos].quantity++
        loadLiveData.value = list

        useCase.updateProductCart(list[pos]).onEach {
            it.onFailure { throwable ->
                list[pos].quantity--
                loadLiveData.value = list
                errorLiveData.value = throwable.message
            }
        }.launchIn(viewModelScope)
    }

    override fun minusClick(pos: Int) {
        list[pos].quantity--
        loadLiveData.value = list
        useCase.updateProductCart(list[pos]).onEach {
            it.onFailure { throwable ->
                list[pos].quantity++
                loadLiveData.value = list
                errorLiveData.value = throwable.message
            }
        }.launchIn(viewModelScope)
    }

    override fun delete(product: CartEntity, pos: Int) {
        useCase.deleteProductID(product).onEach {
            it.onSuccess {
                deleteLiveData.value = "Deleted"
                list.removeAt(pos)
                loadLiveData.value = list
            }
            it.onFailure { throwable ->
                errorLiveData.value = throwable.message
            }
        }.launchIn(viewModelScope)
    }

    override fun load() {
        useCase.getUserCarts().onEach {
            it.onSuccess { response ->
                list.clear()
                list.addAll(response)
                loadLiveData.value = list
            }
            it.onFailure { throwable ->
                errorLiveData.value = throwable.message
            }
        }.launchIn(viewModelScope)
    }
}