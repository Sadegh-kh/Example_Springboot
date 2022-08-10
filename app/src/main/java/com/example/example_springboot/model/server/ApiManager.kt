package com.example.example_springboot.model.server

import android.util.Log
import com.example.example_springboot.model.Student
import com.example.example_springboot.util.Constant
import com.google.gson.JsonObject
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager {
    private val apiService:ApiService

    init {
        val retrofit= Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        apiService=retrofit.create(ApiService::class.java)
    }

    fun getAllStudent(apiCallBack: ApiCallBack<List<Student>>){
        apiService
            .getAllStudent()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :SingleObserver<List<Student>>{
                override fun onSubscribe(d: Disposable) {
                    apiCallBack.onSubscribe(d)
                }

                override fun onSuccess(t: List<Student>) {
                    apiCallBack.onSuccess(t)
                }

                override fun onError(e: Throwable) {
                    apiCallBack.onError(e.message!!)
                }
            })
    }

    fun insertStudent(apiCallBack: ApiCallBack<String>,newStudent: JsonObject){

        apiService.insertStudent(newStudent).enqueue(object :Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                apiCallBack.onSuccess(response.body()!!)
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
               apiCallBack.onError(t.message!!)
            }
        })
    }

    fun updateStudent(apiCallBack: ApiCallBack<String>,id:Int,editStudent:JsonObject){
        apiService.updateStudent(id,editStudent).enqueue(object :Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                apiCallBack.onSuccess(response.body()!!)
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                apiCallBack.onError(t.message!!)
            }
        })
    }

    fun deleteStudent(apiCallBack: ApiCallBack<String>,id:Int){
        apiService.deleteStudent(id).enqueue(object :Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                apiCallBack.onSuccess(response.body()!!)
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                apiCallBack.onError(t.message!!)
            }
        })
    }

    interface ApiCallBack<T>{
        fun onSuccess(data:T)
        fun onError(error:String)
        fun onSubscribe(disposable: Disposable)
    }
}