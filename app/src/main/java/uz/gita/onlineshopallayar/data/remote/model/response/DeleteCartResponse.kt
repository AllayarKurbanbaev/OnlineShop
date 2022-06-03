package uz.gita.onlineshopallayar.data.remote.model.response

data class DeleteCartResponse(
    val __v: Int,
    val date: String,
    val id: Int,
    val products: List<ProductItem>,
    val userId: Int
)