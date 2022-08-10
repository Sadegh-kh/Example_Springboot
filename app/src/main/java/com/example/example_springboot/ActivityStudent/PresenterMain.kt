package com.example.example_springboot.ActivityStudent

import com.example.example_springboot.model.Student
import com.example.example_springboot.model.database.StudentDao
import com.example.example_springboot.model.server.ApiManager
import com.google.gson.Gson
import com.google.gson.JsonObject
import io.reactivex.disposables.Disposable

class PresenterMain(private val studentDao: StudentDao,private val apiManager: ApiManager) : MainContract.PresenterMain {
    private var mainView: MainContract.ViewMain? = null
    override fun onAttach(view: MainContract.ViewMain) {
       mainView=view

        //data from server=>
        apiManager.getAllStudent(object :ApiManager.ApiCallBack<List<Student>>{
            override fun onSuccess(data: List<Student>) {
                mainView!!.showAllStudentFromServer(data)
            }
            override fun onError(error: String) {
                mainView!!.showError(error)
            }

            override fun onSubscribe(disposable: Disposable) {
               mainView!!.disposableStudent(disposable)
            }
        })

        //data from database=>
        val studentListFromDatabase=studentDao.getAllStudents()
        mainView!!.showAllStudentFromDatabase(studentListFromDatabase)

    }

    override fun onDetach() {
        mainView=null
    }


    override fun onDeleteStudent(student: Student, position: Int) {
//        studentDao.deleteStudent(student)

        apiManager.deleteStudent(object :ApiManager.ApiCallBack<String>{
            override fun onSuccess(data: String) {
                mainView!!.showError(data)
            }

            override fun onError(error: String) {
                mainView!!.showError(error)
            }

            override fun onSubscribe(disposable: Disposable) {
                TODO("Not yet implemented")
            }
        },student.id!!)
        mainView!!.deleteStudent(student,position)
    }

    override fun onUpdateStudent(student: Student, position: Int) {
//        studentDao.updateStudent(student)
        val jsonStudentObject= JsonObject()
        jsonStudentObject.addProperty("id",student.id)
        jsonStudentObject.addProperty("firstName",student.firstName)
        jsonStudentObject.addProperty("lastName",student.lastName)
        jsonStudentObject.addProperty("course",student.course)
        jsonStudentObject.addProperty("score",student.score)

        apiManager.updateStudent(object :ApiManager.ApiCallBack<String>{
            override fun onSuccess(data: String) {
                mainView!!.showError(data)
            }

            override fun onError(error: String) {
                mainView!!.showError(error)
            }

            override fun onSubscribe(disposable: Disposable) {
                TODO("Not yet implemented")
            }
        },student.id!!,jsonStudentObject)
        mainView!!.updateStudent(student,position)
    }
}