package uz.gita.onlineshopallayar.app

import android.app.Application
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import uz.gita.onlineshopallayar.BuildConfig
import uz.gita.onlineshopallayar.data.ProductData


@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

    companion object {
        lateinit var instance: App
            private set

        val openDetailScreenLiveData = MutableLiveData<ProductData?>()
        val reloadCartPageLiveData = MutableLiveData<Unit>()

        fun openDetailScreen(data: ProductData) {
            openDetailScreenLiveData.value = data
            openDetailScreenLiveData.value = null
        }

        fun reloadCartPage() {
            reloadCartPageLiveData.value = Unit
        }
    }
}