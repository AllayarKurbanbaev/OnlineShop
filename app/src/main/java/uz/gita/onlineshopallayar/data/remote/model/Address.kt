package uz.gita.onlineshopallayar.data.remote.model

data class Address(
    val city: String,
    val geolocation: Geolocation,
    val number: Int,
    val street: String,
    val zipcode: String
)