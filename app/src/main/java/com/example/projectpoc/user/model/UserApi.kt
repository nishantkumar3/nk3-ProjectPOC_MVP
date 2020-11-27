package com.example.projectpoc.user.model

import com.example.projectpoc.retrofit.RetrofitClient
import com.example.projectpoc.retrofit.RetrofitInterface
import com.example.projectpoc.user.UserContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserApi : UserContract.UserModel {


    private val retrofitInterface: RetrofitInterface =
        RetrofitClient.getRetrofit().create(RetrofitInterface::class.java)

    override fun getUser(emailId: String, presenter: UserContract.UserPresenter) {

        val call = retrofitInterface.getUsers()

        call.enqueue(object : Callback<List<User>> {

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {

                if (!response.isSuccessful) {
                    presenter.responseNotSuccessful(response.code())
                    return
                }

                response.body()?.let { presenter.handleSuccessResponse(it, emailId) }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                presenter.handleFailure(t)
            }
        })


    }


}