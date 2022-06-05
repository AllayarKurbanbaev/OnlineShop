package uz.gita.onlineshopallayar.data.locale.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CartEntity(
    @PrimaryKey
    val id : Int,
    val productName : String,
    val productDescription : String,
    val productPrice : Double,
    val userId : Int,
    val quantity : Int,
    val image : String
) {
    override fun equals(other: Any?): Boolean {
        if (other == null) return false
        if (other === this) return true
        return if (other is CartEntity) {
            other.quantity == this.quantity && other.productName == this.productName
        } else false
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}


