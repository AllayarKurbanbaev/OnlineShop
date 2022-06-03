package uz.gita.onlineshopallayar.di

import android.content.Context
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.gita.onlineshopallayar.BuildConfig.BASE_URL
import uz.gita.onlineshopallayar.data.remote.api.Api
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @[Provides Singleton]
    fun getOkHTTPClientObject(@ApplicationContext context: Context): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(ChuckInterceptor(context))
            .build()

    @[Provides Singleton]
    fun getRetrofitObject(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @[Provides Singleton]
    fun getApi(retrofit: Retrofit): Api = retrofit.create(Api::class.java)
}