package com.example.example_springboot.ActivityAddStudent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.example_springboot.ActivityStudent.MainActivity
import com.example.example_springboot.databinding.ActivityStudentAddBinding
import com.example.example_springboot.model.MyDatabase
import com.example.example_springboot.model.Student
import com.example.example_springboot.model.StudentDao
import com.example.example_springboot.util.showToast

class AddStudentActivity : AppCompatActivity(),AddStudentContract.ViewAddStudent {
    lateinit var presenterAddStudent: AddStudentContract.PresenterAddStudent
    lateinit var binding: ActivityStudentAddBinding
    lateinit var studentDao: StudentDao
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityStudentAddBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        studentDao=MyDatabase.getDatabase(this).studentDao
        presenterAddStudent=PresenterAddStudent(studentDao)
        presenterAddStudent.onAttach(this)
        initUi()

    }

    override fun onDestroy() {
        presenterAddStudent.onDetach()
        super.onDestroy()
    }
    private fun initUi() {
        initActBar()
    }

    private fun initActBar() {
        binding.editTxtFirstName.requestFocus()

        binding.FabSaveStudent.setOnClickListener {
            checkBox()
            createNewStudent()
            goOnNextPage()
        }

        binding.toolBarAddStudent.setNavigationOnClickListener {
            onBackPressed()
        }

    }

    private fun goOnNextPage() {
        val intent=Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

    private fun checkBox() {
        if (binding.editTxtFirstName.text!!.isEmpty()||
            binding.editTxtLastName.text!!.isEmpty()||
            binding.editTxtCourse.text!!.isEmpty()||
            binding.editTxtScore.text!!.isEmpty()
        ){
            showToast("Complete the fields !!")
        }
    }

    private fun createNewStudent() {
        val firstName = binding.editTxtFirstName.text.toString()
        val lastName=binding.editTxtLastName.text.toString()
        val course=binding.editTxtCourse.text.toString()
        val score=binding.editTxtScore.text.toString()
        val newStudent = Student(
            firstName,
            lastName,
            course,
            score
        )
        presenterAddStudent.addNewStudent(newStudent)
    }


}