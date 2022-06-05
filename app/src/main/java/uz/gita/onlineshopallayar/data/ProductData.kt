package uz.gita.onlineshopallayar.data

import java.io.Serializable

data class ProductData(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val image: String
) : Serializable