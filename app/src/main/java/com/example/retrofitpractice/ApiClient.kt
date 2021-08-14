package com.example.retrofitpractice

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    var BASE_URL = "https://reqres.in/api/"
    val getClient: RetrofitAPI
        get() {
            val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build()
            return retrofit.create(RetrofitAPI::class.java)
        }
}