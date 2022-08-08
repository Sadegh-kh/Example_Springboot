package com.example.example_springboot.model.server

import com.example.example_springboot.model.Student
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiService {

    @GET("/student")
    fun getAllStudent():Call<List<Student>>
}