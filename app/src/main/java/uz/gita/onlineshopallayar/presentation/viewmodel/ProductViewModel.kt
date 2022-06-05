package uz.gita.onlineshopallayar.presentation.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.onlineshopallayar.data.ProductData

interface ProductViewModel {
    val progressLiveData: LiveData<Boolean>
    val errorLiveData: LiveData<String>
    val openDetailScreenLiveData: LiveData<Unit>
    val addCartLiveData: LiveData<String>
    val loadLiveData: LiveData<List<ProductData>>

    fun loadData()

    fun addToCart(product: ProductData)
    fun openDetail()
    fun saveProductId(id: Int)
}