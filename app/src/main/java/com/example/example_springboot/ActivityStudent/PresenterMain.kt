package com.example.example_springboot.ActivityStudent

import com.example.example_springboot.model.Student
import com.example.example_springboot.model.database.StudentDao
import com.example.example_springboot.model.server.ApiManager

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
        })

        //data from database=>
        val studentListFromDatabase=studentDao.getAllStudents()
        mainView!!.showAllStudentFromDatabase(studentListFromDatabase)

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