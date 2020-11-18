package com.example.projectpoc.model.api

import com.example.projectpoc.model.dataModel.User
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitInterface {
    @GET("users")
    fun getUsers(): Call<List<User>>
}