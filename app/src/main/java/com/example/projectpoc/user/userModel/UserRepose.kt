package com.example.projectpoc.user.userModel

import com.example.projectpoc.user.userContract.UserInterface
import com.example.projectpoc.model.retrofit.RetrofitClient
import com.example.projectpoc.model.retrofit.RetrofitInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepose : UserInterface.UserModel {


    private val retrofitInterface: RetrofitInterface =
        RetrofitClient.getRetrofit().create(RetrofitInterface::class.java)

    override fun getUser(emailId: String, presenter: UserInterface.UserPresenter) {

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