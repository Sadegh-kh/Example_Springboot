package com.example.example_springboot.ActivityAddStudent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.example_springboot.ActivityStudent.MainActivity
import com.example.example_springboot.databinding.ActivityStudentAddBinding
import com.example.example_springboot.model.database.MyDatabase
import com.example.example_springboot.model.Student
import com.example.example_springboot.model.database.StudentDao
import com.example.example_springboot.model.server.ApiManager
import com.example.example_springboot.util.showToast
import io.reactivex.disposables.Disposable

class AddStudentActivity : AppCompatActivity(), AddStudentContract.ViewAddStudent {
    lateinit var presenterAddStudent: AddStudentContract.PresenterAddStudent
    lateinit var binding: ActivityStudentAddBinding
    lateinit var studentDao: StudentDao
    lateinit var apiManager: ApiManager
    var newId = 0
    lateinit var disposableAddStudentActivity: Disposable
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityStudentAddBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        studentDao = MyDatabase.getDatabase(this).studentDao
        apiManager = ApiManager()
        presenterAddStudent = PresenterAddStudent(studentDao, apiManager)
        presenterAddStudent.onAttach(this)
        initUi()

    }

    override fun onDestroy() {
        presenterAddStudent.onDetach()
        disposableAddStudentActivity.dispose()
        super.onDestroy()
    }

    private fun initUi() {
        initActBar()
    }

    private fun initActBar() {
        binding.editTxtFirstName.requestFocus()

        binding.FabSaveStudent.setOnClickListener {
            createNewStudent()
        }

        binding.toolBarAddStudent.setNavigationOnClickListener {
            onBackPressed()
        }

    }

    private fun goOnNextPage() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun checkEmptyFields(): Boolean {
        if (binding.editTxtFirstName.text!!.isEmpty() ||
            binding.editTxtLastName.text!!.isEmpty() ||
            binding.editTxtCourse.text!!.isEmpty() ||
            binding.editTxtScore.text!!.isEmpty()
        ) {
            return true
        } else {
            return false
        }
    }

    private fun createNewStudent() {
        val firstName = binding.editTxtFirstName.text.toString()
        val lastName = binding.editTxtLastName.text.toString()
        val course = binding.editTxtCourse.text.toString()
        val score = binding.editTxtScore.text.toString()
        val newStudent = Student(
            firstName,
            lastName,
            course,
            score,
            newId + 1
        )
        if (checkEmptyFields()) {
            showToast("Complete the fields !!")
        } else {
            presenterAddStudent.addNewStudent(newStudent)
            goOnNextPage()
        }

    }

    override fun showMassageFromServer(massage: String) {
        Log.v("testApi", massage)
    }

    override fun showNewStudentId(id: Int) {
        newId = id
    }

    override fun disposableStudentAddActivity(disposable: Disposable) {
        disposableAddStudentActivity=disposable
    }


}

