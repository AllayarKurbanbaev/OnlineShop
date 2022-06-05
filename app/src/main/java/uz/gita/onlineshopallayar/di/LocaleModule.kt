package uz.gita.onlineshopallayar.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.gita.onlineshopallayar.data.locale.MyDatabase
import uz.gita.onlineshopallayar.data.locale.dao.CartDao
import uz.gita.onlineshopallayar.data.locale.dao.ProductDao
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class LocaleModule {
    @[Provides Singleton]
    fun getDatabase(@ApplicationContext context: Context): MyDatabase =
        Room.databaseBuilder(context, MyDatabase::class.java, "MyDatabase").build()

    @[Provides Singleton]
    fun getProductDao(database: MyDatabase): ProductDao = database.getProductDao()

    @[Provides Singleton]
    fun getCartDao(database: MyDatabase): CartDao = database.getCartDao()
}