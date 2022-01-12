package com.example.networking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.networking.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.text.StringBuilder

class MainActivity : AppCompatActivity() {

    val BASE_URL="https://jsonplaceholder.typicode.com/"
    lateinit var binding: ActivityMainBinding
    lateinit var myAdapter: MyAdapter
    lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerview.setHasFixedSize(true)
        linearLayoutManager= LinearLayoutManager(this)
        binding.recyclerview.layoutManager=linearLayoutManager
        getMyData()
    }

    private fun getMyData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofit=retrofitBuilder.getData()
        retrofit.enqueue(object : Callback<List<MyDataItem>?> {
            override fun onResponse(
                call: Call<List<MyDataItem>?>,
                response: Response<List<MyDataItem>?>
            ) {
                val responseBody=response.body()!!

              /*  val myStringBuilder=StringBuilder()
                for(myData in responseBody) {
                    myStringBuilder.append(myData.id)
                    myStringBuilder.append("\n")
                }

                binding.txt.text = myStringBuilder*/

                myAdapter=MyAdapter(baseContext,responseBody)
                myAdapter.notifyDataSetChanged()
                binding.recyclerview.adapter=myAdapter
            }

            override fun onFailure(call: Call<List<MyDataItem>?>, t: Throwable) {
                Log.d("MainActivity","on Failure: "+t.message)
            }
        })
    }
}