package com.example.example_springboot.ActivityAddStudent

import com.example.example_springboot.model.Student

class Presenter:AddStudentContract.Presenter {
    var mainView: AddStudentContract.View ?= null
    override fun onAttach(view: AddStudentContract.View) {
        mainView=view
    }

    override fun onDetach() {
        mainView=null
    }

    override fun addNewStudent(student: Student) {
        //add on api =>
        //....

        mainView!!.showNewStudent(student)
    }

}