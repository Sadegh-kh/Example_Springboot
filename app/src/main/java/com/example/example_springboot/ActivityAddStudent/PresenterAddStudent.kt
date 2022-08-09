package com.example.example_springboot.ActivityAddStudent

import android.util.Log
import com.example.example_springboot.model.Student
import com.example.example_springboot.model.database.StudentDao
import com.example.example_springboot.model.server.ApiManager

class PresenterAddStudent(private val studentDao: StudentDao,private val apiManager: ApiManager):
    AddStudentContract.PresenterAddStudent {
    var mainView: AddStudentContract.ViewAddStudent ?= null
    override fun onAttach(view: AddStudentContract.ViewAddStudent) {
        mainView=view

        apiManager.getAllStudent(object :ApiManager.ApiCallBack<List<Student>>{
            override fun onSuccess(data: List<Student>) {
                mainView!!.showNewStudentId(data.size)
            }

            override fun onError(error: String) {
                mainView!!.showMassageFromServer(error)
            }
        })
    }

    override fun onDetach() {
        mainView=null
    }

    override fun addNewStudent(student: Student) {
        //add on api =>
//        studentDao.insertStudent(student)

        apiManager.insertStudent(object :ApiManager.ApiCallBack<String>{
            override fun onSuccess(data: String) {
                mainView!!.showMassageFromServer(data)
            }

            override fun onError(error: String) {
                mainView!!.showMassageFromServer(error)
            }
        },student)
    }

}