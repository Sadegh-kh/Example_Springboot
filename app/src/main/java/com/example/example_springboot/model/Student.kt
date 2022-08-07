package com.example.example_springboot.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "Table Student")
data class Student(
    val firstName:String,
    val lastName:String,
    val Course:String,
    val Score:String,
    @PrimaryKey(autoGenerate = true)
    val id:Int?=null
):Parcelable