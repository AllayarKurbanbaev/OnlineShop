package uz.gita.onlineshopallayar.data.remote.model.request

import uz.gita.onlineshopallayar.data.remote.model.Address
import uz.gita.onlineshopallayar.data.remote.model.Name

data class UserRequest(
    val address: Address,
    val email: String,
    val name: Name,
    val password: String,
    val phone: String,
    val username: String
)
