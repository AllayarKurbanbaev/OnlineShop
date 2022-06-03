package uz.gita.onlineshopallayar.data.locale

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.gita.onlineshopallayar.data.locale.dao.Dao
import uz.gita.onlineshopallayar.data.locale.entities.ProductResponseEntity

@Database(entities = [ProductResponseEntity::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase(){
    abstract fun getDao() : Dao
}