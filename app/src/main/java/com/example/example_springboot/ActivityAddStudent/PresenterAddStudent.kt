package com.example.example_springboot.ActivityAddStudent

import com.example.example_springboot.model.Student
import com.example.example_springboot.model.database.StudentDao
import com.example.example_springboot.model.server.ApiManager
import com.google.gson.JsonObject
import io.reactivex.disposables.Disposable

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

            override fun onSubscribe(disposable: Disposable) {
                mainView!!.disposableStudentAddActivity(disposable)
            }
        })
    }

    override fun onDetach() {
        mainView=null
    }

    override fun addNewStudent(student: Student) {
        //add on api =>
//        studentDao.insertStudent(student)

        val jsonStudentObject= JsonObject()
        jsonStudentObject.addProperty("id",student.id)
        jsonStudentObject.addProperty("firstName",student.firstName)
        jsonStudentObject.addProperty("lastName",student.lastName)
        jsonStudentObject.addProperty("course",student.course)
        jsonStudentObject.addProperty("score",student.score)

        apiManager.insertStudent(object :ApiManager.ApiCallBack<String>{
            override fun onSuccess(data: String) {
                mainView!!.showMassageFromServer(data)
            }

            override fun onError(error: String) {
                mainView!!.showMassageFromServer(error)
            }

            override fun onSubscribe(disposable: Disposable) {
                mainView!!.disposableStudentAddActivity(disposable)
            }
        },jsonStudentObject)
    }

}