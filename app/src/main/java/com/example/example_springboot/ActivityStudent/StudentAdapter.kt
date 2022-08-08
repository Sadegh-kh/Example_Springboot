package com.example.example_springboot.ActivityStudent

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.example_springboot.databinding.ItemStudentBinding
import com.example.example_springboot.model.Student

class StudentAdapter(private val studentList:ArrayList<Student>,private val eventStudent: EventStudent):RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {
    lateinit var binding: ItemStudentBinding

    inner class StudentViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

        @SuppressLint("SetTextI18n")
        fun bindStudent(student: Student){
            binding.txtName.text="${student.firstName} ${student.lastName}"
            binding.txtCourse.text=student.course
            binding.txtScore.text=student.score
            binding.txtCharFirstName.text= student.firstName[0].uppercaseChar().toString()

            itemView.setOnClickListener {
                //update
                eventStudent.onClickStudent(student,adapterPosition)
            }
            itemView.setOnLongClickListener {
                //remove
                eventStudent.onLongClickStudent(student,adapterPosition)
                true
            }
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

    interface EventStudent{
        fun onClickStudent(student: Student,position: Int)
        fun onLongClickStudent(student: Student,position: Int)
    }



}