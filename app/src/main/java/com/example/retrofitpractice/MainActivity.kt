package com.example.retrofitpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitpractice.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var vb: ActivityMainBinding
    lateinit var adapter:Adapter
    private lateinit var datamodel:Datamodel
    private lateinit var retrofitApi: RetrofitAPI
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb.root)
        vb.btnPost.setOnClickListener {
            val name = vb.edtName.text.toString()
            val job = vb.edtJob.text.toString()
            if (name.isEmpty()) {
                vb.edtName.error = "Enter Name"
                vb.edtName.requestFocus()
                return@setOnClickListener
            }
            if (job.isEmpty()) {
                vb.edtJob.error = "Enter Job detail"
                vb.edtJob.requestFocus()
                return@setOnClickListener
            }
            postData(name, job)
        }
        vb.btnGet.setOnClickListener {
            getData()
        }
    }

    private fun getData() {
        vb.tvTv.visibility=View.GONE
        var userList=ArrayList<UserModel>()
        val retrofit=ApiClient.getClient.getUser()
        retrofit.enqueue(object :Callback<Datamodel>{
            override fun onResponse(
                call: Call<Datamodel>?,
                response: Response<Datamodel>?
            ) {
                datamodel= response?.body()!!
                userList=datamodel.data
                adapter= Adapter(userList,this@MainActivity)
                vb.rvList.visibility=View.VISIBLE
                vb.rvList.adapter=adapter
                vb.rvList.layoutManager=LinearLayoutManager(this@MainActivity)
            }

            override fun onFailure(call: Call<Datamodel>?, t: Throwable?) {
                Toast.makeText(this@MainActivity, "${t?.message}", Toast.LENGTH_SHORT).show()
            }
        })

    }

    private fun postData(name: String, job: String) {
        val postModel = PostModel(name, job)
        val retrofit=ApiClient.getClient.postUser(postModel)
        retrofit.enqueue(object : Callback<PostModel> {
            override fun onResponse(call: Call<PostModel>?, response: Response<PostModel>?) {
                Toast.makeText(this@MainActivity, "Success", Toast.LENGTH_SHORT).show()
                val responseBody: PostModel? = response?.body()
                val string =
                    "Response code: " + response?.code() + "\n Job:" + responseBody?.job + "\n Name:" + responseBody?.name
                vb.tvTv.text=string
            }

            override fun onFailure(call: Call<PostModel>?, t: Throwable?) {
                vb.tvTv.text=t?.message.toString()
            }

        })


    }
}