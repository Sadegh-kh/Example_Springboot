package com.example.example_springboot.ActivityAddStudent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.example_springboot.ActivityStudent.MainActivity
import com.example.example_springboot.databinding.ActivityStudentAddBinding
import com.example.example_springboot.model.Student
import com.example.example_springboot.util.Constant
import com.example.example_springboot.util.showToast

class StudentAddActivity : AppCompatActivity(),AddStudentContract.View {
    lateinit var presenterAddStudent:AddStudentContract.Presenter
    lateinit var binding: ActivityStudentAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityStudentAddBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
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
        }

        binding.toolBarAddStudent.setNavigationOnClickListener {
            onBackPressed()
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
            score.toInt()
        )
        presenterAddStudent.addNewStudent(newStudent)
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

    override fun showNewStudent(student: Student) {
        val intent=Intent(this,MainActivity::class.java)
        intent.putExtra(Constant.KEY_ADD_STUDENT,student)
        startActivity(intent)
    }
}