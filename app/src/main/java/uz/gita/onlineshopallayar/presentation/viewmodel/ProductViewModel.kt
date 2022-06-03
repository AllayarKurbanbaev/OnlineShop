package uz.gita.onlineshopallayar.presentation.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.onlineshopallayar.data.ProductData
import uz.gita.onlineshopallayar.data.remote.model.request.CartRequest
import uz.gita.onlineshopallayar.data.remote.model.response.CartResponse
import uz.gita.onlineshopallayar.data.remote.model.response.ProductResponse

interface ProductViewModel {
    val progressLiveData: LiveData<Boolean>
    val errorLiveData: LiveData<String>
    val openDetailScreenLiveData: LiveData<Unit>
    val addCartLiveData: LiveData<String>
    val loadLiveData: LiveData<List<ProductData>>

    fun loadData()
    fun addToCart(cartRequest: CartRequest)
    fun openDetail()
    fun saveProductId(id : Int)
}