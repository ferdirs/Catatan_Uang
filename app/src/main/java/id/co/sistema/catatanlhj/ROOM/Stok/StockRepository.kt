package id.co.sistema.catatanlhj.ROOM.Stok

import androidx.lifecycle.LiveData
import id.co.sistema.catatanlhj.ROOM.Transaction.Transaction
import kotlinx.coroutines.flow.Flow

class StockRepository(private val stockDao: StockDao) {

    suspend fun addStock(stock: Stock){
        stockDao.addStock(stock)
    }

    val readAllData: LiveData<List<Stock>> = stockDao.readAllStock()

    suspend fun updateUser(stock: Stock){
        stockDao.updateItem(stock)
    }

    suspend fun deleteStock(stock: Stock){
        stockDao.deleteStock(stock)
    }

    suspend fun deleteAllStock(){
        stockDao.deleteAllStock()
    }

    fun searchDatabase(searchQuery: String): Flow<List<Stock>> {
        return stockDao.searchDatabase(searchQuery)
    }
}