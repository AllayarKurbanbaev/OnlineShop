package uz.gita.onlineshopallayar.presentation.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.onlineshopallayar.data.locale.entities.ProductResponseEntity
import uz.gita.onlineshopallayar.data.remote.model.response.CartResponse

interface CartViewModel {

    val errorLiveData: LiveData<String>
    val progressLiveData: LiveData<Boolean>
    val loadLiveData: LiveData<List<ProductResponseEntity>>
    val orderLiveData: LiveData<String>
    val plusLiveData: LiveData<Unit>
    val minusLiveData: LiveData<Unit>
    val deleteLiveData: LiveData<String>

    fun order()
    fun plusClick()
    fun minusClick()
    fun delete()

    fun load()
}