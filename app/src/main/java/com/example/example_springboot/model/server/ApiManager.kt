package com.example.example_springboot.model.server

import android.util.Log
import com.example.example_springboot.model.Student
import com.example.example_springboot.util.Constant
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager {
    private val apiService:ApiService

    init {
        val retrofit= Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService=retrofit.create(ApiService::class.java)
    }

    fun getAllStudent(apiCallBack: ApiCallBack<List<Student>>){
        apiService.getAllStudent().enqueue(object :Callback<List<Student>>{
            override fun onResponse(call: Call<List<Student>>, response: Response<List<Student>>) {
                val allStudent=response.body()!!
                apiCallBack.onSuccess(allStudent)
                Log.v("testList",allStudent.toString())
            }

            override fun onFailure(call: Call<List<Student>>, t: Throwable) {
                apiCallBack.onError(t.message!!)
            }
        })
    }

    interface ApiCallBack<T>{
        fun onSuccess(data:T)

        fun onError(error:String)
    }
}