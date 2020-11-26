package com.example.projectpoc.post.postModel

import com.example.projectpoc.post.postContract.PostInterface
import com.example.projectpoc.model.retrofit.RetrofitClient
import com.example.projectpoc.model.retrofit.RetrofitInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostApi: PostInterface.PostModel {

    private val retrofitInterface:RetrofitInterface = RetrofitClient.getRetrofit().create(RetrofitInterface::class.java)

    override fun getPostList(userId: Int?,postPresenter: PostInterface.PostPresenter){

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