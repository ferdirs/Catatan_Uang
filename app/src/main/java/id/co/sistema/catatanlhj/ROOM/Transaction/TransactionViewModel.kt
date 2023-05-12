package id.co.sistema.catatanlhj.ROOM.Transaction

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.co.sistema.catatanlhj.ROOM.User
import id.co.sistema.catatanlhj.ROOM.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class TransactionViewModel(application: Application): AndroidViewModel(application) {

    private val readAllTrans: LiveData<List<Transaction>>
    private val readAllIncome: LiveData<Int>
    private val readAllSpend: LiveData<Int>
    private val readSaldo: LiveData<Int>
    private val countIncome: LiveData<Int>
    private val repository: TransactionRepository
    private val countBoth: LiveData<Transaction>
    private val countSpend: LiveData<Int>
    private val countTrans: LiveData<Int>

    init {
        val transDao = TransactionDatabase.getDatabase(application).TransactionDao()
        repository = TransactionRepository(transDao)
        readAllTrans = repository.readAllData
        readAllIncome = repository.readIncome
        readAllSpend = repository.readSpend
        readSaldo = repository.readSaldo
        countIncome = repository.countIncome
        countBoth = repository.countBoth
        countSpend = repository.countSpend
        countTrans = repository.countTrans
    }

    fun addTrans(trans: Transaction){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTrans(trans)
        }
    }

    fun getAllTrans():LiveData<List<Transaction>>{
         return readAllTrans
    }

    fun getAllIncome():LiveData<Int>{
        return readAllIncome
    }

    fun getAllSpend():LiveData<Int>{
        return readAllSpend
    }

    fun getTotal():LiveData<Int>{
        return readSaldo
    }

    fun deleteAll(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAll()
        }
    }

    fun countIncome():LiveData<Int>{
        return countIncome
    }

    fun countSpend():LiveData<Int>{
        return countSpend
    }

    fun countTrans():LiveData<Int>{
        return countTrans
    }

    fun deleteEntry(trans: Transaction) = viewModelScope.launch {
        repository.deleteEntry(trans)
    }

}