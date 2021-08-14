package com.example.retrofitpractice

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitAPI {
    @POST("users")
    fun postUser(@Body userData:PostModel): Call<PostModel>
    @GET("users?page=2")
    fun getUser():Call<Datamodel>
}