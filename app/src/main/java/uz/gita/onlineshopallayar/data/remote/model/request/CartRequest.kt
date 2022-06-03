package uz.gita.onlineshopallayar.data.remote.model.request

import uz.gita.onlineshopallayar.data.remote.model.response.ProductItem

data class CartRequest(
    val userId: Int,
    val date: String,
    val products: List<ProductItem>
)
