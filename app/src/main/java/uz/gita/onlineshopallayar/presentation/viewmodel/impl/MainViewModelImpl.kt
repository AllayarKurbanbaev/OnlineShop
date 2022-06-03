package uz.gita.onlineshopallayar.presentation.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.onlineshopallayar.domain.usecase.MainUseCase
import uz.gita.onlineshopallayar.presentation.viewmodel.MainViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModelImpl @Inject constructor(
    private val useCase: MainUseCase
) : ViewModel(), MainViewModel {
    override val navigateNextScreenLiveData = MutableLiveData<Int>()
    override val toolbarTitleLiveData = MutableLiveData<String>()
    override val messageLiveData = MutableLiveData<String>()
    override val signOutLiveData = MutableLiveData<Unit>()

    override fun onMenuSelected(position: Int, title: String) {
        navigateNextScreenLiveData.value = position
        toolbarTitleLiveData.value = title
    }

    override fun onClickSignOut() {
        useCase.signOut()
        signOutLiveData.value = Unit
    }
}