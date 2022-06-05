package uz.gita.onlineshopallayar.presentation.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import uz.gita.onlineshopallayar.data.ProductData
import uz.gita.onlineshopallayar.domain.usecase.ProductUseCase
import uz.gita.onlineshopallayar.presentation.viewmodel.ProductViewModel
import javax.inject.Inject

@HiltViewModel
class ProductViewModelImpl @Inject constructor(
    private val useCase: ProductUseCase
) : ViewModel(), ProductViewModel {
    override val progressLiveData = MutableLiveData<Boolean>()
    override val errorLiveData = MutableLiveData<String>()
    override val openDetailScreenLiveData = MutableLiveData<Unit>()
    override val addCartLiveData = MutableLiveData<String>()
    override val loadLiveData = MutableLiveData<List<ProductData>>()


    init {
        loadData()
    }

    override fun loadData() {
        progressLiveData.value = true
        useCase.getAllProduct().onEach {
            progressLiveData.value = false
            it.onSuccess { listData ->
                Timber.tag("SSS").d(listData.toString())
                loadLiveData.value = listData
            }
            it.onFailure { throwable ->
                errorLiveData.value = throwable.message
            }
        }.launchIn(viewModelScope)
    }


    override fun addToCart(product: ProductData) {
        progressLiveData.value = true
        useCase.addProductToCart(product).onEach {
            progressLiveData.value = false
            it.onSuccess {
                addCartLiveData.value = "Added"
            }
            it.onFailure { throwable ->
                errorLiveData.value = throwable.message
            }
        }.launchIn(viewModelScope)
    }

    override fun openDetail() {
        openDetailScreenLiveData.value = Unit
    }

    override fun saveProductId(id: Int) {
        useCase.saveProductId(id)
    }
}