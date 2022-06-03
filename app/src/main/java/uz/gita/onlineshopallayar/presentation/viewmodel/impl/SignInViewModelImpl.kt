package uz.gita.onlineshopallayar.presentation.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import uz.gita.onlineshopallayar.data.remote.model.request.LoginRequest
import uz.gita.onlineshopallayar.domain.usecase.SignInUseCase
import uz.gita.onlineshopallayar.presentation.viewmodel.SignInViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModelImpl @Inject constructor(
    private val useCase: SignInUseCase
) : ViewModel(), SignInViewModel {
    override val loginButtonLiveData = MutableLiveData<String>()
    override val progressLiveData = MutableLiveData<Boolean>()
    override val openMainScreenLiveData = MutableLiveData<Unit>()
    override val errorLiveData = MutableLiveData<String>()

    override fun login(loginRequest: LoginRequest) {
        progressLiveData.value = true

        Timber.tag("TTT").d(loginRequest.toString())
        useCase.signInUser(loginRequest).onEach {
            progressLiveData.value = false
            it.onSuccess { data ->
                loginButtonLiveData.value = data.token
                openMainScreenLiveData.value = Unit
            }
            it.onFailure { error ->
                errorLiveData.value = error.message
            }
        }.launchIn(viewModelScope)
    }
}