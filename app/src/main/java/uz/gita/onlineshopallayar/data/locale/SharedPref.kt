package uz.gita.onlineshopallayar.data.locale

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import uz.gita.onlineshopallayar.utils.SharedPreference
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPref @Inject constructor(@ApplicationContext context: Context) :
    SharedPreference(context) {


    var username: String by StringPreference("")
    var password: String by StringPreference("")

    var userId: Int by IntPreference(0)
    var productId: Int by IntPreference(0)


}