package com.example.testrxandroid

import androidx.lifecycle.Observer
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/v2/coins")
    fun getCoinList():Observable<CoinListResponse>
}