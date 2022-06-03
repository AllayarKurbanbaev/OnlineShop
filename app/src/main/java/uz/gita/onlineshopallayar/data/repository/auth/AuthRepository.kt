package uz.gita.onlineshopallayar.data.repository.auth

import retrofit2.Response
import uz.gita.onlineshopallayar.data.remote.model.request.LoginRequest
import uz.gita.onlineshopallayar.data.remote.model.request.UserRequest
import uz.gita.onlineshopallayar.data.remote.model.response.LoginResponse
import uz.gita.onlineshopallayar.data.remote.model.response.UserResponse

interface AuthRepository {
    suspend fun signInUser(
        loginUserRequest: LoginRequest
    ) : Response<LoginResponse>



     fun signOut()

     fun check() : Boolean

     fun saveUserID(loginUserRequest: LoginRequest)
//    suspend fun signUpUser(
//        userData: UserRequest,
//
//    )
//
//    suspend fun editProfileData(
//        id : Int,
//        userData: UserRequest,
//        success: (UserResponse) -> Unit,
//        failure: (Throwable) -> Unit
//    )
//
//
//    suspend fun profileData(
//        success: (UserResponse) -> Unit,
//        failure: (Throwable) -> Unit
//    )
//
}