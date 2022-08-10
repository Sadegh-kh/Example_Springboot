package com.example.example_springboot.ActivityStudent

import com.example.example_springboot.model.Student
import com.example.example_springboot.util.BasePresenter
import io.reactivex.disposables.Disposable

interface MainContract {

    interface PresenterMain:BasePresenter<ViewMain>{

        fun onDeleteStudent(student: Student,position:Int)
        fun onUpdateStudent(student: Student,position: Int)

    }

    interface ViewMain{

        fun showAllStudentFromServer(studentList: List<Student>)
        fun showAllStudentFromDatabase(studentList: List<Student>)
        fun deleteStudent(oldStudent: Student,position: Int)
        fun updateStudent(editedStudent: Student,position: Int)

        fun showError(error:String)

        fun disposableStudent(disposable: Disposable)

    }
}