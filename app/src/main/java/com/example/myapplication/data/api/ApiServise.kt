package com.example.myapplication

import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

    private const val BASE_URL = "https://edo.ilexx.ru/"

        private val client:OkHttpClient =
            OkHttpClient.Builder()
                .build()

        val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(client)
            .build()

    interface Api{
        @GET("test/catalog.spr")
       suspend fun getProducts(): Response<ResponseBody>
    }

object ApiService{
    val api: Api by lazy{
        retrofit.create(Api::class.java)
    }
}
