package id.co.sistema.catatanlhj.ROOM.Transaction

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.co.sistema.catatanlhj.ROOM.UserDao
import id.co.sistema.catatanlhj.ROOM.UserDatabase
import kotlinx.coroutines.InternalCoroutinesApi


@Database(entities = [Transaction::class], version = 1, exportSchema = false )
abstract class TransactionDatabase():RoomDatabase(){

    abstract fun TransactionDao(): TransactionDao

    companion object{
        @Volatile
        private var INSTANCE:TransactionDatabase? = null

        @OptIn(InternalCoroutinesApi::class)
        fun getDatabase(context: Context): TransactionDatabase {
            val tempInstance = TransactionDatabase.INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            kotlinx.coroutines.internal.synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TransactionDatabase::class.java,
                    "trans_database"
                ).build()
                TransactionDatabase.INSTANCE = instance
                return instance
            }
        }
    }
}
