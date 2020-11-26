package com.example.projectpoc.post.model

import com.example.projectpoc.post.PostContract
import com.example.projectpoc.retrofit.RetrofitClient
import com.example.projectpoc.retrofit.RetrofitInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostApi: PostContract.PostModel {

    private val retrofitInterface: RetrofitInterface = RetrofitClient.getRetrofit().create(
        RetrofitInterface::class.java)

    override fun getPostList(userId: Int?,postPresenter: PostContract.PostPresenter){

        val call : Call<List<PostResponse>>? = userId?.let { retrofitInterface.getPosts(it) }
        call?.enqueue(object : Callback<List<PostResponse>>{
            override fun onResponse(call: Call<List<PostResponse>>, response: Response<List<PostResponse>>) {
                if(!response.isSuccessful){
                    postPresenter.responseNotSuccessful(response.code())
                    return
                }
                response.body()?.let { postPresenter.handleSuccessResponse(it) }
            }

            override fun onFailure(call: Call<List<PostResponse>>, t: Throwable) {
                postPresenter.handleFailure(t)
            }
        })
    }
}