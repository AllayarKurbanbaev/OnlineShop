package uz.gita.onlineshopallayar.presentation.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.onlineshopallayar.data.remote.model.request.LoginRequest

interface SignInViewModel {

    val loginButtonLiveData : LiveData<String>
    val progressLiveData : LiveData<Boolean>
    val openMainScreenLiveData : LiveData<Unit>
    val errorLiveData : LiveData<String>


    fun login(loginRequest: LoginRequest)
}