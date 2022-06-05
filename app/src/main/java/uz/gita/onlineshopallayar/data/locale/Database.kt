package uz.gita.onlineshopallayar.data.locale

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.gita.onlineshopallayar.data.locale.dao.CartDao
import uz.gita.onlineshopallayar.data.locale.dao.ProductDao
import uz.gita.onlineshopallayar.data.locale.entities.CartEntity
import uz.gita.onlineshopallayar.data.locale.entities.ProductResponseEntity

@Database(
    entities = [ProductResponseEntity::class, CartEntity::class],
    version = 2,
    exportSchema = false
)
abstract class Database : RoomDatabase() {
    abstract fun getProductDao(): ProductDao
    abstract fun getCartDao() : CartDao
}