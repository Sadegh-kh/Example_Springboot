package com.example.example_springboot.ActivityAddStudent

import com.example.example_springboot.model.Student
import com.example.example_springboot.util.BasePresenter

interface AddStudentContract {

    interface Presenter:BasePresenter<View>{

        fun addNewStudent(student: Student)
    }
    interface View{

        //don't need any fun because i added on my server
        fun showNewStudent(student: Student)
    }
}