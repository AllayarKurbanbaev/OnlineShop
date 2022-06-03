package uz.gita.onlineshopallayar.presentation.viewmodel.impl

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.onlineshopallayar.presentation.viewmodel.CartViewModel
import javax.inject.Inject

@HiltViewModel
class CartViewModelImpl @Inject constructor(): ViewModel(), CartViewModel{
}