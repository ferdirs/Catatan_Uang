package id.co.sistema.catatanlhj.ROOM.Transaction

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.co.sistema.catatanlhj.ROOM.User

@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTrans(transaction: Transaction)

    @Query("SELECT * FROM trans_table ORDER BY id ASC")
    fun readAllTrans(): LiveData<List<Transaction>>

    @Query("SELECT SUM(income) FROM trans_table")
    fun readIncome(): LiveData<Int>

    @Query("SELECT SUM(spend) FROM trans_table")
    fun readSpend(): LiveData<Int>

    @Query("SELECT (SELECT SUM(income) FROM trans_table) - (SELECT SUM(spend) FROM trans_table)")
    fun readSaldo():LiveData<Int>

    @Query("DELETE from trans_table")
    suspend fun deleteAll()
}