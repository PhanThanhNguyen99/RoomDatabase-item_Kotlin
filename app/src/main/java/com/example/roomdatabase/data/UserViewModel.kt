package com.example.roomdatabase.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Appendable
import androidx.lifecycle.asLiveData

class UserViewModel(application: Application):AndroidViewModel(application) {
    val readAllData : LiveData<List<User>>

    private  val repositpry :UserRepositpry
     init {
         val userDao = UserDatabase.getDatabase(application).userDao()
         repositpry = UserRepositpry(userDao)
         readAllData = repositpry.readAllData
     }
    fun  addUser(user: User){
        viewModelScope.launch(Dispatchers.IO){
          repositpry.addUser(user)
        }
    }
    fun updateUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repositpry.upDateUser(user)
        }
    }
    fun deleteUser(user: User){
        viewModelScope.launch(Dispatchers.IO){
            repositpry.deleteuser(user)
        }
    }
    fun deleteAllUser(){
        viewModelScope.launch(Dispatchers.IO) {
            repositpry.deleteAllUser()
        }
    }
    fun search(searchQuery :String ): LiveData<List<User>> {
        return  repositpry.search(searchQuery).asLiveData()
    }
}