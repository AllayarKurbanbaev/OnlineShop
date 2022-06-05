package uz.gita.onlineshopallayar.data.locale.dao

import androidx.room.*
import uz.gita.onlineshopallayar.data.locale.entities.CartEntity

@Dao
interface CartDao {
    @Query("SELECT * FROM CartEntity WHERE userId = :userId ")
    suspend fun getAllCartProductsByUserId(userId: Int): List<CartEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: CartEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(product: CartEntity)

    @Query("UPDATE CartEntity set quantity = quantity+1 WHERE id = :id ")
    suspend fun increaseProductCount(id: Int)

    @Query("UPDATE CartEntity set quantity = quantity-1 WHERE id = :id ")
    suspend fun decreaseProductCount(id: Int)

    @Query("SELECT quantity FROM CartEntity WHERE id = :id")
    suspend fun getCurrentProductCount(id: Int): Int

    @Query("DELETE FROM CartEntity WHERE userId = :userId")
    suspend fun deleteAllProductByUserId(userId: Int)

    @Delete
    suspend fun delete(product: CartEntity)

}