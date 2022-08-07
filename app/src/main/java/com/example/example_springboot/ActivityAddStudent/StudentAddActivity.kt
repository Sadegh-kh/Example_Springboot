package com.example.example_springboot.ActivityAddStudent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.example_springboot.databinding.ActivityStudentAddBinding

class StudentAddActivity : AppCompatActivity() {
    lateinit var binding: ActivityStudentAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityStudentAddBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initUi()

    }

    private fun initUi() {
        initActBar()
    }

    private fun initActBar() {
        binding.FabSaveStudent.setOnClickListener {
            checkBox()
        }

        binding.toolBarAddStudent.setNavigationOnClickListener {
            onBackPressed()
        }

    }

    private fun checkBox() {

    }
}