package id.co.sistema.catatanlhj.ROOM

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {

    private val realAllData: LiveData<List<User>>
    private val repository: UserRepository
    private val userDao = UserDatabase.getDatabase(application).userDao()
    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        realAllData = repository.readAllData
    }

    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO){
            repository.addUser(user)
        }
    }

    fun login(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            userDao.getUserByUsernameAndPassword(username, password)
        }
    }

}