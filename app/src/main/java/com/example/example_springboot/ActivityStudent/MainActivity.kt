package com.example.example_springboot.ActivityStudent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.example_springboot.ActivityAddStudent.AddStudentActivity
import com.example.example_springboot.databinding.ActivityMainBinding
import com.example.example_springboot.model.MyDatabase
import com.example.example_springboot.model.Student
import com.example.example_springboot.model.StudentDao
import com.example.example_springboot.util.Constant

class MainActivity : AppCompatActivity(),StudentAdapter.EventStudent,MainContract.ViewMain {
    lateinit var binding: ActivityMainBinding
    private lateinit var studentAdapter: StudentAdapter
    lateinit var studentDao: StudentDao
    lateinit var presenterMain: PresenterMain
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        studentDao=MyDatabase.getDatabase(this).studentDao
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
        TODO("Not yet implemented")
    }

    override fun onLongClickStudent(student: Student, position: Int) {
        TODO("Not yet implemented")
    }

    override fun showAllStudent(studentList: List<Student>) {
        TODO("Not yet implemented")
    }

    override fun deleteStudent(oldStudent: Student, position: Int) {
        TODO("Not yet implemented")
    }

    override fun updateStudent(editedStudent: Student, position: Int) {
        TODO("Not yet implemented")
    }


}