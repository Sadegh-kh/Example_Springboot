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

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var studentAdapter: StudentAdapter
    private lateinit var studentList:ArrayList<Student>
    lateinit var studentDao: StudentDao
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        studentDao=MyDatabase.getDatabase(this).studentDao
        initUi()
    }

    private fun initUi() {
        actBar()
        initStudentList()

    }

    private fun initStudentList() {
        studentList = arrayListOf<Student>(
            Student("Sadegh", "Khoshbayan", "Android", "150"),
            Student("Abas", "Khoshbayan", "Web", "130"),
            Student("Hossain", "Mehdizade", "Web", "180")
        )
        studentDao.insertAllStudents(studentList)
        studentAdapter = StudentAdapter(ArrayList(studentDao.getAllStudents()))
        binding.recyclerStudents.adapter = studentAdapter
        binding.recyclerStudents.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }
    override fun onResume() {
        super.onResume()
        addStudent()
    }
    private fun addStudent() {
        val newStudent = intent.getParcelableExtra<Student>(Constant.KEY_ADD_STUDENT)
        if (newStudent != null) {
            studentAdapter.addItem(newStudent)
            binding.recyclerStudents.scrollToPosition(0)
            Log.v("testList",studentList.toString())
        }

    }

    private fun actBar() {
        binding.FabAddNewStudent.setOnClickListener {
            val intent = Intent(this, AddStudentActivity::class.java)
            startActivity(intent)

        }
    }




}