package com.example.example_springboot.ActivityStudent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.example_springboot.ActivityAddStudent.StudentAddActivity
import com.example.example_springboot.R
import com.example.example_springboot.databinding.ActivityMainBinding
import com.example.example_springboot.model.Student

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initUi()
    }

    private fun initUi() {
        actBar()
    }

    private fun actBar() {
        binding.FabAddNewStudent.setOnClickListener {
            val intent = Intent(this,StudentAddActivity::class.java)
            startActivity(intent)
        }
    }
}