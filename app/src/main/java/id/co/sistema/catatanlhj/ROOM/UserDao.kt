package id.co.sistema.catatanlhj.ROOM

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllUser(): LiveData<List<User>>

    @Query("SELECT * FROM user_table WHERE username = :username AND password = :password")
    fun getUserByUsernameAndPassword(username: String, password: String): User?


}