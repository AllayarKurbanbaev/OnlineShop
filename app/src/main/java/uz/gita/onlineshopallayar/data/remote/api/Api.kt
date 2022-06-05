package uz.gita.onlineshopallayar.data.remote.api

import retrofit2.Response
import retrofit2.http.*
import uz.gita.onlineshopallayar.data.remote.model.request.CartRequest
import uz.gita.onlineshopallayar.data.remote.model.request.LoginRequest
import uz.gita.onlineshopallayar.data.remote.model.request.UserRequest
import uz.gita.onlineshopallayar.data.remote.model.response.*


interface Api {
    @GET("/products")
    suspend fun getAllProducts(): Response<List<ProductResponse>>

    @GET("/products/categories")
    suspend fun getAllCategories(): Response<CategoryResponse>

    @GET("/products/category")
    suspend fun getSingleCategory(@Url url : String): Response<List<ProductResponse>>

    @GET("/carts")
    suspend fun getAllCarts(): Response<List<CartResponse>>

    @GET("/carts")
    suspend fun getSingleCart(@Url url : String): Response<CartResponse>

    @GET
    suspend fun getUserCarts(@Url url : String): Response<List<CartResponse>>

    @POST("/carts")
    suspend fun addNewCart(@Body cartRequest: CartRequest): Response<CartResponse>

    @PUT
    suspend fun updateCart(
        @Url url : String,
        @Body cartRequest: CartRequest
    ): Response<CartResponse>

    @DELETE
    suspend fun deleteCart(@Url url : String): Response<CartResponse>

    @POST("/users")
    suspend fun registerUser(@Body userRequest: UserRequest): Response<UserResponse>

    @GET("/users")
    suspend fun getUser(@Url url : String): Response<UserResponse>

    @PUT("/users")
    suspend fun updateUser(
        @Query("id") id: Int,
        @Body userRequest: UserRequest
    ): Response<UserResponse>

    @POST("/auth/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): Response<LoginResponse>

}