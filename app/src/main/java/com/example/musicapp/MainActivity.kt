package com.example.musicapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var myAdapter: MyAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView= findViewById(R.id.recyclerView)

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://mocki.io/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()
        retrofitData.enqueue(object : Callback<MyData?> {
            override fun onResponse(call: Call<MyData?>, response: Response<MyData?>) {
                val responseBody = response.body()
                val dataList = responseBody?.data!!

                myAdapter= MyAdapter(this@MainActivity,dataList)
                recyclerView.adapter =myAdapter
                recyclerView.layoutManager= LinearLayoutManager(this@MainActivity)
                Log.d("Music", "onResponse" + response.body())

            }

            override fun onFailure(call: Call<MyData?>, t: Throwable) {
                Log.d("onFailure", "onFailure" + t.message)
            }
        })
    }
}