package com.example.example_springboot.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface StudentDao {

    @Insert
    fun insertStudent(student: Student)

    @Insert
    fun insertAllStudents(studentList:ArrayList<Student>)

    @Query("SELECT * FROM `Table Student`")
    fun getAllStudents():List<Student>

}