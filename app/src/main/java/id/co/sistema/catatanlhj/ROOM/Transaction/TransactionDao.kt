package id.co.sistema.catatanlhj.ROOM.Transaction

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

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

    @Query("SELECT COUNT(*) as income FROM trans_table WHERE income > 0")
    fun getCount(): LiveData<Int>

    @Query("SELECT COUNT(*) as spend FROM trans_table WHERE spend > 0")
    fun getCountSpend(): LiveData<Int>

    @Query("SELECT COUNT(*) as id FROM trans_table")
    fun getCountTrans(): LiveData<Int>

    @Query("SELECT COUNT(*) as id ,COUNT(*) as income, COUNT(*) as spend , COUNT(*) as notes ,COUNT(*) as jenis , COUNT(*) as nominal from trans_table WHERE income > 0 ")
    fun getCountBoth(): LiveData<Transaction>

    @Delete
    suspend fun deleteEntry(transaction: Transaction)
}