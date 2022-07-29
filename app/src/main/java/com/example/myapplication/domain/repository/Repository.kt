package com.example.myapplication.domain.repository

import okhttp3.ResponseBody

interface Repository  {
    suspend fun getProduct(): ResponseBody
}