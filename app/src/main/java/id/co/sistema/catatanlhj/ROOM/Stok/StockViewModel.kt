package id.co.sistema.catatanlhj.ROOM.Stok

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import id.co.sistema.catatanlhj.ROOM.Transaction.Transaction
import id.co.sistema.catatanlhj.ROOM.Transaction.TransactionDatabase
import id.co.sistema.catatanlhj.ROOM.Transaction.TransactionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StockViewModel(application: Application): AndroidViewModel(application) {

    private val repository: StockRepository
    private val readAllStock: LiveData<List<Stock>>

    init {
        val stockDao = StockDatabase.getDatabase(application).stockDao()
        repository = StockRepository(stockDao)

        readAllStock = repository.readAllData
    }

    fun addStock(stock: Stock){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addStock(stock)
        }
    }

    fun getAllStock(): LiveData<List<Stock>> {
        return readAllStock
    }

    fun updateStock(stock: Stock){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateUser(stock)
        }
    }

    fun deleteStock(stock: Stock){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteStock(stock)
        }
    }

    fun deleteAllStock(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllStock()
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<Stock>> {
        return repository.searchDatabase(searchQuery).asLiveData()
    }

}