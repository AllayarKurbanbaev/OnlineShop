package uz.gita.onlineshopallayar.presentation.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.onlineshopallayar.data.locale.entities.ProductResponseEntity
import uz.gita.onlineshopallayar.data.remote.model.response.CartResponse
import uz.gita.onlineshopallayar.domain.usecase.CartUseCase
import uz.gita.onlineshopallayar.presentation.viewmodel.CartViewModel
import javax.inject.Inject

@HiltViewModel
class CartViewModelImpl @Inject constructor(
    private val useCase: CartUseCase
) : ViewModel(), CartViewModel {
    override val errorLiveData = MutableLiveData<String>()
    override val progressLiveData = MutableLiveData<Boolean>()
    override val loadLiveData = MutableLiveData<List<ProductResponseEntity>>()
    override val orderLiveData = MutableLiveData<String>()
    override val plusLiveData = MutableLiveData<Unit>()
    override val minusLiveData = MutableLiveData<Unit>()
    override val deleteLiveData = MutableLiveData<String>()

    init {
        load()
    }

    override fun order() {

    }

    override fun plusClick() {

    }

    override fun minusClick() {

    }

    override fun delete() {

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