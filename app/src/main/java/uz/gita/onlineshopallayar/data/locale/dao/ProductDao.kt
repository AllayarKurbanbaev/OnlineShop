package uz.gita.onlineshopallayar.data.locale.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import org.jetbrains.annotations.NotNull
import uz.gita.onlineshopallayar.data.locale.entities.CartEntity
import uz.gita.onlineshopallayar.data.locale.entities.ProductResponseEntity

@Dao
interface ProductDao {

    @Query("SELECT * FROM ProductResponseEntity")
    @NotNull
    suspend fun getAllProducts(): List<ProductResponseEntity>

    @Query("SELECT * FROM ProductResponseEntity WHERE id =  :id")
    suspend fun getProductById(id: Int): ProductResponseEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @NotNull
    suspend fun insert(list: List<ProductResponseEntity>)

    @Query("DELETE FROM ProductResponseEntity")
    @NotNull
    suspend fun deleteAllProducts()






}


