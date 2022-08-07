package com.example.example_springboot.util

interface BasePresenter<T>{
   fun onAttach(view:T)
   fun onDetach()
}
