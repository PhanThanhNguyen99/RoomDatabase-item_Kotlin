package com.example.roomdatabase.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "user_table")
data class User (
    @PrimaryKey(autoGenerate = true)
    var id :Int,
    var name :String,
    var address :String
) : Parcelable