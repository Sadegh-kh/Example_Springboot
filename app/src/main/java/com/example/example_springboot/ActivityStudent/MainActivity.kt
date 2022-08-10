package com.example.example_springboot.ActivityStudent

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.example_springboot.ActivityAddStudent.AddStudentActivity
import com.example.example_springboot.databinding.ActivityMainBinding
import com.example.example_springboot.databinding.DialogUpdateStudentBinding
import com.example.example_springboot.model.database.MyDatabase
import com.example.example_springboot.model.Student
import com.example.example_springboot.model.database.StudentDao
import com.example.example_springboot.model.server.ApiManager
import com.example.example_springboot.util.showToast
import io.reactivex.disposables.Disposable

class MainActivity : AppCompatActivity(), StudentAdapter.EventStudent, MainContract.ViewMain {
    lateinit var binding: ActivityMainBinding
    private lateinit var studentAdapter: StudentAdapter
    lateinit var studentDao: StudentDao
    lateinit var presenterMain: PresenterMain
    lateinit var apiManager: ApiManager

    lateinit var disposableMain: Disposable
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        studentDao = MyDatabase.getDatabase(this).studentDao
        apiManager = ApiManager()

        presenterMain = PresenterMain(studentDao, apiManager)
        presenterMain.onAttach(this)
        initUi()
    }

    private fun initUi() {
        actBar()

    }

    private fun initStudentList(studentList: List<Student>) {
        studentAdapter = StudentAdapter(ArrayList(studentList), this)
        binding.recyclerStudents.adapter = studentAdapter
        binding.recyclerStudents.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }

    private fun actBar() {
        binding.FabAddNewStudent.setOnClickListener {
            val intent = Intent(this, AddStudentActivity::class.java)
            startActivity(intent)

        }
    }

    //adapter
    override fun onClickStudent(student: Student, position: Int) {
        val dialog = AlertDialog.Builder(this).create()
        val dialogUpdateStudentBinding = DialogUpdateStudentBinding.inflate(layoutInflater)
        dialog.setView(dialogUpdateStudentBinding.root)
        dialog.show()
        completeDialogUpdateFields(dialogUpdateStudentBinding, student)
        dialogUpdateStudentBinding.FabUpdateStudent.setOnClickListener {
            val checkFields = checkBox(dialogUpdateStudentBinding)
            if (checkFields) {
                showToast("Complete the fields !!")
            } else {
                val newStudent = updatedStudent(dialogUpdateStudentBinding, student.id)
                presenterMain.onUpdateStudent(newStudent, position)
                dialog.dismiss()
            }
        }
        dialogUpdateStudentBinding.FabCancelUpdate.setOnClickListener {
            dialog.dismiss()
        }
    }

    private fun updatedStudent(
        dialogUpdateStudentBinding: DialogUpdateStudentBinding,
        id: Int?
    ): Student {
        val firstName = dialogUpdateStudentBinding.editTxtFirstName.text.toString()
        val lastName = dialogUpdateStudentBinding.editTxtLastName.text.toString()
        val course = dialogUpdateStudentBinding.editTxtCourse.text.toString()
        val score = dialogUpdateStudentBinding.editTxtScore.text.toString()

        return Student(firstName, lastName, course, score, id)

    }

    private fun completeDialogUpdateFields(
        dialogUpdateStudentBinding: DialogUpdateStudentBinding,
        student: Student
    ) {
        dialogUpdateStudentBinding.editTxtFirstName.setText(student.firstName)
        dialogUpdateStudentBinding.editTxtLastName.setText(student.lastName)
        dialogUpdateStudentBinding.editTxtCourse.setText(student.course)
        dialogUpdateStudentBinding.editTxtScore.setText(student.score)
    }

    override fun onLongClickStudent(student: Student, position: Int) {
        val sweetAlertDialog = SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
            .setTitleText("Are you sure ?")
            .setContentText("Won't be able to recover this file ")
            .setConfirmText("Yes,Delete it!")
            .setCancelText("Cancel")
            .setConfirmClickListener {
                presenterMain.onDeleteStudent(student,position)
                it.dismiss()
            }
            .setCancelClickListener {
                it.dismiss()
            }
            .show()

    }

    private fun checkBox(dialogUpdateStudentBinding: DialogUpdateStudentBinding): Boolean {
        if (dialogUpdateStudentBinding.editTxtFirstName.text!!.isEmpty() ||
            dialogUpdateStudentBinding.editTxtLastName.text!!.isEmpty() ||
            dialogUpdateStudentBinding.editTxtCourse.text!!.isEmpty() ||
            dialogUpdateStudentBinding.editTxtScore.text!!.isEmpty()
        ) {
            return true
        } else {
            return false
        }
    }

    override fun showAllStudentFromServer(studentList: List<Student>) {
        initStudentList(studentList)
    }

    override fun showAllStudentFromDatabase(studentList: List<Student>) {
        initStudentList(studentList)
    }

    override fun deleteStudent(oldStudent: Student, position: Int) {
        studentAdapter.onRemoveStudent(oldStudent,position)
    }

    override fun updateStudent(editedStudent: Student, position: Int) {
        studentAdapter.onUpdateRecyclerList(editedStudent, position)
    }

    override fun showError(error: String) {
        Log.v("testApi",error)
    }

    override fun disposableStudent(disposable: Disposable) {
        disposableMain=disposable
    }

    override fun onDestroy() {
        super.onDestroy()
        disposableMain.dispose()
    }


}