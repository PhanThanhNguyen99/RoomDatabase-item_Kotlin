package com.example.roomdatabase.data

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDAO {
    @Insert()
    suspend fun addUser(user :User)

    @Query("Select * from user_table")
     fun readAllUser(): LiveData<List<User>>

     @Update
     suspend fun updateUsr(user: User)

     @Delete
     suspend fun deleteUser(user: User)

     @Query("Delete From user_table")
     suspend fun deleteAllUser()

     @Query("Select * from user_table where name like :searchQuery or address like :searchQuery")
     fun search(searchQuery : String): Flow<List<User>>

}