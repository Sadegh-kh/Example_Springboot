package com.example.example_springboot.model.server

import com.example.example_springboot.model.Student
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.*


interface ApiService {

    @GET("/student")
    fun getAllStudent():Call<List<Student>>

    @POST("/student")
    fun insertStudent(@Body body:JsonObject):Call<String>

    @PUT("/student/update{Id}")
    fun updateStudent(@Path("Id")id:Int,@Body jsonObject: JsonObject):Call<String>

    @DELETE("/student/delete{Id}")
    fun deleteStudent(@Path("Id")id:Int):Call<String>
}