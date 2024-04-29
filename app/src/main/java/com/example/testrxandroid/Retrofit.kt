package com.example.testrxandroid

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {

    private val retrofit = Retrofit.Builder()
    .baseUrl("https://api.coinranking.com/v2/")
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .build()

    val apiService = retrofit.create(ApiService::class.java)
}