package com.example.testrxandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.testrxandroid.Retrofit.apiService
import com.example.testrxandroid.databinding.ActivityMainBinding
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    val disposable = Observable.interval(0, 10, TimeUnit.SECONDS)
        .flatMap { apiService.getCoinList() }
        .retryWhen { errors ->
            errors.flatMap { error ->
                if (error is HttpException && error.code() == 429) {
                    // Implement exponential backoff here
                    Observable.timer(10, TimeUnit.SECONDS)
                } else {
                    Observable.error(error)
                }
            }
        }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({ data ->
            // Handle received data
            Toast.makeText(this,"5555",Toast.LENGTH_SHORT).show()
            Log.d("test-rx-5555555","Yes")
        }, { throwable ->
            // Handle error
            Log.d("test-rx-5555555","No ${throwable}")
        })

    private val binding:ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onResume() {
        super.onResume()
        Log.d("test-rx-5555555","11")
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
        Log.d("test-rx-5555555","55")
    }
}