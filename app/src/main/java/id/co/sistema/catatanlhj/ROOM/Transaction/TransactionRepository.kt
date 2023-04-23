package id.co.sistema.catatanlhj.ROOM.Transaction

import androidx.lifecycle.LiveData
import id.co.sistema.catatanlhj.ROOM.Transaction.TransactionDao
import id.co.sistema.catatanlhj.ROOM.User

class TransactionRepository(private val transDao: TransactionDao) {

    suspend fun addTrans(trans:Transaction){
        transDao.addTrans(trans)
    }

    val readAllData: LiveData<List<Transaction>> = transDao.readAllTrans()

    val readIncome: LiveData<Int> = transDao.readIncome()

    val readSpend: LiveData<Int> = transDao.readSpend()

    val readSaldo:LiveData<Int> = transDao.readSaldo()

   suspend fun deleteAll(){
        transDao.deleteAll()
    }
}