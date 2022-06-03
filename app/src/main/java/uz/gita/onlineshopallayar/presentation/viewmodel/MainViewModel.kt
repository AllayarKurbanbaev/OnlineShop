package uz.gita.onlineshopallayar.presentation.viewmodel

import androidx.lifecycle.LiveData

interface MainViewModel {
    val navigateNextScreenLiveData: LiveData<Int>
    val toolbarTitleLiveData: LiveData<String>
    val messageLiveData: LiveData<String>
    val signOutLiveData: LiveData<Unit>

    fun onMenuSelected(position: Int, title: String)
    fun onClickSignOut()
}