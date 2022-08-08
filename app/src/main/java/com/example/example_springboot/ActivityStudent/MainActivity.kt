package com.example.example_springboot.ActivityStudent

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.example_springboot.ActivityAddStudent.AddStudentActivity
import com.example.example_springboot.databinding.ActivityMainBinding
import com.example.example_springboot.databinding.DialogUpdateStudentBinding
import com.example.example_springboot.model.database.MyDatabase
import com.example.example_springboot.model.Student
import com.example.example_springboot.model.database.StudentDao
import com.example.example_springboot.util.showToast

class MainActivity : AppCompatActivity(),StudentAdapter.EventStudent,MainContract.ViewMain {
    lateinit var binding: ActivityMainBinding
    private lateinit var studentAdapter: StudentAdapter
    lateinit var studentDao: StudentDao
    lateinit var presenterMain: PresenterMain
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        studentDao= MyDatabase.getDatabase(this).studentDao
        presenterMain= PresenterMain(studentDao)
        presenterMain.onAttach(this)
        initUi()
    }

    private fun initUi() {
        actBar()
        initStudentList()

    }

    private fun initStudentList() {
        studentAdapter = StudentAdapter(ArrayList(studentDao.getAllStudents()),this)
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
       val dialog=AlertDialog.Builder(this).create()
        val dialogUpdateStudentBinding=DialogUpdateStudentBinding.inflate(layoutInflater)
        dialog.setView(dialogUpdateStudentBinding.root)
        dialog.show()
        dialogUpdateStudentBinding.FabUpdateStudent.setOnClickListener {
            val checkFields = checkBox(dialogUpdateStudentBinding)
            if (checkFields){
                showToast("Complete the fields !!")
            }else{
                dialog.dismiss()
            }
        }
        dialogUpdateStudentBinding.FabCancelUpdate.setOnClickListener {
            dialog.dismiss()
        }
    }

    override fun onLongClickStudent(student: Student, position: Int) {
        val sweetAlertDialog= SweetAlertDialog(this,SweetAlertDialog.WARNING_TYPE)
            .setTitleText("Are you sure ?")
            .setContentText("Won't be able to recover this file ")
            .setConfirmText("Yes,Delete it!")
            .setCancelText("Cancel")
            .setConfirmClickListener {

                it.dismiss()
            }
            .setCancelClickListener {
                it.dismiss()
            }
            .show()

    }

    private fun checkBox(dialogUpdateStudentBinding: DialogUpdateStudentBinding):Boolean {
        if (dialogUpdateStudentBinding.editTxtFirstName.text!!.isEmpty()||
            dialogUpdateStudentBinding.editTxtLastName.text!!.isEmpty()||
            dialogUpdateStudentBinding.editTxtCourse.text!!.isEmpty()||
            dialogUpdateStudentBinding.editTxtScore.text!!.isEmpty()
        ){
            return true
        }else{
            return false
        }
    }

    override fun showAllStudent(studentList: List<Student>) {
        Toast.makeText(this, "ssss", Toast.LENGTH_SHORT).show()
    }

    override fun deleteStudent(oldStudent: Student, position: Int) {
        Toast.makeText(this, "ssss", Toast.LENGTH_SHORT).show()
    }

    override fun updateStudent(editedStudent: Student, position: Int) {
        Toast.makeText(this, "ssss", Toast.LENGTH_SHORT).show()
    }


}