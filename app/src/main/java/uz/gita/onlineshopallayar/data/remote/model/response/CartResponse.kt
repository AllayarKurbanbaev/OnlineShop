package uz.gita.onlineshopallayar.data.remote.model.response

data class CartResponse(
    val id: Int,
    val userId: Int,
    val date: String,
    val products: List<ProductItem>
)
