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
import javax.inject.Inject

@HiltViewModel
class CartViewModelImpl @Inject constructor(
    private val useCase: CartUseCase
) : ViewModel(), CartViewModel {
    override val errorLiveData = MutableLiveData<String>()
    override val progressLiveData = MutableLiveData<Boolean>()
    override val loadLiveData = MutableLiveData<List<CartEntity>>()
    override val orderLiveData = MutableLiveData<String>()
    override val deleteLiveData = MutableLiveData<String>()
    override val messageLiveData = MutableLiveData<String>()

    override fun order() {
        progressLiveData.value = true
        useCase.order().onEach {
            progressLiveData.value = false
            it.onSuccess {
                load()
                orderLiveData.value = "Ordered"
            }
            it.onFailure { throwable ->
                errorLiveData.value = throwable.message
            }
        }.launchIn(viewModelScope)
    }

    override fun check(product: CartEntity) {
        progressLiveData.value = true
        useCase.updateProductCart(product).onEach {
            progressLiveData.value = false
            it.onSuccess {
                messageLiveData.value = "Update"
            }
            it.onFailure { throwable ->
                errorLiveData.value = throwable.message
            }
        }.launchIn(viewModelScope)
    }

    override fun plusClick(id: Int) {
        useCase.increaseProductCount(id).onEach {
            it.onSuccess {
                load()
            }
            it.onFailure { throwable ->
                errorLiveData.value = throwable.message
            }
        }.launchIn(viewModelScope)
    }

    override fun minusClick(id: Int) {
        useCase.decreaseProductCount(id).onEach {
            it.onSuccess {
                load()
            }
            it.onFailure { throwable ->
                errorLiveData.value = throwable.message
            }
        }.launchIn(viewModelScope)
    }

    override fun delete(product: CartEntity) {
        useCase.deleteProductID(product).onEach {
            it.onSuccess {
                deleteLiveData.value = "Deleted"
                load()
            }
            it.onFailure { throwable ->
                errorLiveData.value = throwable.message
            }
        }.launchIn(viewModelScope)
    }

    override fun load() {
        progressLiveData.value = true
        useCase.getUserCarts().onEach {
            progressLiveData.value = false
            it.onSuccess { response ->
                loadLiveData.value = response
            }
            it.onFailure { throwable ->
                errorLiveData.value = throwable.message
            }
        }.launchIn(viewModelScope)
    }
}