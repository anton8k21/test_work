package com.example.myapplication.data.repositoryImpl

import com.example.myapplication.ApiService
import com.example.myapplication.domain.ApiException
import com.example.myapplication.domain.NetWorkException
import com.example.myapplication.domain.UnknownException
import com.example.myapplication.domain.repository.Repository
import okhttp3.ResponseBody
import java.io.IOException

class RepositoryImpl(private val apiService: ApiService): Repository {

    override suspend fun getProduct(): ResponseBody {
    try {
        val response = apiService.api.getProducts()
        val body = response.body() ?: throw ApiException(response.code(), response.message())
        return body
    } catch (e: IOException) {
        throw NetWorkException
    } catch (e: Exception) {
        throw UnknownException
    }
}
}