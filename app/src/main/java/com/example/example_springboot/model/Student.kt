package com.example.example_springboot.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Student(
    val firstName:String,
    val lastName:String,
    val Course:String,
    val Score:Int
):Parcelable