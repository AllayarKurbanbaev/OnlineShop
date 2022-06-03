package uz.gita.onlineshopallayar.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.gita.onlineshopallayar.data.locale.Database
import uz.gita.onlineshopallayar.data.locale.dao.Dao
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class LocaleModule {
    @[Provides Singleton]
    fun getDatabase(@ApplicationContext context: Context): Database =
        Room.databaseBuilder(context, Database::class.java, "OnlineShopDatabase").build()

    @[Provides Singleton]
    fun getDao(database: Database): Dao = database.getDao()
}