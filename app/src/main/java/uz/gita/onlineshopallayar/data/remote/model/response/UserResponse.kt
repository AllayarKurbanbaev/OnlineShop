package uz.gita.onlineshopallayar.data.remote.model.response

import uz.gita.onlineshopallayar.data.remote.model.Address
import uz.gita.onlineshopallayar.data.remote.model.Name

data class UserResponse(
    val _id: Int,
    val address: Address,
    val email: String,
    val id: Int,
    val name: Name,
    val password: String,
    val phone: String,
    val username: String
)