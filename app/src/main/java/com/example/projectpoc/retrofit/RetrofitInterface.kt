package com.example.projectpoc.retrofit

import com.example.projectpoc.comment.model.Comment
import com.example.projectpoc.post.model.PostResponse
import com.example.projectpoc.user.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitInterface {
    @GET("users")
    fun getUsers(): Call<List<User>>


    @GET("users/{id}/posts")
    fun getPosts(@Path("id") userId: Int): Call<List<PostResponse>>

    @GET("posts/{id}/comments")
    fun getComments(@Path("id") postId: Int?): Call<List<Comment>>

}