package com.example.example_springboot.ActivityAddStudent

import com.example.example_springboot.model.Student
import com.example.example_springboot.util.BasePresenter
import io.reactivex.disposables.Disposable

interface AddStudentContract {

    interface PresenterAddStudent:BasePresenter<ViewAddStudent>{

        fun addNewStudent(student: Student)
    }
    interface ViewAddStudent{

        fun showMassageFromServer(massage:String)
        fun showNewStudentId(id:Int)
        fun disposableStudentAddActivity(disposable: Disposable)
    }
}