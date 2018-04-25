package com.cgf.learningpaging

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class UtilRetrofit {
    companion object {
        private var heart: UtilRetrofit? = null

        fun getInstance(): UtilRetrofit? {

            if (heart == null) {
                synchronized(UtilRetrofit::class.java) {
                    if (heart == null) {
                        heart = UtilRetrofit()
                    }
                }
            }
            return heart
        }
    }


    private class MyLogger : HttpLoggingInterceptor.Logger {
        override fun log(message: String) {
            if (BuildConfig.DEBUG) Log.i("HTTP_LOG", message)

        }

    }

    fun createRetrofit(): Webservice {
        val loggingInterceptor = HttpLoggingInterceptor(MyLogger())
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor {
                    val request = it.request()
                    it.proceed(request.newBuilder().header("x-user-client", "ANDROID")
                            .method(request.method(), request.body()).build())

                }

                .build()
        val retrofit = Retrofit.Builder()
                .baseUrl(Constants.backend)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        return retrofit.create(Webservice::class.java)
    }
}