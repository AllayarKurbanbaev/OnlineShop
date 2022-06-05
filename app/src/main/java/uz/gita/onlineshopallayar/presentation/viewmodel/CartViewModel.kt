package uz.gita.onlineshopallayar.presentation.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.onlineshopallayar.data.locale.entities.CartEntity
import uz.gita.onlineshopallayar.data.locale.entities.ProductResponseEntity
import uz.gita.onlineshopallayar.data.remote.model.response.CartResponse

interface CartViewModel {

    val errorLiveData: LiveData<String>
    val progressLiveData: LiveData<Boolean>
    val loadLiveData: LiveData<List<CartEntity>>
    val orderLiveData: LiveData<String>
    val deleteLiveData: LiveData<String>
    val messageLiveData : LiveData<String>


    fun order()

    fun check(product: CartEntity)
    fun plusClick(id : Int)
    fun minusClick(id : Int)
    fun delete(product: CartEntity)

    fun load()
}