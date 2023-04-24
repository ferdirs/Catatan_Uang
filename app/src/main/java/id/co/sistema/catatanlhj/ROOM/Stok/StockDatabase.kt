package id.co.sistema.catatanlhj.ROOM.Stok

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.co.sistema.catatanlhj.ROOM.Transaction.Transaction
import id.co.sistema.catatanlhj.ROOM.Transaction.TransactionDao
import id.co.sistema.catatanlhj.ROOM.Transaction.TransactionDatabase
import kotlinx.coroutines.InternalCoroutinesApi

@Database(entities = [Stock::class], version = 1, exportSchema = false )
abstract class StockDatabase:RoomDatabase() {

    abstract fun stockDao(): StockDao

    companion object {
        @Volatile
        private var INSTANCE: StockDatabase? = null

        @OptIn(InternalCoroutinesApi::class)
        fun getDatabase(context: Context): StockDatabase {
            val tempInstance = StockDatabase.INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            kotlinx.coroutines.internal.synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    StockDatabase::class.java,
                    "stock_table"
                ).build()
                StockDatabase.INSTANCE = instance
                return instance
            }
        }
    }
}