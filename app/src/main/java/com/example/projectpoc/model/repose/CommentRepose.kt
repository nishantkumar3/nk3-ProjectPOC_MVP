package com.example.projectpoc.model.repose

import com.example.projectpoc.interfaces.CommentInterface
import com.example.projectpoc.model.retrofit.RetrofitClient
import com.example.projectpoc.model.retrofit.RetrofitInterface
import com.example.projectpoc.model.dataModel.Comment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentRepose:CommentInterface.CommentModel {
    private val retrofitInterface:RetrofitInterface = RetrofitClient.getRetrofit().create(RetrofitInterface::class.java)

    override fun getCommentList(postId: Int?, commentPresenter: CommentInterface.CommentPresenter) {
        val call: Call<List<Comment>> = retrofitInterface.getComments(postId)

        call.enqueue(object :Callback<List<Comment>>{
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {

                if(!response.isSuccessful){
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