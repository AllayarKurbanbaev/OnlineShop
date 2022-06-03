package uz.gita.onlineshopallayar.data.locale.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import uz.gita.onlineshopallayar.data.ProductData
import uz.gita.onlineshopallayar.data.remote.model.response.RatingResponse


@Entity
data class ProductResponseEntity(
    @PrimaryKey
    val id : Int,
    val title : String,
    val price : Double,
    val description : String,
    val image : String
)

fun ProductResponseEntity.toProductData() : ProductData = ProductData(
    this.id,
    this.title,
    this.price,
    this.description,
    this.image
)

