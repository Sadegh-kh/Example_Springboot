package com.example.example_springboot.ActivityStudent

import com.example.example_springboot.model.Student
import com.example.example_springboot.util.BasePresenter

interface MainContract {

    interface PresenterMain:BasePresenter<ViewMain>{

        fun onDeleteStudent(student: Student,position:Int)
        fun onUpdateStudent(student: Student,position: Int)

    }

    interface ViewMain{

        fun showAllStudent(studentList: List<Student>)
        fun deleteStudent(oldStudent: Student,position: Int)
        fun updateStudent(editedStudent: Student,position: Int)

    }
}