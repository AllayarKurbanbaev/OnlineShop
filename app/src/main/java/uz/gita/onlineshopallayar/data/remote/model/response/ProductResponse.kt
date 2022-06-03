package uz.gita.onlineshopallayar.data.remote.model.response

import uz.gita.onlineshopallayar.data.ProductData
import uz.gita.onlineshopallayar.data.locale.entities.ProductResponseEntity

data class ProductResponse(
    val id : Int,
    val title:  String,
    val price : Double,
    val description : String,
    val category: String,
    val image : String,
    val rating : RatingResponse
)

fun ProductResponse.toProductEntity() : ProductResponseEntity = ProductResponseEntity(
    this.id,
    this.title,
    this.price,
    this.description,
    this.image
)

fun ProductResponse.toProductData() : ProductData = ProductData(
    this.id,
    this.title,
    this.price,
    this.description,
    this.image
)

