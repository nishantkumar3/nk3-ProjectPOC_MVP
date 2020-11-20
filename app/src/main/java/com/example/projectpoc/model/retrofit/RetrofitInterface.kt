package com.example.projectpoc.model.retrofit

import com.example.projectpoc.model.dataModel.Comment
import com.example.projectpoc.model.dataModel.Post
import com.example.projectpoc.model.dataModel.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitInterface {
    @GET("users")
    fun getUsers(): Call<List<User>>


    @GET("users/{id}/posts")
    fun getPosts(@Path("id") userId: Int): Call<List<Post>>

    @GET("posts/{id}/comments")
    fun getComments(@Path("id") postId: Int?): Call<List<Comment>>

}