package uz.gita.onlineshopallayar.presentation.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.onlineshopallayar.data.locale.entities.CartEntity

interface CartViewModel {
    val errorLiveData: LiveData<String>
    val loadLiveData: LiveData<List<CartEntity>>
    val orderLiveData: LiveData<String>
    val deleteLiveData: LiveData<String>
    val messageLiveData: LiveData<String>

    fun order()
    fun plusClick(pos: Int)
    fun minusClick(pos: Int)
    fun delete(product: CartEntity, pos: Int)
    fun load()
}