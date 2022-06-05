package uz.gita.onlineshopallayar.presentation.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.onlineshopallayar.data.ProductData

interface DetailViewModel {
    val onBackPressedLiveData: LiveData<Unit>
    val addToCardLiveData: LiveData<String>
    val errorLiveData: LiveData<String>


    fun back()
    fun addToCart(product: ProductData)
}