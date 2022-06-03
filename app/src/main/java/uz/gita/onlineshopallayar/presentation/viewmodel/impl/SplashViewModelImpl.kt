package uz.gita.onlineshopallayar.presentation.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import uz.gita.onlineshopallayar.domain.usecase.SplashUseCase
import uz.gita.onlineshopallayar.presentation.viewmodel.SplashViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModelImpl @Inject constructor(
    private val useCase: SplashUseCase
) : ViewModel(), SplashViewModel {
    override val openNextScreenLiveData = MutableLiveData<Boolean>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            Timber.tag("TTT").d(useCase.checking().toString())
            delay(2000)
            openNextScreenLiveData.postValue(useCase.checking())
        }
    }
}