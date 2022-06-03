package uz.gita.onlineshopallayar.data.repository.auth

import retrofit2.Response
import timber.log.Timber
import uz.gita.onlineshopallayar.data.locale.SharedPref
import uz.gita.onlineshopallayar.data.remote.api.Api
import uz.gita.onlineshopallayar.data.remote.model.request.LoginRequest
import uz.gita.onlineshopallayar.data.remote.model.response.LoginResponse
import uz.gita.onlineshopallayar.utils.Users
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: Api,
    private val preference: SharedPref
) : AuthRepository {

    override suspend fun signInUser(loginUserRequest: LoginRequest): Response<LoginResponse> {
        return api.loginUser(loginUserRequest)
    }

    override fun check(): Boolean {
        Timber.tag("TTT").d(preference.userId.toString())
        return preference.userId != 0
    }

    override fun signOut() {
        preference.userId = 0
        preference.username = ""
        preference.password = ""
    }

    override fun saveUserID(loginUserRequest: LoginRequest) {
        preference.username = loginUserRequest.username
        preference.password = loginUserRequest.password
        preference.userId = Users.checking(loginUserRequest.username)
    }

    //    override suspend fun signUpUser(
//        userData: UserRequest,
//        success: (UserResponse) -> Unit,
//        failure: (Throwable) -> Unit
//    ) {
//        val response = api.registerUser(userData)
//
//
//    }
//
//    override suspend fun editProfileData(
//        id: Int,
//        userData: UserRequest,
//        success: (UserResponse) -> Unit,
//        failure: (Throwable) -> Unit
//    ) {
//        val response = api.updateUser(id, userData)
//        response.onFailure {
//            failure.invoke(it)
//        }
//        response.onSuccess {
//            success.invoke(it)
//        }
//
//    }
//
//    override suspend fun profileData(
//        success: (UserResponse) -> Unit,
//        failure: (Throwable) -> Unit
//    ) {
//
//
//    }
//


}