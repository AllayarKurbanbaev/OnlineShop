package uz.gita.onlineshopallayar.data.locale.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.gita.onlineshopallayar.data.locale.entities.ProductResponseEntity

@Dao
interface ProductDao {

    @Query("SELECT * FROM ProductResponseEntity")
    suspend fun getAllProducts(): List<ProductResponseEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<ProductResponseEntity>)

    @Query("DELETE FROM ProductResponseEntity")
    suspend fun deleteAllProducts()
}


