package com.example.example_springboot.ActivityStudent

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.example_springboot.databinding.ItemStudentBinding
import com.example.example_springboot.model.Student

class StudentAdapter(private val studentList:ArrayList<Student>):RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {
    lateinit var binding: ItemStudentBinding

    inner class StudentViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

        @SuppressLint("SetTextI18n")
        fun bindStudent(student: Student){
            binding.txtName.text="${student.firstName} ${student.lastName}"
            binding.txtCourse.text=student.Course
            binding.txtScore.text=student.Score.toString()
            binding.txtCharFirstName.text= student.firstName[0].uppercaseChar().toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        binding= ItemStudentBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return StudentViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bindStudent(studentList[position])
    }

    override fun getItemCount(): Int {
        return studentList.size
    }


}