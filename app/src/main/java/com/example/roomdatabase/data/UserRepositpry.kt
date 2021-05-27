package com.example.roomdatabase.data

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow


class UserRepositpry(private val userDAO: UserDAO) {
  val readAllData :LiveData<List<User>> = userDAO.readAllUser()
    suspend  fun addUser(user: User){
        userDAO.addUser(user)
    }
    suspend fun upDateUser(user: User){
        userDAO.updateUsr(user)
    }
    suspend fun deleteuser(user: User){
        userDAO.deleteUser(user)
    }
    suspend fun deleteAllUser(){
        userDAO.deleteAllUser()
    }
    fun search(searchQuery :String) : Flow<List<User>> {
        return userDAO.search(searchQuery)
    }
}