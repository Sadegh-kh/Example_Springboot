package com.example.example_springboot.model.server

import com.example.example_springboot.model.Student
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiService {

    @GET("/student")
    fun getAllStudent():Call<List<Student>>

    @POST("/student")
    fun insertStudent(@Body body:JsonObject):Call<String>
}