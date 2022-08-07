package com.example.example_springboot.ActivityAddStudent

import com.example.example_springboot.model.Student
import com.example.example_springboot.model.StudentDao

class PresenterAddStudent(private val studentDao: StudentDao):
    AddStudentContract.PresenterAddStudent {
    var mainView: AddStudentContract.ViewAddStudent ?= null
    override fun onAttach(view: AddStudentContract.ViewAddStudent) {
        mainView=view
    }

    override fun onDetach() {
        mainView=null
    }

    override fun addNewStudent(student: Student) {
        //add on api =>
        studentDao.insertStudent(student)
    }

}