package uz.gita.onlineshopallayar.presentation.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.onlineshopallayar.data.ProductData
import uz.gita.onlineshopallayar.data.remote.model.response.ProductResponse

interface DetailViewModel {
    val onBackPressedLiveData: LiveData<Unit>
    val progressLiveData: LiveData<Boolean>
    val loadLiveData: LiveData<ProductData>
    val addToCardLiveData : LiveData<String>
    val errorLiveData : LiveData<String>


    fun back()
    fun addToCart()
    fun loadData()
}