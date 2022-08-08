package com.example.example_springboot.model.database

import androidx.room.*
import com.example.example_springboot.model.Student

@Dao
interface StudentDao {

    @Insert
    fun insertStudent(student: Student)

    @Insert
    fun insertAllStudents(studentList:ArrayList<Student>)

    @Query("SELECT * FROM `Table Student`")
    fun getAllStudents():List<Student>

    @Update
    fun updateStudent(student: Student)

    @Delete
    fun deleteStudent(student: Student)

}