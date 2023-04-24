package id.co.sistema.catatanlhj.ROOM.Stok

import androidx.lifecycle.LiveData
import androidx.room.*
import id.co.sistema.catatanlhj.ROOM.Transaction.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface StockDao {

    @Query("SELECT nama_item FROM stock_table")
    fun getItemName(): LiveData<String>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addStock(stock: Stock)

    @Query("SELECT * FROM stock_table ORDER BY id ASC")
    fun readAllStock(): LiveData<List<Stock>>

    @Update
    fun updateItem(item: Stock)

    @Query("DELETE FROM stock_table")
    suspend fun deleteAllStock()

    @Delete
    suspend fun deleteStock(stock: Stock)

    @Query("SELECT * FROM stock_table WHERE nama_item LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): Flow<List<Stock>>

}