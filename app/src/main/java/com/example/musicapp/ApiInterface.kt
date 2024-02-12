package com.example.musicapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiInterface {


        @GET("1774652d-343c-4f5b-adb4-0bb37a4562bc")
        fun getData(): Call<MyData>
}