package com.example.example_springboot.model

import androidx.room.*

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