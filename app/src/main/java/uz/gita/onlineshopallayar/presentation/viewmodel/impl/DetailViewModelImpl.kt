package uz.gita.onlineshopallayar.presentation.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import uz.gita.onlineshopallayar.data.ProductData
import uz.gita.onlineshopallayar.domain.usecase.DetailUseCase
import uz.gita.onlineshopallayar.presentation.viewmodel.DetailViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModelImpl @Inject constructor(
    private val useCase: DetailUseCase
) : ViewModel(), DetailViewModel {
    override val onBackPressedLiveData = MutableLiveData<Unit>()
    override val progressLiveData = MutableLiveData<Boolean>()
    override val loadLiveData = MutableLiveData<ProductData>()
    override val addToCardLiveData = MutableLiveData<String>()
    override val errorLiveData = MutableLiveData<String>()


    override fun back() {
        onBackPressedLiveData.value = Unit
    }

    override fun addToCart(product: ProductData) {
        progressLiveData.value = true
        useCase.addProductToCart(product).onEach {
            progressLiveData.value = false
            it.onSuccess {
                addToCardLiveData.value = "Added"
            }
            it.onFailure { throwable ->
                errorLiveData.value = throwable.message
            }
        }.launchIn(viewModelScope)
    }


    override fun loadData() {
        progressLiveData.value = true
        useCase.getSingleProduct().onEach {
            Timber.tag("WWW").d(it.toString())
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