package com.example.projectpoc.comment.commentmodel

import com.example.projectpoc.comment.CommentInterface
import com.example.projectpoc.retrofit.RetrofitClient
import com.example.projectpoc.retrofit.RetrofitInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentApi : CommentInterface.CommentModel {
    private val retrofitInterface: RetrofitInterface = RetrofitClient.getRetrofit().create(
        RetrofitInterface::class.java
    )

    override fun getCommentList(postId: Int?, commentPresenter: CommentInterface.CommentPresenter) {
        val call: Call<List<Comment>> = retrofitInterface.getComments(postId)

        call.enqueue(object : Callback<List<Comment>> {
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {

                if (!response.isSuccessful) {
                    commentPresenter.responseNotSuccessful(response.code())
                    return
                }
                response.body()?.let { commentPresenter.handleSuccessResponse(it) }
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                commentPresenter.handleFailure(t)
            }
        })
    }
}