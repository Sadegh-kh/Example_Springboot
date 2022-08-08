package com.example.example_springboot.ActivityStudent

import com.example.example_springboot.model.Student
import com.example.example_springboot.model.database.StudentDao

class PresenterMain(private val studentDao: StudentDao) : MainContract.PresenterMain {
    private var mainView: MainContract.ViewMain? = null

    override fun onAttach(view: MainContract.ViewMain) {
       mainView=view
        val studentList=studentDao.getAllStudents()
        mainView!!.showAllStudent(studentList)
    }

    override fun onDetach() {
        mainView=null
    }

    override fun onDeleteStudent(student: Student, position: Int) {
        studentDao.deleteStudent(student)
        mainView!!.deleteStudent(student,position)
    }

    override fun onUpdateStudent(student: Student, position: Int) {
        studentDao.updateStudent(student)
        mainView!!.updateStudent(student,position)
    }
}